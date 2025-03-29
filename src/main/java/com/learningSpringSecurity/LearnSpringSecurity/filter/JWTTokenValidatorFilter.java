package com.learningSpringSecurity.LearnSpringSecurity.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String jwt = request.getHeader("Authorization");
            if(Objects.nonNull(jwt)){
                try{
                    Environment env = getEnvironment();
                    if(Objects.nonNull(env)){
                        String secret = env.getProperty("JWT_SECRET","jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4");
                        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                        if(Objects.nonNull(secretKey) && jwt.startsWith("Bearer ")){
                            jwt = jwt.substring(7);
                            try{
                                Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload();

                                String username = String.valueOf(claims.get("username"));
                                String authorities = String.valueOf(claims.get("authorities"));

                                Authentication authentication = new UsernamePasswordAuthenticationToken(username,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                                SecurityContextHolder.getContext().setAuthentication(authentication);
                            }catch(Exception e){
                                throw new BadCredentialsException("Invalid credentials received");
                            }
                        }
                    }
                }catch(Exception e){
                    throw new BadCredentialsException("Invalid Token received");
                }
            }
            filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/account");
    }

}
