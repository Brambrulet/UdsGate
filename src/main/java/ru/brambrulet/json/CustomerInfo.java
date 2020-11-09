package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.json.sub.Purchase;

@Getter
public class CustomerInfo extends ResponseJson {

    @SerializedName("user")
    @Expose
    private Customer user;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("purchase")
    @Expose
    private Purchase purchase;
}
