package Com.Waluigi.SurvivalGame.Main.Tools;

import Com.Waluigi.SurvivalGame.Main.GamePanel;
import Com.Waluigi.SurvivalGame.Main.Inventory.Item;
import Com.Waluigi.SurvivalGame.Main.Util.RenderingSystem;
import Com.Waluigi.SurvivalGame.Main.World.Spawnable;

import java.awt.*;
import java.util.Random;

public class Tool {
    public int MiningLevel;
    public ToolTypes ToolType;
    public int Durability;
    public int CurrentDurability;
    public Image ToolImage;

    public Tool(int MLevel, int Durability, ToolTypes type, String[] ImageFiles) {
        RenderingSystem.DrawSystem draw = new RenderingSystem.DrawSystem();
        this.MiningLevel = MLevel;
        this.Durability = Durability;
        this.CurrentDurability = Durability;
        this.ToolType = type;
        this.ToolImage = draw.StackImages(ImageFiles);
    }

    public void UseTool(GamePanel gamePanel, int X, int Y) {
        Spawnable[][] map = gamePanel.CurrentChunk.ChunkObjects;
        Random random = new Random();
        if (X >= 0 && Y >= 0 && X < 12 && Y < 12) {
            Spawnable Object = map[X][Y];
            if (Object != null) {
                Item drop = Object.Drop[random.nextInt(Object.Drop.length)];
                if (ToolType == Object.neededTool) {
                    if (gamePanel.Inventory.contains(drop)) {
                        int temp = gamePanel.Inventory.indexOf(drop);
                        gamePanel.Inventory.get(temp).Amount += 1;
                    } else if (gamePanel.Inventory.size() < gamePanel.SLOTS) {
                        gamePanel.Inventory.add(drop);
                    }
                    gamePanel.CurrentChunk.ChunkObjects[X][Y] = null;
                }
            }
        }

    }

    public void draw(Graphics g, int x, int y, int Scale) {
        RenderingSystem.DrawSystem draw = new RenderingSystem.DrawSystem();
        draw.DrawCenteredImage(g, x, y, this.ToolImage, Scale);
    }
}