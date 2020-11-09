package ru.brambrulet.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.json.sub.Branch;
import ru.brambrulet.json.sub.Cashier;
import ru.brambrulet.json.sub.Origin;

@NoArgsConstructor
@Getter
public class Operation extends ResponseJson {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("dateCreated")
    @Expose
    private ZonedDateTime dateCreated;

    @SerializedName("action")
    @Expose
    private String action;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("cashier")
    @Expose
    private Cashier cashier;

    @SerializedName("branch")
    @Expose
    private Branch branch;

    @SerializedName("points")
    @Expose
    private BigDecimal points;

    @SerializedName("cash")
    @Expose
    private BigDecimal cash;

    @SerializedName("total")
    @Expose
    private BigDecimal total;

    @SerializedName("receiptNumber")
    @Expose
    private String receiptNumber;

    @SerializedName("origin")
    @Expose
    private Origin origin;
}
