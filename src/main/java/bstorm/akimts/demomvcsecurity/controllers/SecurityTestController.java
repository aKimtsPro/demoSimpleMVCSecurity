package bstorm.akimts.demomvcsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SecurityTestController {

    // GET http://localhost:8080/test/noauth
    @GetMapping("/noauth")
    public String noauthAction(){
        return "security/noauth";
    }

    @GetMapping("/auth")
    public String authAction(){
        return "security/auth";
    }

    @GetMapping("/user")
    public String userAction(){
        return "security/user";
    }

    @GetMapping("/admin")
    public String adminAction(){
        return "security/admin";
    }
}
