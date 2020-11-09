package ru.brambrulet.request.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.brambrulet.request.json.sub.OperationCalcParticipant;
import ru.brambrulet.request.json.sub.OperationCalcReceipt;

@Data
@NoArgsConstructor
public class OperationCalc {

    @SerializedName("code")
    @Expose
    public String code;

    @SerializedName("participant")
    @Expose
    public OperationCalcParticipant participant = new OperationCalcParticipant();

    @SerializedName("receipt")
    @Expose
    public OperationCalcReceipt receipt = new OperationCalcReceipt();
}
