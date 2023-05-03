package n1.items;

import n1.enums.ItemType;

public class Flor extends Item {

    String color;

    public Flor(String nom, float preu, String color) {
        super(nom, preu);
        this.color = color;
        this.type = ItemType.flor;
    }

    @Override
    public void printItem(int index) {
        System.out.printf("%-10d %-10s %-10.2f %-10s \n", index, nom, preu, "Color: " + color);
    }
}
