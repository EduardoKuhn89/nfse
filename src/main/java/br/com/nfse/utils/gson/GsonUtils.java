package br.com.nfse.utils.gson;

import br.com.nfse.utils.gson.converters.LocalDateConverter;
import br.com.nfse.utils.gson.converters.LocalDateTimeConverter;
import br.com.nfse.utils.gson.converters.LocalTimeConverter;
import br.com.nfse.utils.gson.converters.ZonedDateTimeConverter;
import br.com.nfse.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GsonUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static Gson builder() {
        return builder(false);
    }

    public static Gson builder(boolean withoutExpose) {
        return builder(false, false);
    }

    public static Gson builder(boolean withoutExpose, boolean prettyPrinting) {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeConverter());
        builder.registerTypeAdapter(LocalDate.class, new LocalDateConverter());
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeConverter());
        builder.registerTypeAdapter(LocalTime.class, new LocalTimeConverter());

        if (withoutExpose) {
            builder.excludeFieldsWithoutExposeAnnotation();
        }

        if (prettyPrinting) {
            builder.setPrettyPrinting();
        }

        return builder.create();
    }

    public static <T> T deserialize(JsonElement element, Class<T> clazz) {
        return builder().fromJson(element, clazz);
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        return builder().fromJson(JsonParser.parseString​(json), clazz);
    }

    public static <T> T deserialize(File file, Class<T> clazz) {
        try {
            return builder().fromJson(JsonParser.parseString​(FileUtils.getContent(file)), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(JsonElement element, Type clazz) {
        return builder().fromJson(element, clazz);
    }

    public static String serialize(Object object) {
        return builder().toJson(object);
    }

    public static JsonElement toJsonTree(Object object) {
        return builder().toJsonTree(object);
    }

    public static JsonElement toJsonTree(Object object, Type type) {
        return builder().toJsonTree(object, type);
    }

    public static String toJson(JsonElement object) {
        return builder().toJson(object);
    }

    public static String toJson(Object object) {
        return builder().toJson(object);
    }

    public static JsonObject serializeToJsonObject(Object object) {
        return JsonParser.parseString(toJson(object)).getAsJsonObject();
    }

    public static JsonObject serializeToJsonObject(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }

    public static JsonArray serializeToJsonArray(Object object) {
        return JsonParser.parseString(toJson(object)).getAsJsonArray();
    }

    public static JsonArray toJsonArray(String object) {
        return JsonParser.parseString(object).getAsJsonArray();
    }

    public static <T> List<T> toList(String jsonArray, Class<T> clazz) {
        return toList(jsonArray, clazz, false);
    }

    public static <T> List<T> toList(String jsonArray, Class<T> clazz, boolean emptyDefault) {
        if (emptyDefault && (jsonArray == null || jsonArray.isEmpty())) {
            jsonArray = "[]";
        }
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return builder().fromJson(jsonArray, typeOfT);
    }

    public static <T> List<T> cloneList(Object object, Class<T> clazz) {
        String jsonArray = toJsonTree(object).toString();
        Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
        return builder().fromJson(jsonArray, typeOfT);
    }

    public static void saveToFile(Object obj, String file) throws IOException {
        try (BufferedWriter bufferedWriter = FileUtils.bufferedWriter(file)) {
            builder(false, true).toJson(obj, bufferedWriter);
        }
    }

    public static <T> T loadFromFile(String file, Class<T> clazz) throws Exception {
        return loadFromFile(new File(file), clazz);
    }

    public static <T> T loadFromFile(File file, Class<T> clazz) throws Exception {
        String str = FileUtils.getContent(file);
        return builder().fromJson(serializeToJsonObject(str), clazz);
    }

    public static LocalDate getLocalDate(JsonObject json, String property) {
        if (json == null || !json.has(property) || json.get(property).isJsonNull()) {
            return null;
        }

        String dateString = json.get(property).getAsString();
        if (dateString == null || dateString.trim().isEmpty() || "null".equalsIgnoreCase(dateString)) {
            return null;
        }

        try {
            if (dateString.contains("T")) {
                return LocalDateTime.parse(dateString, DATE_TIME_FORMATTER).toLocalDate();
            } else {
                return LocalDate.parse(dateString, DATE_FORMATTER);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String getStringOrNull(JsonObject json, String property) {
        return json.has(property) && !json.get(property).isJsonNull()
                ? json.get(property).getAsString() : null;
    }

    public static Boolean getBooleanOrNull(JsonObject json, String property) {
        return json.has(property) && !json.get(property).isJsonNull()
                ? json.get(property).getAsBoolean() : null;
    }

    public static Integer getIntegerOrNull(JsonObject json, String property) {
        return json.has(property) && !json.get(property).isJsonNull()
                ? json.get(property).getAsInt() : null;
    }

    public static BigDecimal getBigDecimalOrNull(JsonObject json, String property) {
        if (!json.has(property) || json.get(property).isJsonNull()) {
            return null;
        }

        try {
            return json.get(property).getAsBigDecimal();
        } catch (NumberFormatException e) {
            try {
                return BigDecimal.valueOf(json.get(property).getAsDouble());
            } catch (Exception ex) {
                System.err.println("Erro ao converter BigDecimal: " + property + " - " + json.get(property));
                return BigDecimal.ZERO;
            }
        }
    }
}
