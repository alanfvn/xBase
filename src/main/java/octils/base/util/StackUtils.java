package octils.base.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class StackUtils {

    private StackUtils() {
        throw new RuntimeException("Cannot instantiate a utility class.");
    }

    public static void getArmorSet(String type, Player player){
        String[] items = {"_BOOTS", "_LEGGINGS", "_CHESTPLATE", "_HELMET"};
        String[] valid = {"DIAMOND", "GOLD", "IRON", "LEATHER", "CHAINMAIL"};
        boolean b = Arrays.stream(valid).anyMatch(x -> x.equalsIgnoreCase(type));
        if(b){
            ItemStack[] armor = new ItemStack[4];
            for (int i = 0; i < items.length; i++) {
                armor[i] =  new ItemStack(Material.valueOf(type.toUpperCase()+items[i]));
            }
            player.getInventory().setArmorContents(armor);
        }
    }

    public static ItemStack getGoldenHeads(int i) {
        return new ItemBuilder(Material.GOLDEN_APPLE).amount(i).durability(0)
                .name("&6Golden Head")
                .lore(Arrays.asList("Some say consuming the head of a",
                "fallen foe strengthens the blood")).build();
    }
}
