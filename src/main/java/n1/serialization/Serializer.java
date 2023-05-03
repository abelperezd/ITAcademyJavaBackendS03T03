package n1.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import n1.Floristeria;
import n1.items.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

//with the help of chatGPT

public class Serializer {
    private static final String path = "src/main/resources/";
    private static final String fileName = "floristeria.json";
    private static Gson gson = new Gson();

    public static Floristeria loadFloristeria() {
        InputStream inputStream = Serializer.class.getResourceAsStream("/" + fileName);

        try {
            if (inputStream != null) {
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                String json = new String(buffer);

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Item.class, new ItemDeserializer())
                        .create();

                Floristeria floristeria = gson.fromJson(json, Floristeria.class);

                System.out.println("Floristeria loaded!");
                return floristeria;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Floristeria();
    }

    public static void saveFloristeria(Floristeria floristeria) {
        try (FileWriter writer = new FileWriter(path + fileName)) {
            gson.toJson(floristeria, writer);
            System.out.println("JSON file created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
