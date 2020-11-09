package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import ru.brambrulet.json.sub.ResponseJson;

@Getter
public class Customers extends ResponseJson {

    @SerializedName("rows")
    @Expose
    private List<Customer> rows;
}
