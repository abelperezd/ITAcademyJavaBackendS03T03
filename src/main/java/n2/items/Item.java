package n2.items;

import n2.enums.ItemType;

public abstract class Item {

    protected String nom;
    protected int preu;

    public Item(String nom, int preu) {
        this.nom = nom;
        this.preu = preu;
    }

    public String getNom() {
        return nom;
    }

    public int getPreu() {
        return preu;
    }
    public abstract void printItem(int index);
}
