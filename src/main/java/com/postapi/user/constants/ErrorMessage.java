package com.postapi.user.constants;


import com.postapi.security.util.HelperUtil;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorMessage {
    private String timestamp;
    private String message;
    private String requestUrl;
    private int status;
    private String error;

    public ErrorMessage(String message, String requestUrl, int status, String error) {
        this.timestamp = HelperUtil.getLocalDateTimeOfUTC().toString();
        this.message = message;
        this.requestUrl = requestUrl;
        this.status = status;
        this.error = error;
    }
}
