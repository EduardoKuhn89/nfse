package br.com.nfse.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.stream.Collectors;

public class NumberUtils {

    public static String NUMBERS = "0 1 2 3 4 5 6 7 8 9";

    public static String format(BigDecimal valor) {
        if (valor == null) {
            return "";
        }
        return format(valor, null);
    }

    public static String format(Integer valor, String formato) {
        if (formato == null || formato.isEmpty()) {
            formato = "##,###,###,##0.00";
        }
        DecimalFormatSymbols df = new DecimalFormatSymbols();
        df.setDecimalSeparator(',');
        df.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat(formato, df);
        return decimalFormat.format(valor);
    }

    public static String format(BigDecimal valor, String formato) {
        if (formato == null || formato.isEmpty()) {
            formato = "##,###,###,##0.00";
        }
        DecimalFormatSymbols df = new DecimalFormatSymbols();
        df.setDecimalSeparator(',');
        df.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat(formato, df);
        return decimalFormat.format(valor);
    }

    public static BigDecimal isNumber(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        value = value.trim();

        // Verifica se a string contém apenas caracteres válidos para um número
        if (!value.matches("[0-9.,+-]+")) {
            return null;
        }

        if (value.contains(",") && value.contains(".")) {
            value = value.replace(".", "").replace(",", ".").trim();
        } else if (value.contains(",")) {
            value = value.replace(",", ".").trim();
        }

        return NumberUtils.isBigDecimal(value);
    }

    public static Integer isInteger(String value) {
        Integer aux = null;
        try {

            if (value != null && value.endsWith(".0")) {
                value = value.split("\\.")[0];
            }

            aux = Integer.parseInt(value);
        } catch (Exception e) {
        }
        return aux;
    }

    public static List<Integer> convertStringToInteger(List<String> strList) {
        return strList.stream().filter(s -> isInteger(s) != null).map(Integer::valueOf).collect(Collectors.toList());
    }

    public static Double isDouble(String value) {
        Double aux = null;
        try {
            aux = Double.parseDouble(value);
        } catch (Exception e) {
        }
        return aux;
    }

    public static Float isFloat(String value) {
        Float aux = null;
        try {
            aux = Float.parseFloat(value);
        } catch (Exception e) {
        }
        return aux;
    }

    public static BigDecimal isBigDecimal(String value) {
        BigDecimal aux = null;
        try {
            aux = new BigDecimal(value);
        } catch (Exception e) {
        }
        return aux;
    }

    public static Long isLong(String value) {
        Long aux = null;
        try {
            aux = Long.parseLong(value);
        } catch (Exception e) {
        }
        return aux;
    }

    public static BigDecimal parseBigDecimal(Double valor) {
        return new BigDecimal((valor == null) ? "0" : valor.toString());
    }

    public static BigDecimal parseBigDecimalByStr(String valor) {
        return (isBigDecimal(valor) == null ? new BigDecimal("0") : isBigDecimal(valor));
    }

    public static String formatMoney(Double valor) {
        if (valor == null) {
            return "0,00";
        }

        DecimalFormatSymbols df = new DecimalFormatSymbols();
        df.setDecimalSeparator(',');
        df.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,##0.00", df);

        return decimalFormat.format(valor);
    }

    public static BigDecimal trunc(BigDecimal value) {
        if (value == null) {
            value = BigDecimal.ZERO;
        }

        String aux = value.toString();
        if (!aux.contains(".") || aux.length() == 1) {
            return new BigDecimal(aux);
        }

        return new BigDecimal(StringUtils.copy(aux, 1, aux.indexOf(".")));
    }

    public static String formatLong(long value) {
        return String.format("%,d", value).replace(",", ".");
    }

    public static Integer toInt(String vlr) {
        return toInt(vlr, null);
    }

    public static Integer toInt(String vlr, Integer defaultValue) {
        if (vlr == null || vlr.isEmpty()) {
            return defaultValue;
        }

        return Integer.parseInt(vlr);
    }

    public static BigDecimal toBig(String vlr) {
        return toBig(vlr, null);
    }

    public static BigDecimal toBig(String vlr, BigDecimal defaultValue) {
        if (vlr == null || vlr.isEmpty()) {
            return defaultValue;
        }

        return new BigDecimal(vlr.replace(",", "."));
    }

    public static String normalizeNumbers(String value) {
        if (value == null || value.isEmpty()) {
            return "0";
        }

        // Removendo espaços e operadores redundantes
        value = value.replaceAll("\\s+", "") // Remove espaços extras
                .replaceAll("\\++", "+") // Remove excesso de "+"
                .replaceAll("--+", "-") // Substitui múltiplos "-" por um único "-"
                .replaceAll("\\*\\*+", "*") // Remove excesso de "*"
                .replaceAll("//+", "/") // Remove excesso de "/"
                .replaceAll("\\+-", "-") // Corrige "+-" para "-"
                .replaceAll("-\\+", "-") // Corrige "-+" para "-"
                .replaceAll("\\+\\*", "*") // Corrige "+*" para "*"
                .replaceAll("\\+/", "/") // Corrige "+/" para "/"
                .replaceAll("-\\*", "*") // Corrige "-*" para "*"
                .replaceAll("-/", "/"); // Corrige "-/" para "/"

        // Garantindo que a expressão inicie corretamente (evita erro do primeiro operador ser um *)
        if (value.startsWith("*") || value.startsWith("/")) {
            value = "0" + value;
        }

        StringBuilder result = new StringBuilder();
        String[] split = value.split("(?=[+\\-*/])|(?<=[+\\-*/])"); // Divide números e operadores corretamente

        for (String elemento : split) {
            if (elemento.isEmpty()) {
                continue; // Ignora elementos vazios
            }
            // Tratamento de números com "," e "."
            if (elemento.matches(".*\\d.*")) { // Apenas processa se houver números
                if (elemento.contains(",") && elemento.contains(".")) {
                    elemento = elemento.replace(".", "").replace(",", ".").trim();
                } else if (elemento.contains(",")) {
                    elemento = elemento.replace(",", ".").trim();
                }
            }

            result.append(elemento);
        }

        return result.toString();
    }

}
