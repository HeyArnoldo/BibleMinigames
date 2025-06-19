package studio.joaosouza.bibleMinigames.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import studio.joaosouza.bibleMinigames.core.ArenaManager;

/**
 * Simple admin command handler.
 */
public class BMACommand implements CommandExecutor {

    private final ArenaManager arenaManager;

    public BMACommand(ArenaManager manager) {
        this.arenaManager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.YELLOW + "/bma tool | reload");
            return true;
        }
        if (args[0].equalsIgnoreCase("tool")) {
            player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD));
            player.sendMessage(ChatColor.GREEN + "Varita entregada");
        } else if (args[0].equalsIgnoreCase("reload")) {
            arenaManager.save();
            player.sendMessage(ChatColor.GREEN + "Configuración guardada");
        }
        return true;
    }
}
