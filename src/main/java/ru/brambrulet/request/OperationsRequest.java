package ru.brambrulet.request;

import ru.brambrulet.UdsGate;
import ru.brambrulet.json.Operations;

public class OperationsRequest extends ListRequest<Operations> {

    public static Operations request(UdsGate gate) {
        return new OperationsRequest().request(gate, Operations.class);
    }

    public static Operations request(UdsGate gate, int offset) {
        OperationsRequest request = new OperationsRequest();
        request.setOffset(offset);

        return request.request(gate, Operations.class);
    }

    public static Operations request(UdsGate gate, int max, int offset) {
        OperationsRequest request = new OperationsRequest();
        request.setMax(max).setOffset(offset);

        return request.request(gate, Operations.class);
    }

    @Override
    protected String getUrl() {
        return urlBuilder("/operations").build();
    }
}
