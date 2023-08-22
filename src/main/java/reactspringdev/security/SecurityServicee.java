package reactspringdev.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

//로그인 서비스를 구현할 때 사용하면 좋다.
@Service
public class SecurityServicee {
    private static final String SECRET_KEY= "rkGU45258GGhiolLO2465TFY5345kGU45258GGhiolLO2465TFY5345";
    //로그인 할때 같이 사용
    public String createToken(String subject, long expTime){
        if(expTime <= 0){
            throw new RuntimeException("만료시간이 0보다 커야함!!");
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        //String 타입 리턴

        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();


    }

    //토큰 검증용도
    public String getToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
