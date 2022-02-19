package Com.Waluigi.SurvivalGame.Main.World;

import java.awt.*;

public class Biome {
    Image Background;
    Spawnable[] BiomeObjects;
    public Biome(Image bg, Spawnable[] Objects){
        this.Background = bg;
        this.BiomeObjects = Objects;
    }
}