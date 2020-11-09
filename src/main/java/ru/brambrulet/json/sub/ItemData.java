package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import ru.brambrulet.json.enums.ItemType;

@Getter
public class ItemData {

    @SerializedName("type")
    @Expose
    private ItemType type;

    @SerializedName("variants")
    @Expose
    private List<ItemVariant> variants;

    @SerializedName("sku")
    @Expose
    private String sku;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("description")
    @Expose
    private String description;
}
