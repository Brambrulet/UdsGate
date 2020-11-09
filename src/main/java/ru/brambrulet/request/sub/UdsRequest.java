package ru.brambrulet.request.sub;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import ru.brambrulet.UdsGate;
import ru.brambrulet.exception.UdsException;
import ru.brambrulet.json.adapters.EnumAdapter;
import ru.brambrulet.json.adapters.UuidAdapter;
import ru.brambrulet.json.adapters.ZonedDateTimeAdapter;
import ru.brambrulet.json.enums.DiscountPolicy;
import ru.brambrulet.json.enums.Gender;
import ru.brambrulet.json.enums.ItemType;
import ru.brambrulet.json.error.CommonError;
import ru.brambrulet.json.sub.ResponseJson;

public abstract class UdsRequest<T extends ResponseJson> {

    protected static final String EMPTY_JSON_OBJECT_SRC = "{}";
    protected static final int MAX_MAX = 50;
    protected static final int MAX_OFFSET = 10_000;
    protected static final String BASE_URL = "https://api.uds.app/partner/v2";
    protected static final String X_TIMESTAMP = "X-Timestamp";
    protected static final String X_ORIGIN_REQUEST_ID = "X-Origin-Request-Id";
    protected static final String X_REQUEST_ID = "X-Request-Id";

    private UUID xOriginRequestId;
    private UUID xRequestId;
    private ZonedDateTime xTimeStamp;
    private HttpResponse response;
    private Class<T> clazz;

    protected abstract String getUrl();

    protected RequestBuilder requestBuilder() {
        return RequestBuilder.get();
    }

    protected T request(UdsGate gate, Class<T> clazz) {
        this.clazz = clazz;
        xOriginRequestId = UUID.randomUUID();
        ZonedDateTime xOriginTimeStamp = ZonedDateTime.now();

        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = requestBuilder()
                .setUri(getUrl())
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
                .setHeader(HttpHeaders.AUTHORIZATION, gate.getAutorization())
                .setHeader(X_ORIGIN_REQUEST_ID, xOriginRequestId.toString())
                .setHeader(X_TIMESTAMP, DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(xOriginTimeStamp))
                .build();

        try {
            System.out.println();
            System.out.println(request);

            response = client.execute(request);
            checkIsOk();
            checkOriginRequestId();
            extractRequestId();
            extractTimeStamp();

            return parseResponse();
        } catch (IOException e) {
            return newInstance(clazz, e.getMessage());
        }
    }

    private void extractTimeStamp() throws UdsException {
        Optional<ZonedDateTime> timeStamp = Arrays.stream(response.getAllHeaders())
                .filter(header -> X_TIMESTAMP.equals(header.getName()))
                .map(header -> ZonedDateTime.parse(header.getValue()))
                .findFirst();

        if (!timeStamp.isPresent()) {
            throw new UdsException("Wrong response - " + X_TIMESTAMP + " is out.");
        }

        xTimeStamp = timeStamp.get();
    }

    private void extractRequestId() throws UdsException {
        Optional<UUID> requestId = Arrays.stream(response.getAllHeaders())
                .filter(header -> X_REQUEST_ID.equals(header.getName()))
                .map(header -> UUID.fromString(header.getValue()))
                .findFirst();

        if (!requestId.isPresent()) {
            throw new UdsException("Wrong response - " + X_REQUEST_ID + " is out.");
        }

        xRequestId = requestId.get();
    }

    private void checkOriginRequestId() throws UdsException {
        if (Arrays.stream(response.getAllHeaders())
                .filter(header -> X_ORIGIN_REQUEST_ID.equals(header.getName()))
                .noneMatch(header -> xOriginRequestId.equals(UUID.fromString(header.getValue())))) {
            throw new UdsException("Wrong " + X_ORIGIN_REQUEST_ID);
        }
    }

    private T newInstance(Class<T> clazz, String errorString) {
        try {
            T instance = clazz.newInstance();
            instance.setErrorMessage(errorString);
            instance.setRequestInfo(xOriginRequestId, xRequestId, xTimeStamp);

            return instance;
        } catch (InstantiationException | IllegalAccessException ex) {
            return null;
        }
    }

    private void checkIsOk() throws UdsException {
        switch (response.getStatusLine().getStatusCode()) {
            case HttpStatus.SC_OK:
            case HttpStatus.SC_NO_CONTENT:
                return;

            default:
                throw new UdsException(getError(response));
        }
    }

    private boolean isNoContent() {
        return HttpStatus.SC_NO_CONTENT == response.getStatusLine().getStatusCode();
    }

    private T parseResponse() throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter().nullSafe())
                .registerTypeAdapter(UUID.class, new UuidAdapter().nullSafe())
                .registerTypeAdapter(Gender.class, EnumAdapter.forEnum(Gender.class))
                .registerTypeAdapter(ItemType.class, EnumAdapter.forEnum(ItemType.class))
                .registerTypeAdapter(DiscountPolicy.class, EnumAdapter.forEnum(DiscountPolicy.class))
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        String responseText = isNoContent() ? EMPTY_JSON_OBJECT_SRC : new BasicResponseHandler().handleResponse(response);
        System.out.println(response.getStatusLine());
        System.out.println(gson.toJson(gson.fromJson(responseText, JsonElement.class)));

        return gson.fromJson(responseText, clazz);
    }

    private String getError(HttpResponse response) {
        System.out.println(response.getStatusLine());
        try {
            String responseText = new BasicResponseHandler().handleResponse(response);
            System.out.println(responseText);

            return new Gson().fromJson(responseText, CommonError.class).toString();
        } catch (IOException e) {
            return getCommonHttpError(response);
        }
    }

    private String getCommonHttpError(HttpResponse response) {
        StatusLine status = response.getStatusLine();
        String message = "Bad request. " + status.getStatusCode() + " " + status.getReasonPhrase();

        System.out.println(message);

        return message;
    }

    protected UrlBuilder urlBuilder(String path) {
        return new UrlBuilder(UdsRequest.BASE_URL + path);
    }

    protected class UrlBuilder {

        private final Map<String, String> params = new TreeMap<>();
        private final String path;
        private StringBuilder builder;

        UrlBuilder(String path) {
            this.path = path;
        }

        @SneakyThrows(UnsupportedEncodingException.class)
        public UrlBuilder addParam(String paramName, Object value) {
            if (!Objects.isNull(paramName) && !Objects.isNull(value)) {
                params.put(paramName, URLEncoder.encode(value.toString(), "UTF-8"));
            }

            return this;
        }

        public String build() {
            if (params.size() == 0) {
                return path;
            }

            builder = new StringBuilder();
            params.forEach(this::appendParamToStringBuilder);
            builder.setCharAt(0, '?');

            return path + builder.toString();
        }

        private void appendParamToStringBuilder(String name, String value) {
            builder.append(String.format("&%s=%s", name, value));
        }
    }
}
