package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class Purchase {

    @SerializedName("maxPoints")
    @Expose
    private BigDecimal maxPoints;

    @SerializedName("total")
    @Expose
    private BigDecimal total;

    @SerializedName("skipLoyaltyTotal")
    @Expose
    private BigDecimal skipLoyaltyTotal;

    @SerializedName("discountAmount")
    @Expose
    private BigDecimal discountAmount;

    @SerializedName("discountPercent")
    @Expose
    private Long discountPercent;

    @SerializedName("points")
    @Expose
    private BigDecimal points;

    @SerializedName("pointsPercent")
    @Expose
    private BigDecimal pointsPercent;

    @SerializedName("netDiscount")
    @Expose
    private BigDecimal netDiscount;

    @SerializedName("netDiscountPercent")
    @Expose
    private Integer netDiscountPercent;

    @SerializedName("cash")
    @Expose
    private BigDecimal cash;
    
    @SerializedName("cashBack")
    @Expose
    private BigDecimal cashBack;
}