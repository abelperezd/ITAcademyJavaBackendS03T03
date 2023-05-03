package n1.items;

import n1.enums.ItemType;

public abstract class Item {
    protected ItemType type;
    protected String nom;
    protected float preu;

    public Item(String nom, float preu) {
        this.nom = nom;
        this.preu = preu;
    }

    public float getPreu() {
        return preu;
    }

    public abstract void printItem(int index);
}
