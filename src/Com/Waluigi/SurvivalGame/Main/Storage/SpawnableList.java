package Com.Waluigi.SurvivalGame.Main.Storage;

import Com.Waluigi.SurvivalGame.Main.Inventory.Item;
import Com.Waluigi.SurvivalGame.Main.Tools.ToolTypes;
import Com.Waluigi.SurvivalGame.Main.World.Spawnable;

public enum SpawnableList {
    TREE ("Tree.png", ItemList.WOOD, true, ToolTypes.AXE),
    ROCK ("Rock.png", ItemList.STONE, true, ToolTypes.PICKAXE),
    SHARP_ROCK ("SharpRock.png", new ItemList[]{ItemList.STONE,ItemList.STONE,ItemList.FLINT}, true, ToolTypes.PICKAXE);

    public final Spawnable Spawnable;
    SpawnableList(String ImageFile, ItemList Drop, boolean Collidable, ToolTypes NeededTool) {
        this.Spawnable = new Spawnable(ImageFile, Drop.item, Collidable, NeededTool);
    }

    SpawnableList(String ImageFile, ItemList[] Drops, boolean Collidable, ToolTypes NeededTool) {
        Item[] temp = new Item[Drops.length];
        int i = 0;
        for (ItemList item : Drops){
            temp[i] = item.item;
            i++;
        }
        this.Spawnable = new Spawnable(ImageFile, temp, Collidable, NeededTool);
    }
}