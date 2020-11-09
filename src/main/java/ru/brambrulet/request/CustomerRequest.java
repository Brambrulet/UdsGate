package ru.brambrulet.request;

import lombok.AllArgsConstructor;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.Customer;
import ru.brambrulet.request.sub.UdsRequest;

@AllArgsConstructor
public class CustomerRequest extends UdsRequest<Customer> {
    private Long id;

    public static Customer request(UdsGate gate, Long id) {
        return new CustomerRequest(id).request(gate, Customer.class);
    }

    @Override
    protected String getUrl() {
        return urlBuilder("/customers/" + id).build();
    }
}
