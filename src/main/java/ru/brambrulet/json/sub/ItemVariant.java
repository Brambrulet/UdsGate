package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemVariant {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("sku")
    @Expose
    private String sku;

    @SerializedName("price")
    @Expose
    private Double price;
}
