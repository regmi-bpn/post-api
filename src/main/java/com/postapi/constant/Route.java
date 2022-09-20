package com.postapi.constant;

public class Route {

    //User

    public static final String USER = "/user";
    public static final String USER_RESISTER_EMAIL = USER + "/register-email";
    public static final String RESEND_OTP = USER + "/resend-otp";
    public static final String VALIDATE_OTP = USER + "/validate-otp";
    public static final String ADD_INFORMATION = USER + "/add-information";
    public static final String LOGIN = USER + "/login";
    public static final String INFORMATION = USER + "/information";
    public static final String LOGOUT = USER + "/logout";

    //NewsPost
    public static final String NEWS_FEED = "/newsfeed";
    public static final String POST_NEWS_FEED = NEWS_FEED + "/post-news-feed";
    public static final String UPDATE_NEWS_FEED = NEWS_FEED + "/news-feed";
    public static final String DELETE_NEWS_FEED = NEWS_FEED + "/news-feed/{id}";
    public static final String GET_USER_NEWS_FEED = NEWS_FEED + "/user-news-feed";
    public static final String USER_NEWS_FEED = NEWS_FEED + "/news-feed";

    //comment
    public static final String COMMENT = NEWS_FEED + "/comment";
    public static final String ADD_COMMENT = COMMENT + "/add-comment";
    public static final String UPDATE_COMMENT = COMMENT + "/comment";
    public static final String GET_COMMENT = COMMENT + "/comment";
    public static final String DELETE_COMMENT = COMMENT + "/comment/{id}";

    //reply
    public static final String REPLY = "/reply";
    public static final String ADD_REPLY = REPLY + "/add-reply";
    public static final String UPDATE_REPLY = REPLY + "/update-reply";
    public static final String EDIT_REPLY = REPLY + "/reply";
    public static final String DELETE_REPLY = REPLY + "/reply/{id}";


}
