package studio.joaosouza.bibleMinigames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import studio.joaosouza.bibleMinigames.core.GameManager;

/**
 * Command for players to interact with minigames.
 */
public class BMCommand implements CommandExecutor {

    private final GameManager gameManager;

    public BMCommand(GameManager manager) {
        this.gameManager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.YELLOW + "/bm join <game>");
            return true;
        }
        if (args[0].equalsIgnoreCase("join") && args.length >= 2) {
            gameManager.joinGame(player, args[1]);
            player.sendMessage(ChatColor.GREEN + "Joined game " + args[1]);
        } else if (args[0].equalsIgnoreCase("leave")) {
            player.sendMessage(ChatColor.RED + "Leaving game is not implemented yet.");
        } else if (args[0].equalsIgnoreCase("list")) {
            player.sendMessage(ChatColor.YELLOW + "Games: " + gameManager.getGame("david_goliath").getId());
        }
        return true;
    }
}
