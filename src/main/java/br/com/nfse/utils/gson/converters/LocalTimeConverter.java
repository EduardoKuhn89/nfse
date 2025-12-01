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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class LocalTimeConverter implements JsonDeserializer<LocalTime>, JsonSerializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        if (StringUtils.isNullOrEmpty(json.getAsString())) {
            return null;
        }

        return LocalTime.parse(json.getAsString(), DateTimeFormatter.ISO_TIME);
    }

    @Override
    public JsonElement serialize(LocalTime ldt, Type type, JsonSerializationContext jsc) {
        String formated = "";
        try {
            formated = ldt.format(DateTimeFormatter.ISO_TIME);
        } catch (Exception ex) {
            System.out.println(">>>>>> Erro ao converter LocalTime: " + ldt.toString());
        }
        return new JsonPrimitive(formated);
    }

}
