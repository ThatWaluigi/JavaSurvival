package Com.Waluigi.SurvivalGame.Main.World;

import Com.Waluigi.SurvivalGame.Main.Inventory.Item;
import Com.Waluigi.SurvivalGame.Main.Tools.ToolTypes;
import Com.Waluigi.SurvivalGame.Main.Util.RenderingSystem;

import java.awt.*;

public class Spawnable {
    public Image ImageFile;
    public boolean PlayerCollidable;
    public Item[] Drop;
    public ToolTypes neededTool;

    public Spawnable(String filename, Item[] drops, boolean collidable, ToolTypes needed) {
        RenderingSystem.ImageGetter image = new RenderingSystem.ImageGetter();
        this.ImageFile = image.getImage("Com/Waluigi/SurvivalGame/Resources/Images/", filename);
        this.PlayerCollidable = collidable;
        this.Drop = drops;
        this.neededTool = needed;
    }

    public Spawnable(String filename, Item drop, boolean collidable, ToolTypes needed) {
        RenderingSystem.ImageGetter image = new RenderingSystem.ImageGetter();
        this.ImageFile = image.getImage("Com/Waluigi/SurvivalGame/Resources/Images/", filename);
        this.PlayerCollidable = collidable;
        this.Drop = new Item[]{drop};
        this.neededTool = needed;
    }
}