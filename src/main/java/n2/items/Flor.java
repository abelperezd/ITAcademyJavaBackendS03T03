package n2.items;

import n2.enums.ItemType;

public class Flor extends Item {

    String color;

    public String getColor() {
        return color;
    }

    public Flor(String nom, int preu, String color) {
        super(nom, preu);
        this.color = color;
    }

    @Override
    public void printItem(int index) {
        System.out.printf("%-10d %-10s %-10d %-10s \n", index, nom, preu, "Color: " + color);
    }
}
