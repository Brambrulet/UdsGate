package ru.brambrulet.json.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class ZonedDateTimeAdapter extends TypeAdapter<ZonedDateTime> {

    @Override
    public void write(JsonWriter out, ZonedDateTime value) throws IOException {
        out.value(DateTimeFormatter.ISO_LOCAL_DATE.format(value));
    }

    @Override
    public ZonedDateTime read(JsonReader in) throws IOException {
        return ZonedDateTime.parse(in.nextString());
    }
}
