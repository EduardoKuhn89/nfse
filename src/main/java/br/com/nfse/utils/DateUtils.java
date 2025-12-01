package br.com.nfse.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("ddMMyyyy"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("yyMMdd"),
            DateTimeFormatter.ofPattern("ddMMyy")
    );

    private static final String MES[] = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
    private static final String SEMANA[] = {"segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sábado", "domingo"};

    public static LocalDate isLocalDate(String value) {
        if (value == null || value.length() < 6 || value.length() > 10) {
            return null;
        }

        LocalDate result = null;
        // Tenta parsear a string em cada um dos formatos suportados
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                result = LocalDate.parse(value, formatter);
                return result; // Data válida
            } catch (Exception e) {
                // Ignora e tenta o próximo formato
            }
        }

        return result;
    }

    public static LocalDate getEndOfTheMonth(LocalDate data) {
        return LocalDate.of(data.getYear(), data.getMonth(), data.lengthOfMonth());
    }

    public static LocalDate getStartOfTheMonth(LocalDate data) {
        return LocalDate.of(data.getYear(), data.getMonth(), 1);
    }

    public static String formatWithZone(ZonedDateTime data) {
        return data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"));
    }

    public static String format(ZonedDateTime data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String formatComHora(ZonedDateTime data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public static String formatComHoraSeg(ZonedDateTime data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public static String format(ZonedDateTime data, String formato) {
        if (formato == null || formato.isEmpty()) {
            formato = "dd/MM/yyyy";
        }
        return data.format(DateTimeFormatter.ofPattern(formato));
    }

    public static String format(LocalDateTime dataHr, String formato) {
        if (formato == null || formato.isEmpty()) {
            formato = "dd/MM/yyyy HH:mm:ss";
        }
        return dataHr.format(DateTimeFormatter.ofPattern(formato));
    }

    public static String format(LocalDate data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String format(LocalDate data, String formato) {
        if (formato == null || formato.isEmpty()) {
            formato = "dd/MM/yyyy";
        }
        return data.format(DateTimeFormatter.ofPattern(formato));
    }

    public static String segundosParaHora(long segundos) {
        long horas = segundos / 3600;
        long minutos = (segundos % 3600) / 60;
        long seg = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, seg);
    }

    public static String millisParaHora(long millis) {
        long seconds = (millis / 1000) % 60;
        long minutes = (millis / (1000 * 60)) % 60;
        long hours = (millis / (1000 * 60 * 60)); // sem % 24, para contar acima de 24h
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String nomeDoMes(LocalDate data, boolean abreviado) {
        return nomeDoMes(data, abreviado, false);
    }

    public static String nomeDoMes(LocalDate data, boolean abreviado, boolean comecaMaiusculo) {
        String aux = nomeDoMes(data.getMonthValue(), abreviado);
        if (comecaMaiusculo) {
            aux = aux.substring(0, 1).toUpperCase() + aux.substring(1, aux.length());
        }
        return aux;
    }

    public static String nomeDoMes(int i, boolean abreviado) {
        if (!abreviado) {
            return (MES[i - 1]);
        } else {
            return (MES[i - 1].substring(0, 3));
        }
    }

    public static String diaDaSemana(int i, boolean abreviado) {
        if (!abreviado) {
            return (SEMANA[i - 1]);
        } else {
            return (SEMANA[i - 1].substring(0, 3));
        }
    }

    public static String dataPorExtenso(LocalDate dt) {
        return dataPorExtenso(dt, true);
    }

    public static String dataPorExtenso(LocalDate dt, boolean withWeek) {
        return (withWeek ? diaDaSemana(dt.getDayOfWeek().getValue(), false) + ", " : "")
                + dt.getDayOfMonth() + " de " + nomeDoMes(dt.getMonthValue(), false) + " de " + dt.getYear();
    }

    public static String formatDate(Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    public static int getQtdeSemanasMes(LocalDate data) {
        Calendar cal = Calendar.getInstance();
        LocalDate localDate = LocalDate.of(data.getYear(), data.getMonth(), data.lengthOfMonth());
        cal.setTime(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getDiaSemana(LocalDate data) {
        //Padrão DELPHI de DATA
        switch (data.getDayOfWeek().getValue()) {
            case 1:
                return 2; //SEGUNDA
            case 2:
                return 3; //TERCA
            case 3:
                return 4; //QUARTA
            case 4:
                return 5; //QUINTA
            case 5:
                return 6; //SEXTA
            case 6:
                return 7; //SABADO
            case 7:
                return 1; //DOMINGO
        }
        return 1;
    }

    public static long getDifDatas(LocalDate dt1, LocalDate dt2, ChronoUnit tipo) {
        return dt1.until(dt2, tipo);
    }

    public static long getDifDatas(ZonedDateTime dt1, ZonedDateTime dt2, ChronoUnit tipo) {
        return dt1.until(dt2, tipo);
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static ZonedDateTime toDateTimeZone(String value) {
        if (value == null || value.trim().isEmpty()) {
            return ZonedDateTime.of(1899, 12, 31, 0, 0, 0, 0, ZoneId.systemDefault());
        }

        LocalDate data = LocalDate.parse(value.split(" ")[0]);
        LocalTime time;
        if (value.contains(" ")) {
            time = LocalTime.parse(value.split(" ")[1]);
        } else {
            time = LocalTime.of(0, 0, 0, 0);
        }

        return ZonedDateTime.of(data.getYear(), data.getMonthValue(), data.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond(), time.getNano(), ZoneId.systemDefault());
    }

    public static boolean isBetween(LocalTime reference, LocalTime start, LocalTime end) {
        return !reference.isBefore(start) && !reference.isAfter(end);
    }
}
