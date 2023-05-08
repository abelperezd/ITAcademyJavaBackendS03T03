package n2.interfaces;

import n2.items.Item;
import n2.ShoppingList;

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