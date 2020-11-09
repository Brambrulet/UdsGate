package ru.brambrulet.json.sub;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ResponseJson {

    private String errorMessage;
    private boolean isError;
    private UUID xOriginRequestId;
    private UUID xRequestId;
    private ZonedDateTime xTimeStamp;

    public ResponseJson() {
        isError = false;
    }

    public void setRequestInfo(UUID xOriginRequestId, UUID xRequestId, ZonedDateTime xTimeStamp) {
        if (!Objects.isNull(this.xOriginRequestId)) {
            return;
        }

        this.xOriginRequestId = xOriginRequestId;
        this.xRequestId = xRequestId;
        this.xTimeStamp = xTimeStamp;
    }

    public ResponseJson setRequestInfo(UUID xOriginRequestId) {
        if (Objects.isNull(this.xOriginRequestId)) {
            this.xOriginRequestId = xOriginRequestId;
        }
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        this.isError = true;
    }
}
