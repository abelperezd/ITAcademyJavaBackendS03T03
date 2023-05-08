package n2;

import n2.enums.Material;
import n2.interfaces.IShopObservable;
import n2.interfaces.IShopObserver;
import n2.items.Arbre;
import n2.items.Decoracio;
import n2.items.Flor;
import n2.items.Item;

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

        System.out.println("\n---BUY---\n");

        ShoppingList list = new ShoppingList();
        showStock();

        int valueRead = 0;
        while (valueRead != -1) {
            System.out.println("Insereix l'index de l'item a comprar (-1 per finalitzar): ");
            valueRead = scanner.nextInt();
            if (valueRead != -1)
                list.addItemToBuy(valueRead);
        }

        if (list.getItemsToBuy().size() > 0) {
            purchaseFinished(list);
            System.out.println("Compra realitzada correctament");
        }
        else{
            System.out.println("Compra cancel·lada");
        }
        System.out.println("");
    }

    //region Add items

    private String getName() {
        System.out.println("Nom: ");
        return scanner.nextLine();
    }

    private int getPrice() {
        System.out.println("Preu: ");
        int preu = scanner.nextInt();
        scanner.nextLine();
        return preu;
    }

    private void addArbre() {
        String name = getName();
        int preu = getPrice();
        System.out.println("Alçada: ");
        int alcada = scanner.nextInt();
        scanner.nextLine();

        addItem(new Arbre(name, preu, alcada));
        System.out.println("");
    }

    private void addFlor() {
        String name = getName();
        int preu = getPrice();

        System.out.println("Color: ");
        String color = scanner.nextLine();

        addItem(new Flor(name, preu, color));
        System.out.println("");
    }

    private void addDecoracio() {
        String name = getName();
        int preu = getPrice();

        System.out.println("Material (0: fusta / 1:plastic): ");
        int materialIndex = scanner.nextInt();
        scanner.nextLine();

        addItem(new Decoracio(name, preu, Material.values()[materialIndex]));
        System.out.println("");
    }

    //endregion

    void deleteItem() {
        System.out.println("\n---ELIMINAR ITEM---\n");

        showStock();

        System.out.println("Index de l'element a eliminar: ");
        int ind = scanner.nextInt();
        scanner.nextLine();

        removeItem(ind);

        System.out.println("");
    }

}
