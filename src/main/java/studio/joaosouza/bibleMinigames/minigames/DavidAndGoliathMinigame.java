package studio.joaosouza.bibleMinigames.minigames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Giant;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitTask;
import studio.joaosouza.bibleMinigames.BibleMinigames;

/**
 * Simplified implementation of the David vs Goliath minigame.
 */
public class DavidAndGoliathMinigame extends AbstractMinigame {

    private final BibleMinigames plugin;
    private Giant goliath;
    private BukkitTask task;

    public DavidAndGoliathMinigame(BibleMinigames plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getId() {
        return "david_goliath";
    }

    @Override
    public void start() {
        setState(GameState.LIVE);
        for (Player p : getPlayers()) {
            p.sendMessage(ChatColor.GOLD + "¡Derrota a Goliat!");
        }
        // Spawn a simple giant as Goliath
        goliath = (Giant) getPlayers().iterator().next().getWorld().spawnEntity(getPlayers().iterator().next().getLocation(), EntityType.GIANT);
        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (goliath.isDead()) {
                end();
            }
        }, 20L, 20L);
    }

    @Override
    public void end() {
        setState(GameState.ENDING);
        if (goliath != null && !goliath.isDead()) {
            goliath.remove();
        }
        if (task != null) {
            task.cancel();
        }
        for (Player p : getPlayers()) {
            p.sendMessage(ChatColor.GREEN + "Con fe, el más pequeño puede vencer al más grande.");
        }
        getPlayers().clear();
        setState(GameState.WAITING);
    }
}
