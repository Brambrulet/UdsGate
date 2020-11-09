package ru.brambrulet.request;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.CustomerInfo;
import ru.brambrulet.request.sub.UdsRequest;

@Builder(builderMethodName = "findWith")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindCustomerRequest extends UdsRequest<CustomerInfo> {

    protected String code;
    protected String phone;
    protected UUID uid;
    protected Boolean exchangeCode;
    protected BigDecimal total;
    protected BigDecimal skipLoyaltyTotal;

    @Override
    protected String getUrl() {
        return urlBuilder("/customers/find")
                .addParam("code", code)
                .addParam("phone", phone)
                .addParam("uid", uid)
                .addParam("exchangeCode", exchangeCode)
                .addParam("total", total)
                .addParam("skipLoyaltyTotal", skipLoyaltyTotal)
                .build();
    }

    public static class FindCustomerRequestBuilder extends FindCustomerRequest{

        private FindCustomerRequest build() {
            return new FindCustomerRequest(code, phone, uid, exchangeCode, total, skipLoyaltyTotal);
        }

        public CustomerInfo request(UdsGate gate) {
            return build().request(gate, CustomerInfo.class);
        }
    }
}
