
package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.brambrulet.json.enums.DiscountPolicy;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.json.sub.LoyaltyProgramSettings;

@Getter
@NoArgsConstructor
public class ApplicationSettings extends ResponseJson {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("promoCode")
    @Expose
    private String promoCode;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("baseDiscountPolicy")
    @Expose
    private DiscountPolicy baseDiscountPolicy;

    @SerializedName("loyaltyProgramSettings")
    @Expose
    private LoyaltyProgramSettings loyaltyProgramSettings;

    @SerializedName("purchaseByPhone")
    @Expose
    private Boolean purchaseByPhone;

    @SerializedName("writeInvoice")
    @Expose
    private Boolean writeInvoice;

    @SerializedName("burnPointsOnPurchase")
    @Expose
    private Boolean burnPointsOnPurchase;

    @SerializedName("burnPointsOnPricelist")
    @Expose
    private Boolean burnPointsOnPricelist;

    @SerializedName("slug")
    @Expose
    private String slug;
}
