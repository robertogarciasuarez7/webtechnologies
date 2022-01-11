package es.uvt.lbd.wt.enlacesrest.security;

import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import es.uvt.lbd.wt.enlacesrest.config.Properties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider {
  private static final String AUTHORITIES_KEY = "auth";

  @Autowired private Properties properties;

  public boolean validateToken(String authToken) {

    Jwts.parserBuilder()
        .setSigningKey(properties.getJwtSecretKey())
        .build()
        .parseClaimsJws(authToken);

    return true;
  }

  public Authentication getAuthentication(String authToken) {
    Claims claims =
        Jwts.parserBuilder()
            .setSigningKey(properties.getJwtSecretKey())
            .build()
            .parseClaimsJws(authToken)
            .getBody();
    GrantedAuthority authority = new SimpleGrantedAuthority(claims.get(AUTHORITIES_KEY).toString());
    Collection<GrantedAuthority> authorities = Collections.singleton(authority);
    User user = new User(claims.getSubject(), "", authorities);
    return new UsernamePasswordAuthenticationToken(user, authToken, authorities);
  }

  public String createToken(Authentication authentication) {
    String authority = authentication.getAuthorities().iterator().next().toString();

    long now = (new Date()).getTime();
    Date validity = new Date(now + (properties.getJwtValidity() * 1000));

    Key key =
        new SecretKeySpec(
            Base64.getDecoder().decode(properties.getJwtSecretKey()),
            SignatureAlgorithm.HS512.getJcaName());

    return Jwts.builder()
        .setSubject(authentication.getName())
        .claim(AUTHORITIES_KEY, authority)
        .signWith(key)
        .setExpiration(validity)
        .compact();
  }
}
