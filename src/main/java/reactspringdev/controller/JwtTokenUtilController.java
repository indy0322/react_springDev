package reactspringdev.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactspringdev.security.JwtTokenUtil;

@RestController
public class JwtTokenUtilController {

    @Value("${jwt.token.secret}")//springframework(Lombok 아님), ${jwt.token.secret}은 application.properties의 값을 가져옴.
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60L;

    @GetMapping("/jwttoken")
    public String createToken(@RequestParam("userName") String userName){
        String token = JwtTokenUtil.createToken(userName, key, expireTimeMs);

        return token;
    }
}
