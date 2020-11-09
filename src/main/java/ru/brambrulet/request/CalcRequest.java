package ru.brambrulet.request;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.CustomerInfo;
import ru.brambrulet.request.json.OperationCalc;

@Builder(builderMethodName = "with")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class CalcRequest extends ListRequest<CustomerInfo> {
    private String code;
    private UUID uid;
    private String phone;
    private BigDecimal total;
    private BigDecimal skipLoyaltyTotal;
    private BigDecimal points;

    public static class CalcRequestBuilder extends CalcRequest {
        private CalcRequestBuilder() { }

        private CalcRequest build() {
            return null;
        }

        @Override
        protected RequestBuilder requestBuilder() {
            return RequestBuilder.post()
                    .setEntity(new StringEntity(getJsonSrc(), ContentType.APPLICATION_JSON));
        }

        private String getJsonSrc() {
            OperationCalc calc = new OperationCalc();
            calc.code = code;
            calc.participant.uid = uid;
            calc.participant.phone = phone;
            calc.receipt.total = total;
            calc.receipt.skipLoyaltyTotal = skipLoyaltyTotal;
            calc.receipt.points = points;

            return new Gson().toJson(calc);
        }

        @Override
        protected String getUrl() {
            return urlBuilder("/operations/calc").build();
        }

        public CustomerInfo request(UdsGate gate) {
            return request(gate, CustomerInfo.class);
        }
    }
}
