package com.example.demospring.jwtrestapi2.jwt;

import com.example.demospring.jwtrestapi2.model.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    public static final long JWT_TOKEN_VALIDITY = 20 * 24 * 60 * 60; //20 day
    //secret key for generate token
    public static final String SECRET = "5465464bcd3967c1859c1c9eeb365dc8ebd62e782dbfa7e094b6e40404dcdb8b15f4bcd3967c1859c1c9eeb365dc8ebd62e782dbfa7e094b6e40404dcdb8b15f";
    //create token
    private String createToken(Map<String, Object> claim, String subject){
        return Jwts.builder()
                .setClaims(claim)  //set object (information user)
                .setSubject(subject) //set username or email ...
                .setIssuedAt(new Date(System.currentTimeMillis())) //createdAt
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) //expired token
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();  //sign with secret have key can access to use
    }
    //call it to use
    private Key getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //2. generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        AppUser appuser = (AppUser) userDetails;
        claims.put("userId", appuser.getUserId());
        return createToken(claims, userDetails.getUsername());
    }

    //3. retrieving any information from token we will need the secret key
    private Claims extractAllClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //4. extract a specific claim from the JWT token’s claims.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    //5. retrieve username from jwt token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //6. retrieve expiration date from jwt token
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //7. check expired token
    private Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    //8. validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
