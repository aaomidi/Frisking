package me.aaomidi.frisking;

import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static final Logger log = Bukkit.getLogger();
    public static Main plugin;
    public final PlayerInteract pi = new PlayerInteract();
    public static HashMap<Player, Location> drug = new HashMap<Player, Location>();
    public ItemStack wand = (ItemStack) new ItemStack(Material.STICK, 1);

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        getServer().getConsoleSender().sendMessage(String.format("%1$s[%2$s] **%2$s version %3$s has been disabled.**", ChatColor.RED, pdfFile.getName(), pdfFile.getVersion()));
    }

    @Override
    public void onEnable() {
        PluginDescriptionFile pdfFile = getDescription();
        getServer().getConsoleSender().sendMessage(String.format("%1$s[%2$s] **%2$s version %3$s has been enabled.**", ChatColor.GREEN, pdfFile.getName(), pdfFile.getVersion()));
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.pi, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PluginDescriptionFile pdfFile = getDescription();
            if (commandLabel.equalsIgnoreCase("check")) {
                if (player.hasPermission("frisking.check") || player.isOp()) {
                    if (!(drug.containsKey(player))) {
                        player.sendMessage(ChatColor.AQUA + "Drug checker activated!");
                        drug.put(player, null);
                        player.setItemInHand(wand);
                    } else {
                        drug.remove(player);
                        player.sendMessage(ChatColor.RED + "Drug checker deactivated!");
                        player.getInventory().remove(Material.STICK);
                    }
                }
            } else {
                player.sendMessage(ChatColor.DARK_RED + "You do not have the required permissions!");
            }
        }
        return false;
    }
}