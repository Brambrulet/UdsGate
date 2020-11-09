
package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class TotalCashSpent {

    @SerializedName("target")
    @Expose
    private BigDecimal target;
}
