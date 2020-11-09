package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class Participant {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("inviterId")
    @Expose
    private Long inviterId;

    @SerializedName("points")
    @Expose
    private BigDecimal points;

    @SerializedName("discountRate")
    @Expose
    private Long discountRate;

    @SerializedName("cashbackRate")
    @Expose
    private Long cashbackRate;

    @SerializedName("membershipTier")
    @Expose
    private MembershipTier membershipTier;

    @SerializedName("dateCreated")
    @Expose
    private ZonedDateTime dateCreated;

    @SerializedName("lastTransactionTime")
    @Expose
    private ZonedDateTime lastTransactionTime;

    @SerializedName("birthDate")
    @Expose
    private ZonedDateTime birthDate;
}
