package reactspringdev.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

public class JwtTokenUtil {

    public static String getUserId(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("userId",String.class);
    }

    public static boolean isExpired(String token, String secretKey){
        //토큰의 만료시간이 지금보다 전 인가(예) 만료시간: 2023년 7월 21일 17시, 현재시간: 2023년 7월 21일 18시 => 토큰 만료됨.)
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public static String createToken(String userName, String key, long expireTimeMs){
        Claims claims = Jwts.claims(); // 일종의 Map 이다.
        claims.put("userName", userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }
}
