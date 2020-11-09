package ru.brambrulet.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Branch {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("displayName")
    @Expose
    private String displayName;
}