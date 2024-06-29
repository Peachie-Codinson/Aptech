package org.example.aptechproject.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private int httpCode;
    private String message;
    private Map<String, String> messages;

    public ErrorResponse(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public ErrorResponse(int httpCode, String message, Map<String, String> messages) {
        this.httpCode = httpCode;
        this.message = message;
        this.messages = messages;
    }

    public ErrorResponse(int httpCode, Map<String, String> messages) {
        this.httpCode = httpCode;
        this.messages = messages;
    }
}
