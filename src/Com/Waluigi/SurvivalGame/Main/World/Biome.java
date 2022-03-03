package Com.Waluigi.SurvivalGame.Main.World;

import Com.Waluigi.SurvivalGame.Main.GamePanel;
import Com.Waluigi.SurvivalGame.Main.Util.RenderSys;

import java.awt.*;

public class Biome {
    public Image Background;
    Spawnable[] BiomeObjects;
    public Biome(String bgFile, Spawnable[] Objects){
        RenderSys sys = new RenderSys();
        this.Background = sys.getImage(GamePanel.IMAGE_PATH, bgFile);
        this.BiomeObjects = Objects;
    }
}