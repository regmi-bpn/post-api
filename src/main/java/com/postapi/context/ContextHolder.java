package com.postapi.context;

import java.util.List;

public class ContextHolder implements Runnable {
    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<>();
    private final Long userId;
    private final String username;
    private final String userType;
    private List<String> permissions;

    // public ContextHolder(Long userId, String username) {
    // this.userId = userId;
    // this.username = username;
    // }

    public ContextHolder(Long userId, String username, String userType, List<String> permissions) {
        this.userId = userId;
        this.username = username;
        this.userType = userType;
        this.permissions = permissions;
    }

    public ContextHolder(Long userId, String username, String userType) {
        this.userId = userId;
        this.username = username;
        this.userType = userType;
    }

    public static void set(Context context) {
        threadLocal.set(context);
    }

    public static void unset() {
        threadLocal.remove();
    }

    public static Context get() {
        return threadLocal.get();
    }

    @Override
    public void run() {
        set(new Context(userId, username, userType, permissions));
        new ContextHolderService().getContext();
    }
}
