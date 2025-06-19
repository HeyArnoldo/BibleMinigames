package studio.joaosouza.bibleMinigames.core;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Loads and manages arena configurations.
 */
public class ArenaManager {

    private final Set<String> arenas = new HashSet<>();
    private final File arenasFile;
    private final FileConfiguration config;
    private final JavaPlugin plugin;

    public ArenaManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.arenasFile = new File(plugin.getDataFolder(), "arenas.yml");
        if (!arenasFile.exists()) {
            plugin.saveResource("arenas.yml", false);
        }
        this.config = YamlConfiguration.loadConfiguration(arenasFile);
        setupWorld();
        copyDefaultSchematics();
        createDefaultArenaEntries();
        arenas.addAll(config.getConfigurationSection("arenas").getKeys(false));
    }

    public Set<String> getArenas() {
        return arenas;
    }

    public void save() {
        try {
            config.save(arenasFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupWorld() {
        World world = Bukkit.getWorld("bible_minigames_world");
        if (world == null) {
            WorldCreator creator = new WorldCreator("bible_minigames_world");
            creator.generator(new VoidWorldGenerator());
            world = Bukkit.createWorld(creator);
            if (world != null) {
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            }
        }
    }

    private void copyDefaultSchematics() {
        File schemDir = new File(plugin.getDataFolder(), "schematics");
        if (!schemDir.exists()) {
            schemDir.mkdirs();
        }
        File dg = new File(schemDir, "david_goliath_default.schem");
        if (!dg.exists()) {
            plugin.saveResource("schematics/david_goliath_default.schem", false);
        }
    }

    private void createDefaultArenaEntries() {
        String path = "arenas.david_goliath_default";
        if (!config.contains(path)) {
            config.set(path + ".type", "DEFAULT");
            config.set(path + ".game_type", "DAVID_GOLIATH");
            config.set(path + ".enabled", true);
        }
    }
}
