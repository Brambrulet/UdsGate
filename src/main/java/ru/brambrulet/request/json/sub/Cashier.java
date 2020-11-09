package ru.brambrulet.request.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cashier {

    @SerializedName("externalId")
    @Expose
    public String externalId;

    @SerializedName("name")
    @Expose
    public String name;
}
