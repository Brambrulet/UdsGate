package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.brambrulet.json.sub.ResponseJson;

@NoArgsConstructor
@Getter
public class Operations extends ResponseJson {

    @SerializedName("rows")
    @Expose
    private List<Operation> rows;

    @SerializedName("total")
    @Expose
    private Long total;
}