package Com.Waluigi.SurvivalGame.Main.Storage;

import Com.Waluigi.SurvivalGame.Main.GamePanel;
import Com.Waluigi.SurvivalGame.Main.Inventory.Item;
import Com.Waluigi.SurvivalGame.Main.Util.RenderingSystem;

public enum ItemList {
    STONE ("Stone", "Rock.png"),
    WOOD ("Wood", "Tree.png"),
    FLINT ("Flint", "SharpRock.png");

    public final Item item;
    ItemList(String ItemName, String Image) {
        RenderingSystem.ImageGetter getter = new RenderingSystem.ImageGetter();
        this.item = new Item(ItemName, getter.getImage(GamePanel.IMAGE_PATH, Image));
    }
}