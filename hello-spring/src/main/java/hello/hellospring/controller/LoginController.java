package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(originPatterns = "http://localhost:8080")
@RestController
@RequestMapping("/api")
@Controller
public class LoginController {
    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/insert")
    public void login(@RequestParam String email, @RequestParam String password) {
        System.out.println("ID : " + email + "PW : " + password);

       /* Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);*/
    }
}
