package Com.Waluigi.SurvivalGame.Main;

import Com.Waluigi.SurvivalGame.Main.Util.RenderingSystem;

import java.awt.*;

public class Player {
    Image Filename;

    public Player(String file) {
        RenderingSystem.ImageGetter image = new RenderingSystem.ImageGetter();
        this.Filename = image.getImage("Com/Waluigi/SurvivalGame/Resources/Images/", file);
    }
}