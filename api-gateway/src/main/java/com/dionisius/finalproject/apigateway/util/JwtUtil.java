package com.dionisius.finalproject.apigateway.util;

import com.dionisius.finalproject.apigateway.exception.JwtTokenMalformedException;
import com.dionisius.finalproject.apigateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

@Component
public class JwtUtil {
//    @Value("${jwt.secret}")
    private String jwtSecret = "iniadalahasecretkeyyangdigunakanuntukgenerate";
    byte[] keyData = jwtSecret.getBytes(Charset.forName("UTF-8"));
    private final Key key = new SecretKeySpec(keyData, SignatureAlgorithm.HS256.getJcaName());


    public Claims getClaims(final String token){
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            System.out.println(e.getMessage()+" => "+e);
        }
        return null;
    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        }catch (SignatureException ex){
            System.out.println("Invalid JWT signature"+ex.getMessage());
            throw new JwtTokenMalformedException("Invalid JWT signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT token"+ex.getMessage());
            throw new JwtTokenMalformedException("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT toke"+ex.getMessage());
            throw new JwtTokenMalformedException("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token"+ex.getMessage());
            throw new JwtTokenMalformedException("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty"+ex.getMessage());
            throw new JwtTokenMissingException("JWT claims string is empty");
        }
    }
}

