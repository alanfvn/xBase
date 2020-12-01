package octils.base;

import octils.base.sql.SQLManager;
import octils.base.api.twit.TwitterManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Base extends JavaPlugin {

    public void onEnable() {
        this.registerConfig();
        final FileConfiguration cf = getConfig();


        TwitterManager.get().setup(cf.getString("auth.key"), cf.getString("auth.secret"),
                cf.getString("auth.token"), cf.getString("auth.token_secret"));

        //INIT SQL
        try {
            SQLManager.get().initPool(
                    cf.getString("sql.ip"),
                    cf.getString("sql.port"),
                    cf.getString("sql.user"),
                    cf.getString("sql.password"),
                    cf.getString("sql.database"),
                    3, 1);

        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.shutdown();
        }
    }

    public void onDisable() {
        SQLManager.get().closePool();
    }

    
    private void registerConfig() {
        try {
            if (!getDataFolder().exists()) {
                if(getDataFolder().mkdirs())
                    getLogger().info("Config file not found. Creating...");
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists())
                saveDefaultConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        getConfig().options().copyDefaults(true);
    }

}

