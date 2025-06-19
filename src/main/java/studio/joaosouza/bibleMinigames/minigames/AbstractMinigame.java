package studio.joaosouza.bibleMinigames.minigames;

import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic structure for all minigames.
 */
public abstract class AbstractMinigame {

    private final Set<Player> players = new HashSet<>();
    private GameState state = GameState.WAITING;

    /**
     * @return unique identifier for the game
     */
    public abstract String getId();

    public GameState getState() {
        return state;
    }

    protected void setState(GameState state) {
        this.state = state;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Called when the game should start.
     */
    public abstract void start();

    /**
     * Called when the game should end.
     */
    public abstract void end();
}
