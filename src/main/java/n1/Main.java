package n1;

//Good video about annotations
//https://www.youtube.com/watch?v=DkZr7_c9ry8

//About serialization:
//https://www.baeldung.com/java-custom-annotation

import n1.serialization.Serializer;

@SuppressWarnings("deprecation")
public class Main {
    public static void main(String[] args) {

        Floristeria floristeria = Serializer.loadFloristeria();

        //TODO: get the persistence from the database

        Menu menu = new Menu();

        menu.addObserver(floristeria);
        menu.displayMenu();
        menu.removeObserver(floristeria);

        Serializer.saveFloristeria(floristeria);
    }
}

