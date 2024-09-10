package greefox.explosiveBow;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ExplosiveBowListener implements Listener {
    static final FileConfiguration config = ExplosiveBow.getInstance().getConfig();

    final ExplosiveBow plugin;

    public ExplosiveBowListener(ExplosiveBow plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onFireworkLaunch(ProjectileLaunchEvent event) {
        // This is where you detect when the projectile is launched.
        if (event.getEntity().getType() == EntityType.FIREWORK_ROCKET) {
            Firework firework = (Firework) event.getEntity();

            // Check if the shooter is a player
            if (firework.getShooter() instanceof Player player) {

                // Check if the player is holding an explosive crossbow
                ItemStack crossbow = player.getInventory().getItemInMainHand();

                if (crossbow.hasItemMeta() && crossbow.getItemMeta().hasItemName()) {
                    if (crossbow.getItemMeta().getItemName().equalsIgnoreCase("explosive_xbow")) {
                        if (!crossbow.containsEnchantment(Enchantment.PIERCING)) {


                            // Mark this arrow as explosive
                            firework.setCustomName("rocket");
                            firework.setCustomNameVisible(false);
                            firework.setGlowing(true);
                        } else {
                            firework.setCustomName("pier_rocket");
                            firework.setCustomNameVisible(false);
                            firework.setGlowing(true);
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void onFireworkExplosion(ProjectileHitEvent e) {

        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntity().getType() == EntityType.FIREWORK_ROCKET) {
                Firework firework = (Firework) e.getEntity();

                if (Objects.requireNonNull(firework.getCustomName()).equalsIgnoreCase("rocket")) {
                    float explosion_power = (float) config.getDouble("explosive-xbow.power");
                    firework.getWorld().createExplosion(firework.getLocation(), explosion_power, false, true);
                    if (config.getBoolean("explosive-xbow.particles")) {
                        firework.getWorld().spawnParticle(Particle.EXPLOSION, firework.getLocation(), 4);
                        firework.getWorld().playSound(firework.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                    }
                    firework.remove();

                } else if (firework.getCustomName().equalsIgnoreCase("pier_rocket")) {

                    float explosion_power = (float) config.getDouble("explosive-xbow.power");
                    firework.getWorld().createExplosion(firework.getLocation(), explosion_power, false, true);
                    firework.getWorld().createExplosion(firework.getLocation().add(0,-5,0), explosion_power, false, true);
                    firework.getWorld().createExplosion(firework.getLocation().add(0,-10,0), explosion_power, false, true);


                    if (config.getBoolean("explosive-xbow.particles")) {
                        firework.getWorld().spawnParticle(Particle.EXPLOSION, firework.getLocation(), 4);
                        firework.getWorld().playSound(firework.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);

                    }
                    firework.remove();


                }
            }
        }
    }

    @EventHandler
    public void onArrowLaunch(ProjectileLaunchEvent event) {
        // This is where you detect when the projectile is launched.
        if (event.getEntity().getType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getEntity();

            // Check if the shooter is a player
            if (arrow.getShooter() instanceof Player player) {

                // Check if the player is holding an explosive crossbow
                ItemStack bow = player.getInventory().getItemInMainHand();

                if (bow.hasItemMeta() && bow.getItemMeta().hasItemName()) {
                    if (bow.getItemMeta().getItemName().equalsIgnoreCase("explosive_bow")) {

                        // Mark this arrow as explosive
                        arrow.setCustomName("arrow");
                        arrow.setCustomNameVisible(false);
                        arrow.setGlowing(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArrowExplosion(ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            if (e.getEntity().getType() == EntityType.ARROW) {
                Arrow arrow = (Arrow) e.getEntity();

                if (Objects.requireNonNull(arrow.getCustomName()).equalsIgnoreCase("arrow")) {
                    float explosion_power = (float) config.getDouble("explosive-bow.power");
                    arrow.getWorld().createExplosion(arrow.getLocation(), explosion_power, false, true);
                    if (config.getBoolean("explosive-bow.particles")) {
                        arrow.getWorld().spawnParticle(Particle.EXPLOSION, arrow.getLocation(), 4);
                        arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                    }
                    arrow.remove();
                }

            }
        }
    }
}



