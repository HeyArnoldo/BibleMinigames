package studio.joaosouza.bibleMinigames.player;

import java.util.UUID;

/**
 * Data container for player progress.
 */
public class PlayerProfile {
    private final UUID uuid;
    private String lastKnownName;
    private int faithPoints;

    public PlayerProfile(UUID uuid, String name) {
        this.uuid = uuid;
        this.lastKnownName = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getLastKnownName() {
        return lastKnownName;
    }

    public void setLastKnownName(String lastKnownName) {
        this.lastKnownName = lastKnownName;
    }

    public int getFaithPoints() {
        return faithPoints;
    }

    public void addFaithPoints(int amount) {
        faithPoints += amount;
    }
}
