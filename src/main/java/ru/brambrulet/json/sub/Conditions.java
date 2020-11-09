package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Conditions {

    @SerializedName("totalCashSpent")
    @Expose
    private TotalCashSpent totalCashSpent;

    @SerializedName("effectiveInvitedCount")
    @Expose
    private EffectiveInvitedCount effectiveInvitedCount;
}
