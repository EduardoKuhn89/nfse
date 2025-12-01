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
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class ZonedDateTimeConverter implements JsonDeserializer<ZonedDateTime>, JsonSerializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        if (StringUtils.isNullOrEmpty(json.getAsString())) {
            return null;
        }

        //ex: 2024-04-05T00:00:01
        if (json.getAsString().length() == 19 && json.getAsString().contains("T")) {
            return ZonedDateTime.of(LocalDate.parse(json.getAsString().split("T")[0]), LocalTime.parse(json.getAsString().split("T")[1]), ZoneId.systemDefault());
        }

        //ex: 2024-02-24T06:36:07-03:00 - OK
        return ZonedDateTime.parse(json.getAsString());
    }

    @Override
    public JsonElement serialize(ZonedDateTime zdt, Type type, JsonSerializationContext jsc) {
        String formated = "";
        try {
            formated = zdt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        } catch (Exception ex) {
            System.out.println(">>>>>> Erro ao converter ZonedDateTime: " + zdt.toString());
        }
        return new JsonPrimitive(formated);
    }
}
