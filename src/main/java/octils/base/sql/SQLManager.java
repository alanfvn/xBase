package octils.base.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLManager
{
    private static SQLManager sql;
    private HikariDataSource dataSource;
    private HikariConfig hikariConfig;



    private SQLManager() { }

    public static SQLManager get(){
        return sql == null ? sql = new SQLManager() : sql;
    }

    public void initPool(String ip, String port, String user, String password, String database, int minCon, int maxCon) throws Exception {

        if(hikariConfig != null){
            throw new Exception("Connection pool already initialized");
        }else {
            hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl("jdbc:postgresql://" + ip + ":" + port + "/" + database);
            hikariConfig.setDriverClassName(org.postgresql.Driver.class.getName());
            hikariConfig.setUsername(user);
            hikariConfig.setPassword(password);
            hikariConfig.setConnectionTimeout(2 * 1000);
            hikariConfig.setIdleTimeout(60 * 1000);
            hikariConfig.setMaxLifetime(300 * 1000);
            hikariConfig.setMinimumIdle(minCon);
            hikariConfig.setMaximumPoolSize(maxCon);
            dataSource = new HikariDataSource(hikariConfig);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close(Connection conn, PreparedStatement ps, ResultSet res) {
        if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        if (ps != null) try { ps.close(); } catch (SQLException ignored) {}
        if (res != null) try { res.close(); } catch (SQLException ignored) {}
    }

    public void close(Connection conn, PreparedStatement ps){
        close(conn, ps, null);
    }

    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

}
