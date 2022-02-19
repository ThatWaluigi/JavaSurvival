package Com.Waluigi.SurvivalGame.Main.World;

import java.util.Random;

public class Chunk {
    public Spawnable[][] ChunkObjects;
    public int X;
    public int Y;

    public Chunk(int MapX, int MapY, int x, int y) {
        this.ChunkObjects = new Spawnable[MapX][MapY];
        this.X = x;
        this.Y = y;
    }

    public void GenerateChunk(Spawnable[] Objects, int Times, int min, int max) {
        Random random = new Random();
        if (Objects != null) {
            for(int i = 0; i < Times; ++i) {
                for(int j = 0; j < random.nextInt(min, max); ++j) {
                    Spawnable obj = Objects[random.nextInt(Objects.length)];
                    int X = random.nextInt(this.ChunkObjects.length);
                    int Y = random.nextInt(this.ChunkObjects[1].length);
                    this.ChunkObjects[X][Y] = obj;
                }
            }
        }

    }
}