package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import lombok.Getter;
import ru.brambrulet.json.enums.Gender;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.json.sub.MembershipTier;
import ru.brambrulet.json.sub.Participant;

@Getter
public class Customer extends ResponseJson {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("uid")
    @Expose
    private UUID uid;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("displayName")
    @Expose
    private String displayName;

    @SerializedName("gender")
    @Expose
    private Gender gender;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("membershipTier")
    @Expose
    private MembershipTier membershipTier;

    @SerializedName("participant")
    @Expose
    private Participant participant;
}