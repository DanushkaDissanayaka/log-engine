package com.logger.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class JwtTokenUtil {

    public static String generateTwtToken(User user, long jwtExpireTIme){
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpireTIme*60*1000))
                .withClaim("role", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(getJwtAlgorithn());
    }


    public static UsernamePasswordAuthenticationToken  getAuthenticationToken(String authHeader){
        String token = authHeader.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(getJwtAlgorithn()).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("role").asArray(String.class);

        Collection<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        stream(roles).forEach(role -> {
            authoritys.add(new SimpleGrantedAuthority(role));
        });

        return new UsernamePasswordAuthenticationToken(username, null, authoritys);
    }

    public static Algorithm getJwtAlgorithn(){
        return Algorithm.HMAC256("secret".getBytes());
    }
}
