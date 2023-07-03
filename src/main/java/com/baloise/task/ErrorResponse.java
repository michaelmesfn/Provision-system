package com.baloise.task;
import org.springframework.http.HttpStatus;
public class ErrorResponse {
    private int status;
    private String message;
    public static final String INTERMEDIARYSHARE_RANGE_ERROR = "Range of the Intermediary Share should be between 5 and 25";
    public ErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
