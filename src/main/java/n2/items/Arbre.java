package n2.items;

import n2.enums.ItemType;

public class Arbre extends Item {

    private int alcada;

    public int getAlcada() {
        return alcada;
    }

    public Arbre(String nom, int preu, int alcada) {
        super(nom, preu);
        this.alcada = alcada;
    }

    @Override
    public void printItem(int index) {
        System.out.printf("%-10d %-10s %-10d %-10s \n", index, nom, preu, "Alçada: " + alcada);
    }

}
