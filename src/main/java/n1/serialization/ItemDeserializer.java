package n1.serialization;

import com.google.gson.*;
import n1.enums.Material;
import n1.items.*;

import java.lang.reflect.Type;

//with the help of chatGPT

public class ItemDeserializer implements JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String itemType = jsonObject.get("type").getAsString();

        if (itemType.equals("arbre")) {
            String nom = jsonObject.get("nom").getAsString();
            float preu = jsonObject.get("preu").getAsFloat();
            float alcada = jsonObject.get("alcada").getAsFloat();
            return new Arbre(nom, preu, alcada);
        } else if (itemType.equals("flor")) {
            String nom = jsonObject.get("nom").getAsString();
            float preu = jsonObject.get("preu").getAsFloat();
            String color = jsonObject.get("color").getAsString();
            return new Flor(nom, preu, color);
        } else if (itemType.equals("decoracio")) {
            String nom = jsonObject.get("nom").getAsString();
            float preu = jsonObject.get("preu").getAsFloat();
            Material material = Material.valueOf(jsonObject.get("material").getAsString());
            return new Decoracio(nom, preu, material);
        } else {
            throw new JsonParseException("Unknown item type: " + itemType);
        }
    }
}

