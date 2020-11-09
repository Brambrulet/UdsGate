
package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;

@Getter
public class LoyaltyProgramSettings {

    @SerializedName("baseMembershipTier")
    @Expose
    private BaseMembershipTier baseMembershipTier;

    @SerializedName("membershipTiers")
    @Expose
    private List<MembershipTier> membershipTiers;

    @SerializedName("maxScoresDiscount")
    @Expose
    private Long maxScoresDiscount;

    @SerializedName("referralCashbackRates")
    @Expose
    private List<Integer> referralCashbackRates;

    @SerializedName("cashierAward")
    @Expose
    private Integer cashierAward;

    @SerializedName("referralReward")
    @Expose
    private Integer referralReward;

    @SerializedName("receiptLimit")
    @Expose
    private Integer receiptLimit;

    @SerializedName("deferPointsForDays")
    @Expose
    private Integer deferPointsForDays;
}
