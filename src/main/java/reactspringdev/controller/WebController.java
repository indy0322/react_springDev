package reactspringdev.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import reactspringdev.domain.*;
import reactspringdev.repository.ArticleRepository;
import reactspringdev.repository.UserUpdateDto;
import reactspringdev.service.ArticleService;
import reactspringdev.service.SecurityService;
import reactspringdev.service.SecurityServiceV1;
import reactspringdev.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;
    private final SecurityService securityService;
    private final ArticleService articleService;

    /*public WebController(UserService userService){
        this.userService = userService;
    }*/

    @Value("${jwt.token.secret}")//springframework(Lombok 아님), ${jwt.token.secret}은 application.properties의 값을 가져옴.
    private String key;

    @GetMapping("/api/hello")
    public List<String> Hello(){
        return Arrays.asList("서버 포트는 8080", "리액트 포트는 3000");
    }

    @PostMapping("/api/register")
    public String register(@RequestBody User user){
        try{
            User existUser = userService.search(user.getUserId());
            if(existUser != null){
                return "존재하는 아이디 입니다";
            }
            userService.save(user);
            return user.getUserId();
        }
        catch(Exception e){
            System.out.println(e);
            return "아이디, 비밀번호를 제대로 입력해주세요";
        }
    }

    @PostMapping("/api/searchUser")
    public User searchUser(@RequestBody SearchUser userId){ //@RequestBody String userId 같이 매개변수를 지정하면 안된다.(매개변수의 객체는 User 같이 따라 만들어 주어야 한다.)
        User findUser = userService.search(userId.getUserId());
        return findUser;
    }

    @PostMapping("/api/updateUser")
    public String updateUser(@RequestBody UserUpdateDto updateDto){
        return userService.update(updateDto);
    }

    @PostMapping("/api/deleteUser")
    public String deleteUser(@RequestBody SearchUser userId){ //@RequestBody String userId 같이 매개변수를 지정하면 안된다.(매개변수의 객체는 User 같이 따라 만들어 주어야 한다.)
        return userService.delete(userId.getUserId());
    }

    @PostMapping("/api/login")
    public String login(@RequestBody User user){
        try{
            User findUser = userService.search(user.getUserId());
            if(findUser != null){
                String pass = String.valueOf(findUser.getUserPasswd());
                String pass2 = String.valueOf(user.getUserPasswd());
                if(pass.equals(pass2)){
                    log.info("사용자 비밀번호: {}",findUser.getUserPasswd());
                    log.info("입력한 비밀번호: {}",user.getUserPasswd());
                    return securityService.createToken(user.getUserId(),key);
                }
                else{
                    log.info("사용자 비밀번호: {}",findUser.getUserPasswd());
                    log.info("입력한 비밀번호: {}",user.getUserPasswd());
                    return "비빌번호가 다릅니다.";
                }
            }
            else{
                return "존재하지 않는 사용자 입니다.";
            }
        }catch (Exception e){
            log.error("로그인 에러: {}",e);
            return "로그인에 실패했습니다.";
        }
    }

    @PostMapping("/api/article/save")
    //'Content-type'가 'multipart/form-data'형태로 전송된 것은 @RequestBody로 받을 수 없다. @RequestPart는 'multipart/form-data'형태로 전송 된것을 받을 때 사용한다.(@RequestPart대신에 @RequestParam을 사용할 수 있다. 그리고 어노데이션에 (value = "데이터 키 이름")을 붙여 주어야한다. )
    //ArticleDTO같이 여러 데이터를 묶음 형태의 객체로 전송 받고 싶으면 @ModelAttribute를 사용한다.
    //다중 파일을 전송 받고 싶으면 이 같은 코드를 사용하면 된다. @RequestPart(value = "image", required = false) List<MultipartFile> articleImage, MultipartHttpServletRequest request, @RequestPart(value = "image", required = false) MultipartFile[] articleImage
    public String articleSave(@RequestPart(value = "image", required = false) List<MultipartFile> articleImage, @RequestPart(value = "title") String articleTitle, @RequestPart(value = "userId") String userId, @RequestPart(value = "contents", required = false) String contents){

        System.out.println(articleImage);
        System.out.println(articleImage.size());
        /*System.out.println(articleImage);
        log.info("multipartfile : {} ",articleImage);*/
        System.out.println(articleTitle);
        System.out.println(contents);
        System.out.println(userId);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd.hh:mm:ss");
        System.out.println(dateFormat.format(date));

        try{
            Article article = new Article();
            article.setContents(contents);
            article.setUserId(userId);
            if(articleTitle != null){
                article.setTitle(articleTitle);
            }
            if(articleImage != null){
                String[] imageArray = new String[articleImage.size()];
                int index=0;
                for(MultipartFile i: articleImage) {
                    String base64Image = Base64.getEncoder().encodeToString(i.getBytes());
                    imageArray[index] = base64Image;
                    index++;
                    //article.setImage(base64Image);
                }
                log.info("배열0 : {}",imageArray[0]);
                log.info("배열1 : {}",imageArray[1]);
                log.info("배열2 : {}",imageArray[2]);
                //String base64Image = Base64.getEncoder().encodeToString(articleImage.getBytes());
                article.setImage(Arrays.toString(imageArray));
            }
            //article.setTime(dateFormat.format(date));
            //articleService.save(article);
        }catch (IOException e){
            e.printStackTrace();
        }


        return "ok";
    }

    @PostMapping("/api/article/findimage")
    public Article findImage(@RequestBody findImage findImage){
        Article findArticle =  articleService.findImage(findImage.getUserId(),findImage.getTime());
        //String encodeImage = findArticle.getImage();
        return findArticle;
    }

    @PostMapping("/articleList")
    public Article[] articles(){
        Article[] articles = articleService.allArticle();
        return articles;
    }

    @PostMapping("/pageauth")
    public ResponseData pageauth(HttpServletRequest request, Authentication authentication){
        final String authoriztion = request.getHeader(HttpHeaders.AUTHORIZATION);
        //log.info("헤더 토큰 : {}",authoriztion);
        ResponseData responseData = new ResponseData();
        responseData.setResponsedata(authentication.getName());

        return responseData;
    }

}
