package Com.Waluigi.SurvivalGame.Main.World;

import Com.Waluigi.SurvivalGame.Main.GamePanel;

import java.util.Random;

public class Chunk {
    public Biome ChunkBiome;
    public Spawnable[][] ChunkObjects;
    public int X;
    public int Y;

    public Chunk(int x, int y, Biome Biome) {
        this.ChunkObjects = new Spawnable[GamePanel.GRID_X][GamePanel.GRID_Y];
        this.ChunkBiome = Biome;
        this.X = x;
        this.Y = y;
    }

    public void GenerateChunk(int Times, int min, int max) {
        Random random = new Random();
            for(int i = 0; i < Times; ++i) {
                for(int j = 0; j < random.nextInt(min, max); ++j) {
                    Spawnable obj = this.ChunkBiome.BiomeObjects[random.nextInt(this.ChunkBiome.BiomeObjects.length)];
                    int X = random.nextInt(this.ChunkObjects.length);
                    int Y = random.nextInt(this.ChunkObjects[1].length);
                    this.ChunkObjects[X][Y] = obj;
                }
            }
    }
}