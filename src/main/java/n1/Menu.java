package n1;

import n1.interfaces.IShopObservable;
import n1.interfaces.IShopObserver;
import n1.items.Arbre;
import n1.items.Decoracio;
import n1.items.Flor;
import n1.items.Item;

import java.util.LinkedList;
import java.util.Scanner;

public class Menu implements IShopObservable {
    private Scanner scanner = new Scanner(System.in);
    private LinkedList<IShopObserver> observers = new LinkedList<>();

    public void displayMenu() {
        int ans = -1;

        while (ans != 0) {
            System.out.println("1. Veure stock");
            System.out.println("2. Comprar");
            System.out.println("3. Mostrar tickets");
            System.out.println("4. Mostrar ingressos");
            System.out.println("5. Afegir arbre");
            System.out.println("6. Afegir flor");
            System.out.println("7. Afegir decoració");
            System.out.println("8. Eliminar item");
            System.out.println("0. Surt");

            ans = scanner.nextInt();
            scanner.nextLine();

            switch (ans) {
                case 1:
                    showStock();
                    break;
                case 2:
                    buy();
                    break;
                case 3:
                    showTickets();
                    break;
                case 4:
                    showIncomes();
                    break;
                case 5:
                    addArbre();
                    break;
                case 6:
                    addFlor();
                    break;
                case 7:
                    addDecoracio();
                    break;
                case 8:
                    deleteItem();
                    break;
            }
        }
    }

    //region Observable

    @Override
    public void showStock() {
        observers.forEach(o -> o.showStock());

    }
    @Override
    public void purchaseFinished(ShoppingList list) {

        observers.forEach(o -> o.purchaseFinished(list));
    }

    @Override
    public void showTickets() {
        observers.forEach(o -> o.showTickets());
    }

    @Override
    public void showIncomes() {
        observers.forEach(o -> o.showIncomes());
    }

    @Override
    public void addItem(Item i) {
        observers.forEach(o -> o.addItem(i));
    }

    @Override
    public void removeItem(int i) {
        observers.forEach(o -> o.removeItem(i));
    }

    @Override
    public void addObserver(IShopObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IShopObserver o) {
        observers.remove(o);

    }

    //endregion

    private void buy() {

        ShoppingList list = new ShoppingList();
        showStock();

        int valueRead = 0;
        while (valueRead != -1) {
            System.out.println("Insereix l'index de l'item a comprar (-1 per finalitzar): ");
            valueRead = scanner.nextInt();
            if (valueRead != -1)
                list.addItemToBuy(valueRead);
        }
        purchaseFinished(list);

        System.out.println("Compra realitzada correctament");
    }

    //region Add items

    private String getName() {
        System.out.println("Nom: ");
        return scanner.nextLine();
    }

    private float getPrice() {
        System.out.println("Preu: ");
        float preu = scanner.nextFloat();
        scanner.nextLine();
        return preu;
    }

    private void addArbre() {
        String name = getName();
        float preu = getPrice();
        System.out.println("Alçaca: ");
        float alcada = scanner.nextFloat();
        scanner.nextLine();

        addItem(new Arbre(name, preu, alcada));
    }

    private void addFlor() {
        String name = getName();
        float preu = getPrice();

        System.out.println("Color: ");
        String color = scanner.nextLine();

        addItem(new Flor(name, preu, color));
    }

    private void addDecoracio() {
        String name = getName();
        float preu = getPrice();

        System.out.println("Material (0: fusta/ 1:plastic): ");
        int materialIndex = scanner.nextInt();
        scanner.nextLine();

        addItem(new Decoracio(name, preu, Material.values()[materialIndex]));
    }

    //endregion

    void deleteItem() {
        showStock();

        System.out.println("Index de l'element a eliminar: ");
        int ind = scanner.nextInt();
        scanner.nextLine();

        removeItem(ind);
    }

}
