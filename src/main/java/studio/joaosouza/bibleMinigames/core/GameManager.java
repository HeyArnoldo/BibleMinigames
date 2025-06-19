package studio.joaosouza.bibleMinigames.core;

import org.bukkit.entity.Player;
import studio.joaosouza.bibleMinigames.minigames.AbstractMinigame;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles running minigames and their lifecycle.
 */
public class GameManager {

    private final Map<String, AbstractMinigame> games = new HashMap<>();

    public void registerGame(AbstractMinigame game) {
        games.put(game.getId().toLowerCase(), game);
    }

    public AbstractMinigame getGame(String id) {
        return games.get(id.toLowerCase());
    }

    public void joinGame(Player player, String id) {
        AbstractMinigame game = games.get(id.toLowerCase());
        if (game != null) {
            game.addPlayer(player);
            if (game.getState() == studio.joaosouza.bibleMinigames.minigames.GameState.WAITING) {
                game.start();
            }
        }
    }
}
