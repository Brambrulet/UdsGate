package ru.brambrulet.request;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.OneOfOperation;
import ru.brambrulet.request.json.OperationNew;

import static java.util.Objects.isNull;

@Builder(builderMethodName = "create")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationRequest extends ListRequest<OneOfOperation>  {

    protected Long id;
    protected String code;
    protected String phone;
    protected UUID uid;
    protected String nonce;
    protected String cashierExternalId;
    protected String cashierName;
    protected BigDecimal total;
    protected BigDecimal cash;
    protected BigDecimal points;
    protected String number;
    protected BigDecimal skipLoyaltyTotal;

    public static OneOfOperation getById(UdsGate gate, long id) {
        OperationRequest request = new OperationRequest();
        request.id = id;
        return request.request(gate, OneOfOperation.class);
    }

    @Override
    protected RequestBuilder requestBuilder() {
        if (!isNull(id)) {
            return super.requestBuilder();
        }

        return RequestBuilder.post()
                .setEntity(new StringEntity(getJsonSrc(), ContentType.APPLICATION_JSON));
    }

    private String getJsonSrc() {
        OperationNew operation = new OperationNew();
        operation.code = code;
        operation.participant.uid = uid;
        operation.participant.phone = phone;
        operation.cashier.externalId = cashierExternalId;
        operation.cashier.name = cashierName;
        operation.nonce = nonce;
        operation.receipt.total = total;
        operation.receipt.cash = cash;
        operation.receipt.points = points;
        operation.receipt.number = number;
        operation.receipt.skipLoyaltyTotal = skipLoyaltyTotal;

        return new Gson().toJson(operation);
    }

    @Override
    protected String getUrl() {
        return urlBuilder(isNull(id) ? "/operations/calc" : ("/operations/" + id)).build();
    }

    public static class OperationRequestBuilder extends OperationRequest {
        private OperationRequestBuilder() {
        }

        private OperationRequest build() {
            return new OperationRequest(null, code, phone, uid, nonce, cashierExternalId,
                                        cashierName, total, cash, points,
                                        number, skipLoyaltyTotal);
        }

        public OneOfOperation request(UdsGate gate) {
            return build().request(gate, OneOfOperation.class);
        }
    }
}
