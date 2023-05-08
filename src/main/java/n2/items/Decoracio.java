package n2.items;


import n2.enums.ItemType;
import n2.enums.Material;

public class Decoracio  extends Item {

    Material material;

    public String getMaterial() {
        return material.toString();
    }

    public Decoracio(String nom, int preu, Material material) {
        super(nom, preu);
        this.material = material;
    }

    @Override
    public void printItem(int index) {
        System.out.printf("%-10d %-10s %-10d %-10s \n",index, nom, preu, "Material: " + material.toString());
    }
}
