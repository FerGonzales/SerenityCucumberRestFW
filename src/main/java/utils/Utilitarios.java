package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class Utilitarios {
    public String getFechaActual()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
