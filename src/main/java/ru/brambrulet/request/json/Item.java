package ru.brambrulet.request.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {

    @SerializedName("nodeId")
    @Expose
    public Long nodeId;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("externalId")
    @Expose
    public String externalId;

    @SerializedName("data")
    @Expose
    public ItemData data = new ItemData();

    @SerializedName("hidden")
    @Expose
    public Boolean hidden;

    public void assign(ru.brambrulet.json.Item src) {
        name = src.getName();
        externalId = src.getExternalId();
        hidden = src.getHidden();
        data.assign(src.getData());
    }
}
