package reactspringdev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/functions")
public class FunctionController {

    @PostMapping("/review")
    public ResponseEntity<String> writeReview(Authentication authentication){
        //authentication.getName()이 JwtFilter의 UsernamePasswordAuthenticationToken의 userName(username)(예) /users/login?userName=kim 에서 kim)값이 들어간다.
        return ResponseEntity.ok().body(authentication.getCredentials() + "님의 리뷰 등록이 완료 되었습니다.");
    }
}
