package n1;

import n1.items.Item;

import java.util.LinkedList;

public class Tiquet {

    private LinkedList<Item> products = new LinkedList<>();

    public void addProduct(Item item) {
        products.add(item);
    }

    public void showProducts() {
        System.out.printf("%-10s %-10s %-10s \n", "NOM", "PREU", "INF. EXTRA");
        products.forEach(p -> p.printItem());
    }


}
