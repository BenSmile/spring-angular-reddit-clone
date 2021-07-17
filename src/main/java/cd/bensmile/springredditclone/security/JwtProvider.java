package cd.bensmile.springredditclone.security;

import cd.bensmile.springredditclone.exception.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {


    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex) {
            throw new SpringRedditException("Exception occured while loading keystore");
        }
    }

    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
            .setSubject(principal.getUsername())
            .signWith(getPrivateKey())
            .compact();
    }

    private Key getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (Exception ex) {
            throw new SpringRedditException("Exception occured while retrieving keystore");
        }
    }

    public boolean validateToken(String token) {
        parser().setSigningKey(getPublicKey()).parseClaimsJws(token);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return  keyStore.getCertificate("springblog").getPublicKey();
        } catch (Exception ex) {
            throw new SpringRedditException("Exception occured while retrieving public key");
        }

    }

    public String getUsernameFromJwt(String jwt){
        Claims claims = parser().setSigningKey(getPrivateKey())
            .parseClaimsJws(jwt)
            .getBody();
        return claims.getSubject();
    }
}
