package ru.fratask.exception;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class AntivirusException extends RuntimeException {

    private AntivirusResponseCode code;
    private String errorMessage;

    public AntivirusException(AntivirusResponseCode code) {
        super(code.getErrorMessage());
        this.code = code;
        this.errorMessage = code.getErrorMessage();
    }

    public AntivirusException(AntivirusResponseCode code, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public AntivirusException(Exception exception) {
        super();
        this.errorMessage = exception.getMessage();
    }

    public AntivirusException(Throwable cause) {
        super(cause);
    }
}

