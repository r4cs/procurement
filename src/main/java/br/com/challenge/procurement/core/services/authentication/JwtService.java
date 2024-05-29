package br.com.challenge.procurement.core.services.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private final String issuer = "FastSupply";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        System.out.println("*** generateToken() para userDetails: " + userDetails);
        return generateTokenn(new HashMap<>(), userDetails);
    }

    public String generateTokenn(Map<String, Object> extraClaims, UserDetails userDetails) {
        System.out.println("*** userDetails.getAuthorities em generateTokenn: " + userDetails.getAuthorities().toString());
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        extraClaims.put("roles", authorities);
        System.out.println("*** extra claims em genTokenn: " + extraClaims);
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        System.out.println("\n\n*** Iniciando DEBUG em JwtService - isTokenValid(): ");
        final String username = extractUsername(token);
        System.out.println("* username = extractUsername(token): " + username);
        final List<String> roles = extractClaim(token, claims -> claims.get("roles", List.class));
        System.out.println("roles: " + roles);
        List<GrantedAuthority> tokenAuthorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        System.out.println("* tokenAuthorities: " + tokenAuthorities);
        return (username.equals(userDetails.getUsername())) &&
                !isTokenExpired(token) &&
                userDetails.getAuthorities().containsAll(tokenAuthorities);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isFornecedor(String jwt) {
        System.out.println("\n\n*** Iniciando DEBUG em JwtService - isFornecedor(....): ");
        Claims claims = extractAllClaims(jwt);
        System.out.println("* claims = extractAllClaims(jwt): " + claims.toString());
        List<String> roles = claims.get("roles", List.class);
        System.out.println("*roles: " + roles.toString());
        return  roles.contains("SUPPLYER");
    }
}


