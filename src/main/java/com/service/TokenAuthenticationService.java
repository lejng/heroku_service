package com.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utils.JsonUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;

public class TokenAuthenticationService {
    private static final long EXPIRATION_TIME = 864000000; // 10 days
    private static final String SECRET = "ThisIsASecret";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        String token = TOKEN_PREFIX + " " + JWT;
        res.addHeader(HEADER_STRING, token);
        Map body = new HashMap<String, String>();
        body.put(HEADER_STRING, token);
        addTokenToBody(res, JsonUtils.mapToJson(body));
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET).
                    parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, new LinkedList<GrantedAuthority>()) : null;
        }
        return null;
    }

    public static void addTokenToBody(HttpServletResponse res, String body){
        try{
            res.getWriter().write(body);
            res.getWriter().flush();
            res.getWriter().close();
        }catch (IOException e){
            return;
        }
    }
}
