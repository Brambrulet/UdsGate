package ru.brambrulet.json.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class EnumAdapter<T extends Enum<T>> extends TypeAdapter<T> {

    private Class<T> clazz;

    public static<T extends Enum<T>> TypeAdapter<T> forEnum(Class<T> clazz) {
        return new EnumAdapter<>(clazz).nullSafe();
    }

    public EnumAdapter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        out.value(value.name());
    }

    @Override
    public T read(JsonReader in) throws IOException {
        return Enum.valueOf(clazz, in.nextString());
    }
}
