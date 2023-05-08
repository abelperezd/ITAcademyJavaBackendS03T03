package n2;

import java.util.LinkedList;

public class ShoppingList {
    public LinkedList<Integer> getItemsToBuy() {
        return itemsToBuy;
    }

    private LinkedList<Integer> itemsToBuy = new LinkedList<>();

    public void addItemToBuy(int i) {
        if (!itemsToBuy.contains(i))
            itemsToBuy.add(i);
    }
}
