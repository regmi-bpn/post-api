package com.postapi.constant;

public enum ErrorMessage {
    //
    UNAUTHORIZED("AU001", "You are not authorized."),
    // client validator
    CLIENT_WITH_GIVEN_EMAIL_PRESENT("CV001", "Client with the given username already present."),
    USER_TYPE_NOT_CLIENT("CV002", "userType not user"),

    // client service
    OTP_TIME_EXPIRED("CS001", "Otp period has expired"), OTP_RESEND_EXCEEDED_WITH_TIME("CS002"),
    OTP_RESEND_EXCEEDED("CS003"), NOT_VALID_USER_NAME("CS004", "User with the given credential can not be found."),
    OTP_CAN_NOT_BE_SEND("CS005", "Otp can not be sent."), NOT_VALID_EMAIL("CS006", "Provided email is not valid"),
    NOT_VALID_ID("CS007", "Can not find user from the given credential."),
    PASSWORD_CONFIRM_PASSWORD_DOES_NOT_MATCH("CS007", "Password and confirm password mismatched"),
    OTP_GUESS_OVER("CS008", "Otp guess over"), OTP_INCORRECT("CS009"),
    INVALID_LOGIN_CREDENTIAL("CS010", "Provided credential is not valid."),
    USER_ALREADY_ADDED_INFORMATION("CS011", "User has already added information"),
    CAN_NOT_FIND_CLIENT_BY_DEVICE_IDENTIFIER("CS012", "Can not find client by device identifier."),
    USER_WITH_DEVICE_IDENTIFIER_REGISTERED("CS014", "Client with the given device identifier already registered."),
    REGISTRATION_TYPE_DOES_NOT_MATCH("CS015"),NOT_VALID_USER_ID("CS016","Not valid user id"),

    //NewsFeed
    MESSAGE_EMPTY("NF001","Empty message"),
    NOT_VALID_CREDENTIAL("NF002","Not valid credential"),
    USER_POST_NOT_AVAILABLE("NF003","User has not post any newsfeed"),
    INVALID_NEWSFEED_ID("NF004","Invalid NewsFeed id"),

    //comment
    COMMENT_ID_NOT_VALID("C001","Comment id not available"),
    // company branch
    COMPANY_BRANCH_ID_NOT_VALID("CBS001", "Can not find company branch with the given information."),
    BRANCH_WITH_GIVEN_NAME_PRESENT("CBS002", "Branch with the given name is already present."),

    // company service
    COMPANY_SERVICE_ID_NOT_VALID("CSS001", "Can not find company service with the given information."),
    SERVICE_IMAGE_NOT_PRESENT("CSS002", "Service image is not present."),
    SERVICE_NAME_ALREADY_PRESENT("CSS003", "Service name is already present."),







    ;

    String code;
    String message;

    ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorMessage(String code) {
        this.code = code;
        this.message = null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
