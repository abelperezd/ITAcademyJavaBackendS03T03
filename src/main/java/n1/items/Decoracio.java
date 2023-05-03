package n1.items;


import n1.enums.ItemType;
import n1.enums.Material;

public class Decoracio  extends Item {

    Material material;

    public Decoracio(String nom, float preu, Material material) {
        super(nom, preu);
        this.material = material;
        this.type = ItemType.decoracio;
    }

    @Override
    public void printItem(int index) {
        System.out.printf("%-10d %-10s %-10.2f %-10s \n",index, nom, preu, "Material: " + material.toString());
    }
}
