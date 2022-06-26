package io.github.JoltMuz.LaserTag;

import static io.github.JoltMuz.LaserTag.Cooldown.Explosive;
import static io.github.JoltMuz.LaserTag.Cooldown.Shoot;
import static io.github.JoltMuz.LaserTag.Cooldown.checkCooldown;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class Explosive implements Listener
{
	@EventHandler
    public void Shoot(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        if (item != null && item.getType() == Material.IRON_HOE &&
                item.hasItemMeta() &&
                item.getItemMeta().hasDisplayName() &&
                item.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Laser Gun"))
        {
            if (e.getAction() == Action.LEFT_CLICK_AIR)
            {
                if (checkCooldown(p.getName(), Shoot, 0.5))
                {
                    ArrayList<Location> Locations = new ArrayList<>();
                    for ( int i =2 ; i < 10; i += 1)
                    {
                        Locations.add(p.getEyeLocation().add(p.getLocation().getDirection().multiply(i)));
                    }
                    for ( int i = 0 ; i < Locations.size(); i ++)
                    {
                        p.getWorld().spigot().playEffect(Locations.get(i), Effect.MAGIC_CRIT, 0,0,0,0,0,0,1,10);
                        p.getWorld().playSound(p.getLocation(), Sound.BAT_DEATH,20,1);
                        for (Entity en : Locations.get(i).getChunk().getEntities())
                        {
                            if (en.getLocation().distance(Locations.get(i)) < 1.5)
                            {
                                if (en instanceof Damageable)
                                {
                                    ((Damageable) en).damage(2,p);
                                    p.getWorld().playSound(p.getLocation(), Sound.SUCCESSFUL_HIT,100,1);
                                }
                            }
                        }
                    }
                }
            }
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
            {
                if (checkCooldown(p.getName(), Explosive, 6))
                {
                    p.getWorld().playEffect(p.getLocation(),Effect.EXPLOSION_HUGE,1);
                    p.getWorld().playSound(p.getLocation(), Sound.EXPLODE,100,1);

                    for (Entity en : p.getNearbyEntities(5,5,5))
                    {
                        if (en.getLocation().distance(p.getLocation()) < 5)
                        {
                            if (en instanceof Damageable)
                            {
                                ((Damageable) en).damage(6,p);

                            }
                        }
                    }
                }
                else
                {
                    double cooldown = 6 - ((double)System.currentTimeMillis()/(double)1000 - Explosive.get(p.getName()));
                    p.sendMessage(ChatColor.RED + "(!) " + ChatColor.DARK_GRAY + "You can use Ability after " + Math.round(cooldown*10.0)/10.0 + " second(s).");
                }
            }
        }
    }
}
