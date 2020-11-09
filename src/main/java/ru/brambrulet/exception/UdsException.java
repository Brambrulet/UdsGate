package ru.brambrulet.exception;

import java.io.IOException;
import lombok.NoArgsConstructor;
import ru.brambrulet.json.error.CommonError;

@NoArgsConstructor
public class UdsException extends IOException {

    public UdsException(String message) {
        super(message);
    }
    public UdsException(String message, Throwable cause) {
        super(message, cause);
    }
    public UdsException(Throwable cause) {
        super(cause);
    }
    public UdsException(CommonError error) {
        super(error.toString());
    }
}
