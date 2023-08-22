package reactspringdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactspringdev.security.SecurityServicee;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private SecurityServicee securityService;

    @GetMapping("/create/token")
    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject){
        String token = securityService.createToken(subject,(10*100*60));
        Map<String, Object> map =new LinkedHashMap<>();
        map.put("result",token);
        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token){
        String subject = securityService.getToken(token);
        Map<String, Object> map =new LinkedHashMap<>();
        map.put("result",subject);
        return map;
    }


}
