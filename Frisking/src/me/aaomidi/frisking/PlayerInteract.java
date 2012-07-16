package me.aaomidi.frisking;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

class PlayerInteract implements Listener {

    public static HashMap<Player, Location> jail = new HashMap<Player, Location>();
    public static Main plugin;

    @EventHandler
    public void EntityDamgeByEntityEvent(EntityDamageByEntityEvent e) {

        Player player = (Player) e.getDamager();
        if (Main.drug.containsKey(player)) {
            if (player.getItemInHand().getType() == Material.STICK) {
                Player target = (Player) e.getEntity();
                if (target.getInventory().contains(Material.RED_MUSHROOM) || target.getInventory().contains(Material.SUGAR_CANE) || target.getInventory().contains(Material.SUGAR) || target.getInventory().contains(Material.SEEDS) || target.getInventory().contains(Material.CACTUS)) {
                    if (!jail.containsKey(target)) {
                        player.sendMessage(ChatColor.DARK_RED + "DRUG ALERT");
                        target.getInventory().remove(Material.RED_MUSHROOM);
                        target.getInventory().remove(Material.CACTUS);
                        target.getInventory().remove(Material.SEEDS);
                        target.getInventory().remove(Material.SUGAR_CANE);
                        target.getInventory().remove(Material.SUGAR);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take 100 " + target.getName());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nick " + target.getName() + " " + target.getName() + "&4X");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + target.getName() + " was caught with drugs for the first time by " + player.getName() + " !");
                        jail.put(target, null);
                        target.sendMessage(ChatColor.DARK_RED + "You were caught with drugs 1/2");
                        e.setDamage(0);
                    } else if (jail.containsKey(target)) {
                        player.sendMessage(ChatColor.DARK_RED + "DRUG ALERT");
                        target.getInventory().remove(Material.RED_MUSHROOM);
                        target.getInventory().remove(Material.CACTUS);
                        target.getInventory().remove(Material.SEEDS);
                        target.getInventory().remove(Material.SUGAR_CANE);
                        target.getInventory().remove(Material.SUGAR);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + target.getName() + " 200");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nick " + target.getName() + " off");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "manuadd " + target.getName() + " prisoner" + " prison2");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say " + target.getName() + " was caught with drugs for the second time by " + player.getName() + " and sent into the jail!");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + target.getName());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "ci " + target.getName());
                        target.sendMessage(ChatColor.DARK_RED + "You were caught with drugs 2/2 JAIL TIME!");
                        jail.remove(target);
                        ItemStack armor = new ItemStack(Material.AIR);
                        target.getInventory().setHelmet(armor);
                        target.getInventory().setChestplate(armor);
                        target.getInventory().setLeggings(armor);
                        target.getInventory().setBoots(armor);
                    }
                } else {
                    player.sendMessage(ChatColor.BLUE + target.getName() + " is clean :)");
                }
            }
        }
    }
}