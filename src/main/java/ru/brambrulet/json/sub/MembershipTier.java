
package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class MembershipTier {

    @SerializedName("uid")
    @Expose
    private String uid;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("rate")
    @Expose
    private Integer rate;

    @SerializedName("conditions")
    @Expose
    private Conditions conditions;
}
