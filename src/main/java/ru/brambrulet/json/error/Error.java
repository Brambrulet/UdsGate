package ru.brambrulet.json.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Error {

    @SerializedName("errorCode")
    @Expose
    private String errorCode;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("field")
    @Expose
    private String field;

    @SerializedName("value")
    @Expose
    private String value;

    @Override
    public String toString() {
        return "\n\t\t" + errorCode +
                ". " + message +
                " field='" + field + '\'' +
                ", value=" + value;
    }
}