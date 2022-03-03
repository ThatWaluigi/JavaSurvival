package Com.Waluigi.SurvivalGame.Main.Storage;

import Com.Waluigi.SurvivalGame.Main.World.Biome;
import Com.Waluigi.SurvivalGame.Main.World.Spawnable;

public enum BiomeList {
    FOREST ("GrassGround.png", new SpawnableList[]{SpawnableList.ROCK, SpawnableList.TREE,SpawnableList.TREE}),
    QUARRY ("StoneGround.png", new SpawnableList[]{SpawnableList.ROCK,SpawnableList.ROCK,SpawnableList.SHARP_ROCK,SpawnableList.ROCK}),
    MOUNTIAN ("StoneGround.png", new SpawnableList[]{SpawnableList.SHARP_ROCK, SpawnableList.SHARP_ROCK, SpawnableList.SHARP_ROCK, SpawnableList.ROCK, SpawnableList.ROCK});

    public final Biome biome;
    BiomeList(String BgImageFile, SpawnableList[] BiomeObjects) {
        Spawnable[] temp = new Spawnable[BiomeObjects.length];
        int i = 0;
        for (SpawnableList spawn : BiomeObjects){
            temp[i] = spawn.Spawnable;
            i++;
        }
        biome = new Biome(BgImageFile, temp);
    }
}