package n1.items;

public class Arbre extends Item {
    private float alcada;

    public Arbre(String nom, float preu, float alcada) {
        super(nom, preu);
        this.alcada = alcada;
    }

    @Override
    public void printItem() {
        System.out.printf("%-10s %-10f %-10s \n", nom, preu, "Alç: " + alcada);
    }

}
