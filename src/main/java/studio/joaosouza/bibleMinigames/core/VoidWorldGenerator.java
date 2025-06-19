package studio.joaosouza.bibleMinigames.core;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.ChunkGenerator.ChunkData;
import org.bukkit.generator.ChunkGenerator.BiomeGrid;

import java.util.Random;

/**
 * Simple generator that creates an empty world.
 */
public class VoidWorldGenerator extends ChunkGenerator {
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        return createChunkData(world);
    }
}
