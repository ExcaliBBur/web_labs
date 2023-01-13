package se.ifmo.lab4.securingweb;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String SECRET_KEY = "4E635266556A586E3272357538782F413F442A472D4B6150645367566B597033";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username));
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}