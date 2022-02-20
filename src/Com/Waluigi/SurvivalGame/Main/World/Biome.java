package Com.Waluigi.SurvivalGame.Main.World;

import Com.Waluigi.SurvivalGame.Main.GamePanel;
import Com.Waluigi.SurvivalGame.Main.Util.RenderingSystem;

import java.awt.*;

public class Biome {
    public Image Background;
    Spawnable[] BiomeObjects;
    public Biome(String bgFile, Spawnable[] Objects){
        RenderingSystem.ImageGetter getter = new RenderingSystem.ImageGetter();
        this.Background = getter.getImage(GamePanel.IMAGE_PATH, bgFile);
        this.BiomeObjects = Objects;
    }
}