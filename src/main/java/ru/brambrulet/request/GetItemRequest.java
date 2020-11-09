package ru.brambrulet.request;

import lombok.AllArgsConstructor;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.Item;
import ru.brambrulet.request.sub.UdsRequest;

@AllArgsConstructor
public class GetItemRequest extends UdsRequest<Item> {

    private Long id;

    public static Item request(UdsGate gate, Long id) {
        return new GetItemRequest(id).request(gate, Item.class);
    }

    @Override
    protected String getUrl() {
        return urlBuilder("/goods/" + id)
                .build();
    }
}
