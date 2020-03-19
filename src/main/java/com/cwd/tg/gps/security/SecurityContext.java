package com.cwd.tg.gps.security;

public final class SecurityContext {

    private static UserToken userToken;

    public static UserToken getUserToken() {
        return userToken;
    }

    public static void setUserToken(UserToken userToken) {
        SecurityContext.userToken = userToken;
    }

}
