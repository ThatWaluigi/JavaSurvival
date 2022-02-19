package Com.Waluigi.SurvivalGame.Main.Storage;

import Com.Waluigi.SurvivalGame.Main.GamePanel;
import Com.Waluigi.SurvivalGame.Main.Inventory.Item;
import Com.Waluigi.SurvivalGame.Main.Util.RenderingSystem;
import Com.Waluigi.SurvivalGame.Main.World.Biome;
import Com.Waluigi.SurvivalGame.Main.World.Spawnable;

import java.awt.*;
import java.util.ArrayList;

public enum BiomeList {
    FOREST ("DirtGround.png",
            new SpawnableList[]{SpawnableList.ROCK, SpawnableList.TREE,SpawnableList.TREE}
    );

    public Biome biome;
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