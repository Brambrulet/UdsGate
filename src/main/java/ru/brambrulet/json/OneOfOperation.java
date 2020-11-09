package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.brambrulet.json.sub.ResponseJson;

@NoArgsConstructor
@Getter
public class OneOfOperation extends ResponseJson {

    @SerializedName("oneOf")
    @Expose
    private List<Operation> oneOf;
}