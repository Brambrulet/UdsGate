package ru.brambrulet.request;

import ru.brambrulet.UdsGate;
import ru.brambrulet.json.Customers;

public class CustomersRequest extends ListRequest<Customers> {

    public static Customers request(UdsGate gate) {
        return new CustomersRequest().request(gate, Customers.class);
    }

    public static Customers request(UdsGate gate, int offset) {
        CustomersRequest request = new CustomersRequest();
        request.setOffset(offset);

        return request.request(gate, Customers.class);
    }

    public static Customers request(UdsGate gate, int max, int offset) {
        CustomersRequest request = new CustomersRequest();
        request.setMax(max).setOffset(offset);

        return request.request(gate, Customers.class);
    }

    @Override
    protected String getUrl() {
        return urlBuilder("/customers").build();
    }
}
