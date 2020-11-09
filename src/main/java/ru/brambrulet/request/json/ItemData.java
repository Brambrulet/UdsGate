package ru.brambrulet.request.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.brambrulet.json.sub.ItemVariant;
import ru.brambrulet.json.enums.ItemType;

public class ItemData {

    @SerializedName("type")
    @Expose
    public ItemType type;

    @SerializedName("variants")
    @Expose
    public List<ItemVariant> variants;

    @SerializedName("sku")
    @Expose
    public String sku;

    @SerializedName("price")
    @Expose
    public Double price;

    @SerializedName("description")
    @Expose
    public String description;

    public void assign(ru.brambrulet.json.sub.ItemData src) {
        type = src.getType();
        sku = src.getSku();
        price = src.getPrice();
        description = src.getDescription();
        variants = Objects.isNull(src.getVariants()) ? null : new ArrayList<>(src.getVariants());
    }
}
