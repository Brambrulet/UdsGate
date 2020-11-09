package ru.brambrulet.json.error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;

@Getter
public class CommonError {

    @SerializedName("errorCode")
    @Expose
    private String errorCode;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("errors")
    @Expose
    private List<Error> errors = null;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(errorCode + ". " + message);

        if (errors != null) {
            for (Error error : errors) {
                builder.append("\n" + error);
            }
        }
        return builder.toString();
    }
}