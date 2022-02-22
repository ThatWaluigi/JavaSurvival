package Com.Waluigi.SurvivalGame.Main.Storage;

import Com.Waluigi.SurvivalGame.Main.Tools.Tool;
import Com.Waluigi.SurvivalGame.Main.Tools.ToolTypes;

public enum ToolList {
    SHOVEL (1, 120, ToolTypes.SHOVEL, new String[]{"ToolHandle.png", "ToolShovelHead.png"}),
    AXE (1, 75, ToolTypes.AXE, new String[]{"ToolHandle.png", "ToolAxeHead.png"}),
    PICKAXE (1, 95, ToolTypes.PICKAXE, new String[]{"ToolHandle.png", "ToolPickaxeHead.png"});

    public final Tool Tool;
    ToolList(int MiningLevel, int Durability, ToolTypes Type, String[] Images) {
        this.Tool = new Tool(MiningLevel, Durability, Type, Images);
    }
}