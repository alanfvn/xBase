package octils.base.util.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ReflectionUtil {


    private ReflectionUtil(){}

    public static void sendPacket(Object nmsPlayer, Object packet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        Object plrConnection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
        plrConnection.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(plrConnection, packet);
    }

    public static Object getHandle(Player p) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return p.getClass().getMethod("getHandle").invoke(p);
    }

    public static Class<?> getNmsClass(String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + getServerVersion()+ "." + nmsClassName);
    }

    private static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }



}
