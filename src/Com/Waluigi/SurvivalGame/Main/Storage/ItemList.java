package Com.Waluigi.SurvivalGame.Main.Storage;

import Com.Waluigi.SurvivalGame.Main.Inventory.Item;

public enum ItemList {
    STONE ("Stone"),
    WOOD ("Wood"),
    FLINT ("Flint");

    public final Item item;
    ItemList(String ItemName) {
        this.item = new Item(ItemName);
    }
}