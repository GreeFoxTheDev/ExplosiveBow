package greefox.explosiveBow;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Give implements CommandExecutor, TabCompleter {

    ExplosiveBow plugin;

    public Give(ExplosiveBow plugin) {
        this.plugin = plugin;
    }
    private void addItemToInventory(Player player, ItemStack item) {
        player.getInventory().addItem(item);
    }
    private void dropItem(Player player, ItemStack itemStack) {
        player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("giveexplosive")) {
            if (args.length == 1){
                if (commandSender instanceof Player player && player.isOp()){
                    if (!(player.getInventory().firstEmpty() == -1)) {
                        switch (args[0]) {
                            case "explosive_bow":
                                addItemToInventory(player, ExplosiveBowItem.explosiveBow);
                            case "explosive_xbow":
                                addItemToInventory(player, ExplosiveBowItem.explosiveXBow);
                        }
                    } else {
                        switch (args[0]) {
                            case "explosive_bow":
                                dropItem(player, ExplosiveBowItem.explosiveBow);
                            case "explosive_xbow":
                                dropItem(player, ExplosiveBowItem.explosiveXBow);
                        }
                    }
                }
            }

        }
    return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return List.of();
    }
}
