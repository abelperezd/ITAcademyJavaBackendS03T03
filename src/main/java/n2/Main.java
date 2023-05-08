package n2;

//Good video about annotations
//https://www.youtube.com/watch?v=DkZr7_c9ry8

//About serialization:
//https://www.baeldung.com/java-custom-annotation

import n2.DDBB.SQLConnector;

@SuppressWarnings("deprecation")
public class Main {
    public static void main(String[] args) {

        new SQLConnector();

        Floristeria floristeria = new Floristeria();

        Menu menu = new Menu();

        menu.addObserver(floristeria);
        menu.displayMenu();
        menu.removeObserver(floristeria);

        SQLConnector.Instance().Close();
    }

}

