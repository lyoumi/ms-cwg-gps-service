package com.cwd.tg.gps.security.impl;

import com.cwd.tg.gps.exception.TokenExpirationException;
import com.cwd.tg.gps.exception.TokenValidationException;
import com.cwd.tg.gps.security.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtilImpl implements JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Boolean isTokenExpired(String token) {
        var expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    private  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        var claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            throw new TokenValidationException("Existing token is invalid", e);
        } catch (ExpiredJwtException e) {
            throw new TokenExpirationException("Existing token is expired", e);
        }
    }
}
