package n1.items;

import n1.enums.ItemType;

public class Arbre extends Item {

    private float alcada;

    public Arbre(String nom, float preu, float alcada) {
        super(nom, preu);
        this.alcada = alcada;
        this.type = ItemType.arbre;
    }

    @Override
    public void printItem(int index) {
        System.out.printf("%-10d %-10s %-10.2f %-10s \n", index, nom, preu, "Alçada: " + alcada);
    }

}
