package n2.tiquets;

import java.util.LinkedList;

public class Tiquets {
    LinkedList<Tiquet> tiquets = new LinkedList<>();

    public void addTiquet(Tiquet tiquet) {
        tiquets.add(tiquet);
    }

    public void showTickets() {
        for (int i = 0; i < tiquets.size(); i++){
            System.out.println("Tiquet #" + i );
            tiquets.get(i).showProducts();
            System.out.println("");
        }
    }

    public int getTiquetsAmount(){
        return tiquets.size();
    }

}
