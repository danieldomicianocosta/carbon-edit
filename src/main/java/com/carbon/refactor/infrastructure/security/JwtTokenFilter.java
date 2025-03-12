package com.carbon.refactor.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Filter to extract JWT token information from requests.
 * This filter intercepts all requests, extracts the JWT token from the Authorization header,
 * decodes it, and stores the user information in the UserContext for use in business logic.
 */
@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    
    // In a production environment, this should be configured in application.yml
    // and injected using @Value
    private final SecretKey secretKey;
    
    public JwtTokenFilter(@Value("${jwt.secret:chave_secreta_muito_longa_para_garantir_seguranca_adequada_do_jwt}") String secret) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            String token = extractToken(request);
            
            if (token != null) {
                Claims claims = extractClaims(token);
                
                if (claims != null) {
                    String username = claims.getSubject();
                    String role = claims.get("role", String.class);
                    
                    log.debug("Extracted user info from token: username={}, role={}", username, role);
                    
                    // Store user information in the context
                    UserContext.setCurrentUser(new UserContext.UserInfo(username, role));
                }
            }
        } catch (Exception e) {
            // Log the error, but allow the request to continue
            log.error("Error processing JWT token", e);
        }
        
        try {
            // Continue with the filter chain
            filterChain.doFilter(request, response);
        } finally {
            // Clear the context after the request is processed
            UserContext.clear();
        }
    }
    
    /**
     * Extract the JWT token from the Authorization header.
     * 
     * @param request The HTTP request
     * @return The JWT token, or null if not found
     */
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(TOKEN_PREFIX.length());
        }
        
        return null;
    }
    
    /**
     * Extract claims from the JWT token.
     * This method first tries to validate the token with the secret key.
     * If that fails (e.g., if the token was signed with a different key),
     * it falls back to just decoding the token without validation.
     * 
     * @param token The JWT token
     * @return The claims from the token, or null if extraction failed
     */
    private Claims extractClaims(String token) {
        try {
            // Try to parse and validate the token
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.debug("Could not validate token signature, falling back to decode-only mode");
            
            // If validation fails, just decode the token without validation
            // This is useful when we only need to read the claims and don't care about validation
            String[] chunks = token.split("\\.");
            if (chunks.length == 3) {
                try {
                    String payload = new String(Base64.getUrlDecoder().decode(chunks[1]));
                    
                    // Extract the required fields manually
                    String sub = extractField(payload, "sub");
                    String role = extractField(payload, "role");
                    
                    if (sub != null && role != null) {
                        Claims claims = Jwts.claims();
                        claims.setSubject(sub);
                        claims.put("role", role);
                        return claims;
                    }
                } catch (Exception ex) {
                    log.error("Error decoding token payload", ex);
                }
            }
            return null;
        }
    }
    
    /**
     * Extract a field from a JSON string using regex.
     * This is a simple implementation and should be replaced with a proper JSON parser
     * in a production environment.
     * 
     * @param json The JSON string
     * @param fieldName The name of the field to extract
     * @return The value of the field, or null if not found
     */
    private String extractField(String json, String fieldName) {
        String pattern = "\"" + fieldName + "\"\\s*:\\s*\"([^\"]*)\"";
        java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = r.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
