package studio.joaosouza.bibleMinigames;

import org.bukkit.plugin.java.JavaPlugin;
import studio.joaosouza.bibleMinigames.commands.BMACommand;
import studio.joaosouza.bibleMinigames.commands.BMCommand;
import studio.joaosouza.bibleMinigames.core.ArenaManager;
import studio.joaosouza.bibleMinigames.core.ConfigManager;
import studio.joaosouza.bibleMinigames.core.GameManager;
import studio.joaosouza.bibleMinigames.minigames.DavidAndGoliathMinigame;

/**
 * Main plugin class.
 */
public final class BibleMinigames extends JavaPlugin {

    private ConfigManager configManager;
    private GameManager gameManager;
    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.arenaManager = new ArenaManager(this);
        this.gameManager = new GameManager();

        // register default game
        gameManager.registerGame(new DavidAndGoliathMinigame(this));

        getCommand("bm").setExecutor(new BMCommand(gameManager));
        getCommand("bma").setExecutor(new BMACommand(arenaManager));

        getLogger().info("BibleMinigames enabled");
    }

    @Override
    public void onDisable() {
        arenaManager.save();
        getLogger().info("BibleMinigames disabled");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
