package n1.items;

public class Flor extends Item {

    String color;

    public Flor(String nom, float preu, String color) {
        super(nom, preu);
        this.color = color;
    }

    @Override
    public void printItem() {
        System.out.printf("%-10s %-10f %-10s \n", nom, preu, "Col: " + color);
    }
}
