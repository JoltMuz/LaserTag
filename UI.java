import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;


public class UI implements Listener
{
    public static ItemStack LongRangeSelect;
    public static ItemStack ExplosiveSelect;
    public static ItemStack BoostSelect;
    public static Inventory AbilityUI = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Choose Your Ability");
    public static ItemStack LongRangeGun;
    public static ItemStack ExplosiveGun;
    public static ItemStack BoostGun;

    static
    {
        LongRangeSelect = new ItemStack(Material.INK_SACK, 1, DyeColor.BLUE.getData());
        ItemMeta LongRangeSelectMeta = LongRangeSelect.getItemMeta();
        LongRangeSelectMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Long Range");
        ArrayList<String> LongRangeSelectLore = new ArrayList<>();
        LongRangeSelectLore.add(ChatColor.YELLOW + "Shoot a laser with increased range");
        LongRangeSelectLore.add(ChatColor.YELLOW + "of 20 blocks.");
        LongRangeSelectLore.add(ChatColor.DARK_AQUA + "Cooldown: " + ChatColor.AQUA + "4 seconds");
        LongRangeSelectMeta.setLore(LongRangeSelectLore);
        LongRangeSelect.setItemMeta(LongRangeSelectMeta);

        ExplosiveSelect = new ItemStack(Material.INK_SACK, 1, DyeColor.ORANGE.getData());
        ItemMeta ExplosiveSelectMeta = ExplosiveSelect.getItemMeta();
        ExplosiveSelectMeta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Explosive");
        ArrayList<String> ExplosiveSelectLore = new ArrayList<>();
        ExplosiveSelectLore.add(ChatColor.RED + "Create an explosion of 5 block radius");
        ExplosiveSelectLore.add(ChatColor.RED + "at your location.");
        ExplosiveSelectLore.add(ChatColor.DARK_AQUA + "Cooldown: " + ChatColor.AQUA + "6 seconds");
        ExplosiveSelectMeta.setLore(ExplosiveSelectLore);
        ExplosiveSelect.setItemMeta(ExplosiveSelectMeta);

        BoostSelect = new ItemStack(Material.INK_SACK, 1, DyeColor.MAGENTA.getData());
        ItemMeta BoostSelectMeta = BoostSelect.getItemMeta();
        BoostSelectMeta.setDisplayName(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Boost");
        ArrayList<String> BoostSelectLore = new ArrayList<>();
        BoostSelectLore.add(ChatColor.GREEN + "Boost yourself in the direction you");
        BoostSelectLore.add(ChatColor.GREEN + "are looking");
        BoostSelectLore.add(ChatColor.DARK_AQUA + "Cooldown: " + ChatColor.AQUA + "5 seconds");
        BoostSelectMeta.setLore(BoostSelectLore);
        BoostSelect.setItemMeta(BoostSelectMeta);

        AbilityUI.setItem(2, LongRangeSelect);
        AbilityUI.setItem(4, ExplosiveSelect);
        AbilityUI.setItem(6, BoostSelect);
        
        LongRangeGun = new ItemStack(Material.IRON_HOE);
        ItemMeta LongRangeGunMeta = LongRangeGun.getItemMeta();
        LongRangeGunMeta.setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Laser Gun");
        ArrayList<String> LongRangeGunLore = new ArrayList<>();
        LongRangeGunLore.add(ChatColor.GOLD + "Left-Click: " + ChatColor.YELLOW +"Shoot" );
        LongRangeGunLore.add(ChatColor.GOLD + "Right-Click: " + ChatColor.YELLOW +"Ability (Long Range)" );
        LongRangeGunLore.add(ChatColor.DARK_AQUA + "Cooldown: " + ChatColor.AQUA + "4 seconds");;
        LongRangeGunMeta.setLore(LongRangeGunLore);
        LongRangeGun.setItemMeta(LongRangeGunMeta);

        ExplosiveGun = new ItemStack(Material.IRON_HOE);
        ItemMeta ExplosiveGunMeta = ExplosiveGun.getItemMeta();
        ExplosiveGunMeta.setDisplayName(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Laser Gun");
        ArrayList<String> ExplosiveGunLore = new ArrayList<>();
        ExplosiveGunLore.add(ChatColor.DARK_RED + "Left-Click: " + ChatColor.RED +"Shoot");
        ExplosiveGunLore.add(ChatColor.DARK_RED+ "Right-Click: " + ChatColor.RED +"Ability (Explosive)");
        ExplosiveGunLore.add(ChatColor.DARK_AQUA + "Cooldown: " + ChatColor.AQUA + "6 seconds");
        ExplosiveGunMeta.setLore(ExplosiveGunLore);
        ExplosiveGun.setItemMeta(ExplosiveGunMeta);

        BoostGun = new ItemStack(Material.IRON_HOE);
        ItemMeta BoostGunMeta = BoostGun.getItemMeta();
        BoostGunMeta.setDisplayName(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Laser Gun");
        ArrayList<String> BoostGunLore = new ArrayList<>();
        BoostGunLore.add(ChatColor.DARK_GREEN + "Left-Click: " + ChatColor.GREEN +"Shoot");
        BoostGunLore.add(ChatColor.DARK_GREEN + "Right-Click: " + ChatColor.GREEN +"Ability (Boost)");
        BoostGunLore.add(ChatColor.DARK_AQUA + "Cooldown: " + ChatColor.AQUA + "5 seconds");
        BoostGunMeta.setLore(BoostGunLore);
        BoostGun.setItemMeta(BoostGunMeta);

    }
    @EventHandler
    public void AbilityUI(PlayerLevelChangeEvent e)
    {
        if (e.getNewLevel() > 0)
        {
            e.getPlayer().openInventory(AbilityUI);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player p = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        if (inventory.getName().equals(AbilityUI.getName()))
        {
            event.setCancelled(true);

            if (clicked.hasItemMeta())
            {
                if (clicked.getItemMeta().getDisplayName().equals(ChatColor.GOLD + ChatColor.BOLD.toString() + "Long Range"))
                {
                    p.getInventory().addItem(LongRangeGun);
                    p.closeInventory();
                    Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has chosen " + ChatColor.GOLD + "Long Range"  + ChatColor.YELLOW + " Ability!");
                }
                if (clicked.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "Explosive"))
                {
                    p.getInventory().addItem(ExplosiveGun);
                    p.closeInventory();
                    Bukkit.broadcastMessage(ChatColor.DARK_RED + p.getName() + ChatColor.RED + " has chosen " + ChatColor.DARK_RED + "Explosive"  + ChatColor.RED + " Ability!");
                }
                if (clicked.getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Boost"))
                {
                    p.getInventory().addItem(BoostGun);
                    p.closeInventory();
                    Bukkit.broadcastMessage(ChatColor.DARK_GREEN + p.getName() + ChatColor.GREEN + " has chosen " + ChatColor.DARK_GREEN + "Boost"  + ChatColor.GREEN + " Ability!");
                }
            }
        }
    }
}
