package Com.Waluigi.SurvivalGame.Main.Inventory;

import java.awt.*;

public class Item {
    public String Name;
    public Image Icon;
    public int Amount;

    public Item(String name, Image image) {
        this.Name = name;
        this.Icon = image;
        this.Amount = 1;
    }
}