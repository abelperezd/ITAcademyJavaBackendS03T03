package n1.items;


import n1.Material;

public class Decoracio  extends Item {

    Material material;

    public Decoracio(String nom, float preu, Material material) {
        super(nom, preu);
        this.material = material;
    }

    @Override
    public void printItem() {
        System.out.printf("%-10s %-10f %-10s \n", nom, preu, "Mat: " + material.toString());
    }
}
