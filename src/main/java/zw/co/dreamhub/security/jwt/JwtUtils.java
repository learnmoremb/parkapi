package zw.co.dreamhub.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import zw.co.dreamhub.config.env.InfoEnv;
import zw.co.dreamhub.security.services.UserDetailsImpl;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/4/2021
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {

    private final InfoEnv env;


    private Key getSigningKey() {
        byte[] keyBytes = env.security().jwt().secret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusMillis(env.security().jwt().jwtExpirationMs())))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusMillis(env.security().jwt().jwtExpirationMs())))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(env.security().jwt().secret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validateJwtToken(String authToken) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(env.security().jwt().secret())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token : {}", (Object) e.getStackTrace());
        } catch (ExpiredJwtException e) {
            log.info("JWT token is expired : {}", (Object) e.getStackTrace());
        } catch (UnsupportedJwtException e) {
            log.info("JWT token is unsupported : {}", (Object) e.getStackTrace());
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty : {}", (Object) e.getStackTrace());
        }

        return false;
    }

}
