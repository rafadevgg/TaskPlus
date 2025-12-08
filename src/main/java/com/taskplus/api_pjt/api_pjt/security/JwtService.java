package com.taskplus.api_pjt.api_pjt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTime = currentTimeMillis + expiration;

        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(expirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            boolean isUsernameValid = username.equals(userDetails.getUsername());
            boolean isTokenNotExpired = !isTokenExpired(token);

            System.out.println("Token validation - Username match: " + isUsernameValid + ", Not expired: " + isTokenNotExpired);

            return isUsernameValid && isTokenNotExpired;
        } catch (Exception e) {
            System.err.println("Token validation error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            Date expiration = extractExpiration(token);
            Date now = new Date();
            boolean expired = expiration.before(now);

            System.out.println("Token expiration check - Expires at: " + expiration + ", Now: " + now + ", Expired: " + expired);

            return expired;
        } catch (Exception e) {
            System.err.println("Token expiration check error: " + e.getMessage());
            return true;
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            System.err.println("Error parsing token: " + e.getMessage());
            throw e;
        }
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}