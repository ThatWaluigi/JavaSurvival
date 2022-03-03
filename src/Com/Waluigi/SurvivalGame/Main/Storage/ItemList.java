package Com.Waluigi.SurvivalGame.Main.Storage;

import Com.Waluigi.SurvivalGame.Main.GamePanel;
import Com.Waluigi.SurvivalGame.Main.Inventory.Item;
import Com.Waluigi.SurvivalGame.Main.Util.RenderSys;

public enum ItemList {
    STONE ("Stone", "Rock.png"),
    WOOD ("Wood", "Tree.png"),
    FLINT ("Flint", "SharpRock.png");

    public final Item item;
    ItemList(String ItemName, String Image) {
        RenderSys sys = new RenderSys();
        this.item = new Item(ItemName, sys.getImage(GamePanel.IMAGE_PATH, Image));
    }
}