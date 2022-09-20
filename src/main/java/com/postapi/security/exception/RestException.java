package com.postapi.security.exception;



import com.postapi.constant.ErrorMessage;
import lombok.Getter;

@Getter
public class RestException  extends RuntimeException{

    private final String code;
    private final String message;

    public RestException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public RestException(ErrorMessage message){
        super(message.getMessage());
        this.code = message.getCode();
        this.message = message.getMessage();
    }

    public RestException(ErrorMessage errorMessage, String message){
        super(message);
        this.code = errorMessage.getCode();
        this.message = message;
    }
}
