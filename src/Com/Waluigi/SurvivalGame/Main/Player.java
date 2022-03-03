package Com.Waluigi.SurvivalGame.Main;

import Com.Waluigi.SurvivalGame.Main.Util.RenderSys;

import java.awt.*;

public class Player {
    Image Filename;

    public Player(String file) {
        RenderSys sys = new RenderSys();
        this.Filename = sys.getImage("Com/Waluigi/SurvivalGame/Resources/Images/", file);
    }
}