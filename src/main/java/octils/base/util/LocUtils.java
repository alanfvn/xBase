package octils.base.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class LocUtils {

    private LocUtils() {
        throw new RuntimeException("Cannot instantiate a utility class.");
    }

    public static String locationToString(Location location) {
        if(location==null){
            return null;
        }
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
    }

    public static Location stringToLocation(String s) {
        if(s.isEmpty()){
            return null;
        }
        String[] split = s.split(",");
        return new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]));
    }

    public static boolean isInsideLocation(Location l, int radius) {
        int px = l.getBlockX();
        int pz = l.getBlockZ();
        int minX = -radius - 1;
        int maxX = radius + 1;
        int maxZ = radius + 1;
        int minZ = -radius - 1;
        return px > minX && px < maxX && pz > minZ && pz < maxZ;
    }


    public static boolean isInsideLocation(Location center, Location current, int radius, int miny) {
        int px = Math.abs(current.getBlockX());
        int pz = Math.abs(current.getBlockZ());
        int py = current.getBlockY();

        int cx = radius+Math.abs(center.getBlockX());
        int cz = radius+Math.abs(center.getBlockZ());

        return px < cx && pz < cz && py > miny;
    }


    public static List<Location> getSphere(Location centerBlock, int radius, boolean hollow) {

        List<Location> circleBlocks = new ArrayList<>();

        int bx = centerBlock.getBlockX();
        int by = centerBlock.getBlockY();
        int bz = centerBlock.getBlockZ();

        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {

                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));

                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {
                        Location l = new Location(centerBlock.getWorld(), x, y, z);
                        circleBlocks.add(l);
                    }
                }
            }
        }
        return circleBlocks;
    }
}
