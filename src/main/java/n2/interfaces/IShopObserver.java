package n2.interfaces;

import n2.items.Item;
import n2.ShoppingList;

public interface IShopObserver {
     void showStock();
     void purchaseFinished(ShoppingList list);
     void showTickets();
     void showIncomes();
     void addItem(Item i);
     void removeItem(int i);
}