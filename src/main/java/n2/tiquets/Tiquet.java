package n2.tiquets;

import n2.items.Item;

import java.util.LinkedList;

public class Tiquet {

    private LinkedList<Item> products = new LinkedList<>();

    public void addProduct(Item item) {
        products.add(item);
    }

    public void showProducts() {
        System.out.printf("%-10s %-10s %-10s \n", "NOM", "PREU", "INF. EXTRA");
        int ind = 0;
        for (Item product : products) {
            product.printItem(ind++);
        }
    }
}
