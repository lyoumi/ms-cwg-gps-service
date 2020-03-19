package com.cwd.tg.gps.security;

public interface JwtTokenUtil {
    Boolean isTokenExpired(String token);
}
