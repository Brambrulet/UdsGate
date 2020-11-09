package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import ru.brambrulet.json.sub.ResponseJson;

@Getter
public class Accepted extends ResponseJson {

    @SerializedName("accepted")
    @Expose
    private String accepted;
}
