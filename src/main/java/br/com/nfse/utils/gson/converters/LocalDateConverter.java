package br.com.nfse.utils.gson.converters;

import br.com.nfse.utils.StringUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        if (StringUtils.isNullOrEmpty(json.getAsString())) {
            return null;
        }
        if (json.getAsString().contains("T")) { // Nos casos que a data bem com Time junto... nesse caso é pego só a data e o time ignorado
            return LocalDate.parse(json.getAsString().split("T")[0]);
        }
        return LocalDate.parse(json.getAsString());
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsc) {

        String formated = "";
        try {
            formated = localDate.format(DateTimeFormatter.ISO_DATE);
        } catch (Exception ex) {
            System.out.println(">>>>>> Erro ao converter LocalDate: " + localDate.toString());
        }
        return new JsonPrimitive(formated);

//        JsonPrimitive jsonPrimitive = new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_DATE));
//        return jsonPrimitive;
    }

}
