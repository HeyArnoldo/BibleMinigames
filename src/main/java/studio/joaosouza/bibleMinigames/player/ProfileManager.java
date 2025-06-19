package studio.joaosouza.bibleMinigames.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages the profiles of online players.
 */
public class ProfileManager {

    private final Map<UUID, PlayerProfile> profiles = new HashMap<>();

    public PlayerProfile getProfile(Player player) {
        return profiles.computeIfAbsent(player.getUniqueId(), uuid -> new PlayerProfile(uuid, player.getName()));
    }
}
