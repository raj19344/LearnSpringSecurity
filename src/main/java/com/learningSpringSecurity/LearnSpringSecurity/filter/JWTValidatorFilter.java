package com.learningSpringSecurity.LearnSpringSecurity.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.learningSpringSecurity.LearnSpringSecurity.filter.JWTTokenGeneratorFilter.secretKey;

public class JWTValidatorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader("Authorization");
        if(jwt!=null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
            SecretKey secretKey2 = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            if (secretKey2 != null) {
                try {


                    Claims claims = Jwts.parser()
                            .verifyWith(secretKey2)
                            .build()
                            .parseSignedClaims(jwt)
                            .getPayload();

                    String username = String.valueOf(claims.get("username"));
                    String authority = String.valueOf(claims.get("authorities"));

                    System.out.println("\nExtracted role from JWT: " + authority);
                    System.out.println("\nExtracted Username from JWT: " + username);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    }catch (Exception e){
                    throw new BadCredentialsException("Invalid Token Received");
                }
                }
            }

        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/apiLogin");
    }
}
