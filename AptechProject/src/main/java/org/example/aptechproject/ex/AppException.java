package org.example.aptechproject.ex;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
    private HttpStatus status;

    private AppException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static AppException notFound(String msg) {
        return new AppException(HttpStatus.NOT_FOUND, msg);
    }

    public static AppException badRequest(String msg) {
        return new AppException(HttpStatus.BAD_REQUEST, msg);
    }
    public static AppException existedRequest(String msg) {
        return new AppException(HttpStatus.CONFLICT, msg);
    }
}

