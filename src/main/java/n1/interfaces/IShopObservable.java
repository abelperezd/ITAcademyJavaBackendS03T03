package n1.interfaces;

import n1.items.Item;
import n1.ShoppingList;

public interface IShopObservable {
    void addObserver (IShopObserver o);
    void removeObserver (IShopObserver o);
    void showStock();
    void purchaseFinished(ShoppingList list);
    void showTickets();
    void showIncomes();
    void addItem(Item i);
    void removeItem(int i);
}