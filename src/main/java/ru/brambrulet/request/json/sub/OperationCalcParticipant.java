package ru.brambrulet.request.json.sub;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationCalcParticipant {

    @SerializedName("uid")
    @Expose
    public UUID uid;

    @SerializedName("phone")
    @Expose
    public String phone;
}
