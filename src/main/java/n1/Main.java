package n1;

//Good video about annotations
//https://www.youtube.com/watch?v=DkZr7_c9ry8

//About serialization:
//https://www.baeldung.com/java-custom-annotation

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

@SuppressWarnings("deprecation")
public class Main {
    public static void main(String[] args) {

        Floristeria floristeria = new Floristeria();

        //TODO: get the persistence from the database

        Menu menu = new Menu();

        menu.addObserver(floristeria);
        menu.displayMenu();
        menu.removeObserver(floristeria);
    }
}

