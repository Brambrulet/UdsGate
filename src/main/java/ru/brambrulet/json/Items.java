package ru.brambrulet.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import ru.brambrulet.json.sub.ResponseJson;

@Getter
public class Items extends ResponseJson {

    @SerializedName("rows")
    @Expose
    private List<Item> rows = null;

    @SerializedName("total")
    @Expose
    private Integer total;
}