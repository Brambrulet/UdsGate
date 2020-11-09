package ru.brambrulet.request;

import ru.brambrulet.UdsGate;
import ru.brambrulet.json.Items;

public class ItemsRequest extends ListRequest<Items> {

    private Long nodeId;

    public static Items request(UdsGate gate) {
        return new ItemsRequest().request(gate, Items.class);
    }

    public static Items request(UdsGate gate, int offset) {
        ItemsRequest request = new ItemsRequest();
        request.setOffset(offset);

        return request.request(gate, Items.class);
    }

    public static Items request(UdsGate gate, int max, int offset) {
        ItemsRequest request = new ItemsRequest();
        request.setMax(max).setOffset(offset);

        return request.request(gate, Items.class);
    }

    @Override
    protected String getUrl() {
        return urlBuilder("/goods")
                .addParam("nodeId", nodeId)
                .build();
    }
}
