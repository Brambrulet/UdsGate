package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Origin {

    @SerializedName("id")
    @Expose
    private Long id;
}