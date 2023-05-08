package n2;

import n2.DDBB.SQLConnector;
import n2.interfaces.IShopObserver;
import n2.items.Arbre;
import n2.items.Decoracio;
import n2.items.Flor;
import n2.items.Item;
import n2.tiquets.Tiquet;
import n2.tiquets.Tiquets;

import java.util.LinkedList;

public class Floristeria implements IShopObserver {
    private LinkedList<Item> items;
    private Tiquets tiquets;
    private int guanys;

    public Floristeria() {

        guanys = SQLConnector.Instance().getFloristeriaIncomes();

        items = SQLConnector.Instance().getItems();

        tiquets = SQLConnector.Instance().getTickets();
    }

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

        int tiquetId = tiquets.getTiquetsAmount() + 1;

        SQLConnector.Instance().addTicket(tiquetId);

        for (Item item : itemsBought) {
            if (item instanceof Arbre) {
                SQLConnector.Instance().buyArbre((Arbre) item, tiquetId);
            }
            if (item instanceof Flor) {
                SQLConnector.Instance().buyFlor((Flor) item, tiquetId);
            }
            if (item instanceof Decoracio) {
                SQLConnector.Instance().buyDecoracio((Decoracio) item, tiquetId);
            }

            guanys += item.getPreu();
            tiquet.addProduct(item);
            items.remove(item);
        }
        SQLConnector.Instance().updateFloristeriaIncomes(guanys);
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
        System.out.println("Ingressos: " + guanys);
        System.out.println("");
    }

    @Override
    public void addItem(Item item) {
        items.add(item);

        if (item instanceof Arbre) {
            SQLConnector.Instance().addArbre((Arbre) item);
        }
        if (item instanceof Flor) {
            SQLConnector.Instance().addFlor((Flor) item);
        }
        if (item instanceof Decoracio) {
            SQLConnector.Instance().addDecoracio((Decoracio) item);
        }
    }

    @Override
    public void removeItem(int pos) {
        if (pos > items.size() - 1)
            return;

        Item item = items.get(pos);

        if (item instanceof Arbre) {
            SQLConnector.Instance().removeArbre((Arbre) item);
        }
        if (item instanceof Flor) {
            SQLConnector.Instance().removeFlor((Flor) item);
        }
        if (item instanceof Decoracio) {
            SQLConnector.Instance().removeDecoracio((Decoracio) item);
        }

        items.remove(pos);
    }
}
