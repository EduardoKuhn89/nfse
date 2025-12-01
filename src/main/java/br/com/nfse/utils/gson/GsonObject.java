package br.com.nfse.utils.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonObject {

    private final JsonObject object = new JsonObject();

    public GsonObject add(String name, Object value) {
        if (value == null) {
            object.add(name, null);
        } else if (value instanceof String) {
            object.addProperty(name, (String) value);
        } else if (value instanceof Number) {
            object.addProperty(name, (Number) value);
        } else if (value instanceof Boolean) {
            object.addProperty(name, (Boolean) value);
        } else if (value instanceof Character) {
            object.addProperty(name, (Character) value);
        } else if (value instanceof JsonObject) {
            object.add(name, (JsonObject) value);
        } else if (value instanceof JsonArray) {
            object.add(name, (JsonArray) value);
        } else {
            object.addProperty(name, value.toString());
        }
        return this;
    }

    public JsonObject toJsonObject() {
        return object;
    }

}
