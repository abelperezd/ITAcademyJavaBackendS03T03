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
        System.out.println("\n---STOCK---\n");

        System.out.printf("%-10s %-10s %-10s %-10s \n", "ID", "NOM", "PREU", "OTHER");
        float value = 0;
        int ind = 0;
        for (Item i : items) {
            value += i.getPreu();
            i.printItem(ind++);
        }
        System.out.println("");
        System.out.println("Stock: " + items.size());
        System.out.println("Value: " + value);
        System.out.println("");
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
            items.remove(item);
        }
        tiquets.addTiquet(tiquet);
    }

    @Override
    public void showTickets() {
        System.out.println("\n---TICKETS---\n");

        tiquets.showTickets();

        System.out.println("");
    }

    @Override
    public void showIncomes() {
        System.out.println("");
        System.out.println("Ingressos: " + incomes);
        System.out.println("");
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
