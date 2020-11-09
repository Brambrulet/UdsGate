package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cashier {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("displayName")
    @Expose
    private String displayName;
}