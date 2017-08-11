package models;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToJsonConverter extends StdConverter<LocalDateTime, String> {
    @Override
    public String convert(LocalDateTime value) {
        return value.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
