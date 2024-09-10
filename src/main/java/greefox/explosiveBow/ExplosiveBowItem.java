package greefox.explosiveBow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ExplosiveBowItem extends JavaPlugin {


    public static void init(){
        createExplosiveBow();
        createExplosiveXBow();
    }
    public static ItemStack explosiveBow;
    public static ItemStack explosiveXBow;

    private static void createExplosiveBow(){
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Explosive Bow");
        im.setItemName("explosive_bow");
        im.setCustomModelData(4);


        item.setItemMeta(im);
        explosiveBow = item;
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("explosive_bow"), item);
        sr.shape("TTT", "TBT", "TXT");
        sr.setIngredient('T', Material.TNT);
        sr.setIngredient('B', Material.BOW);
        sr.setIngredient('X', Material.NETHER_STAR);

        Bukkit.getServer().addRecipe(sr);
    }
    private static void createExplosiveXBow(){
        ItemStack item = new ItemStack(Material.CROSSBOW, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Explosive Crossbow");
        im.setItemName("explosive_xbow");
        im.setCustomModelData(4);


        item.setItemMeta(im);
        explosiveXBow = item;
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("explosive_xbow"), item);
        sr.shape("TTT", "TBT", "TXT");
        sr.setIngredient('T', Material.TNT);
        sr.setIngredient('B', Material.CROSSBOW);
        sr.setIngredient('X', Material.NETHER_STAR);

        Bukkit.getServer().addRecipe(sr);

    }
}
