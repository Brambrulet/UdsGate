package ru.brambrulet.request.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationCalcReceipt {

    @SerializedName("total")
    @Expose
    public BigDecimal total;

    @SerializedName("cash")
    @Expose
    public BigDecimal cash;

    @SerializedName("skipLoyaltyTotal")
    @Expose
    public BigDecimal skipLoyaltyTotal;

    @SerializedName("points")
    @Expose
    public BigDecimal points;

    @SerializedName("number")
    @Expose
    public String number;
}
