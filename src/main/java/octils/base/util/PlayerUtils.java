package octils.base.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerUtils {

    private PlayerUtils() {
        throw new RuntimeException("Cannot instantiate a utility class.");
    }

    public static boolean nameIsValid(String s) {
        return s.matches("^\\w{2,16}$");
    }

    public static void BroadcastSound(Sound s, float f1,float f2) {
        Bukkit.getOnlinePlayers().forEach(x -> x.playSound(x.getLocation(), s, f1, f2));
    }

    public static void notifyOnline(String message,String permission) {
        Bukkit.getOnlinePlayers().stream().filter(x -> x.hasPermission(permission)).forEach(x -> x.sendMessage(message));
    }

    public static void clearEffects(Player p) {
        p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
    }

    public static void safeTeleport(Player player, Location location) {
        player.setFallDistance(0);
        player.setNoDamageTicks(20);
        player.teleport(location);
    }
}
