package n1;

import n1.interfaces.IShopObserver;
import n1.items.Item;

import java.util.LinkedList;

public class Floristeria implements IShopObserver {
    private LinkedList<Item> items = new LinkedList<>();
    private Tiquets tiquets = new Tiquets();
    private float incomes = 0;

    @Override
    public void showStock() {
        float value = 0;
        for (Item i : items) {
            value += i.getPreu();
            i.printItem();
        }
        System.out.println("Stock: " + items.size());
        System.out.println("Value: " + value);
    }

    @Override
    public void purchaseFinished(ShoppingList list) {
        LinkedList<Item> itemsBought = new LinkedList<>();
        Tiquet tiquet = new Tiquet();

        for (Integer ind : list.getItemsToBuy()) {
            itemsBought.add(items.get(ind));
        }

        for (Item item : itemsBought) {
            incomes += item.getPreu();
            tiquet.addProduct(item);
            tiquets.addTiquet(tiquet);
            items.remove(item);
        }
    }

    @Override
    public void showTickets() {
        tiquets.showTickets();
    }

    @Override
    public void showIncomes() {
        System.out.println("Ingressos: " + incomes);
    }

    @Override
    public void addItem(Item it) {
        items.add(it);
    }

    @Override
    public void removeItem(int pos) {
        if (pos > items.size() - 1)
            return;
        items.remove(pos);
    }

}
