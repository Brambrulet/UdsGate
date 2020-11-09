package ru.brambrulet.request;

import lombok.NoArgsConstructor;
import ru.brambrulet.json.sub.ResponseJson;
import ru.brambrulet.request.sub.UdsRequest;

@NoArgsConstructor
public abstract class ListRequest<T extends ResponseJson> extends UdsRequest<T> {
    protected Integer max;
    protected Integer offset;

    protected ListRequest(int max, int offset) {
        setMax(max);
        setOffset(offset);
    }

    protected ListRequest<T> setMax(int max) {
        this.max = Math.max(Math.min(max, MAX_MAX), 0);
        return this;
    }

    protected ListRequest<T> setOffset(int offset) {
        this.offset = Math.max(Math.min(offset, MAX_OFFSET), 0);
        return this;
    }

    @Override
    protected UrlBuilder urlBuilder(String path) {
        return super.urlBuilder(path)
                .addParam("max", max)
                .addParam("offset", offset);
    }
}
