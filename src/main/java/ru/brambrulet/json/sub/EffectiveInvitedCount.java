
package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class EffectiveInvitedCount {

    @SerializedName("target")
    @Expose
    private Integer target;
}
