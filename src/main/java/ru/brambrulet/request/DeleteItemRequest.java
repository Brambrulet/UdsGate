package ru.brambrulet.request;

import lombok.AllArgsConstructor;
import org.apache.http.client.methods.RequestBuilder;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.request.sub.UdsRequest;

@AllArgsConstructor
public class DeleteItemRequest extends UdsRequest<ResponseJson> {

    private Long id;

    public static ResponseJson request(UdsGate gate, Long id) {
        return new DeleteItemRequest(id).request(gate, ResponseJson.class);
    }

    @Override
    protected RequestBuilder requestBuilder() {
        return RequestBuilder.delete();
    }

    @Override
    protected String getUrl() {
        return urlBuilder("/goods/" + id)
                .build();
    }
}
