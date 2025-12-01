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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        if (StringUtils.isNullOrEmpty(json.getAsString())) {
            return null;
        }

        return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public JsonElement serialize(LocalDateTime ldt, Type type, JsonSerializationContext jsc) {

        String formated = "";
        try {
            formated = ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception ex) {
            System.out.println(">>>>>> Erro ao converter LocalDateTime: " + ldt.toString());
        }
        return new JsonPrimitive(formated);

//        return new JsonPrimitive(ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

}
