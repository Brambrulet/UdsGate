package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.time.ZonedDateTime;
import lombok.Getter;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.json.sub.ItemData;

@Getter
public class Item extends ResponseJson {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("externalId")
    @Expose
    private String externalId;

    @SerializedName("data")
    @Expose
    private ItemData data;

    @SerializedName("dateCreated")
    @Expose
    private ZonedDateTime dateCreated;

    @SerializedName("hidden")
    @Expose
    private Boolean hidden;
}