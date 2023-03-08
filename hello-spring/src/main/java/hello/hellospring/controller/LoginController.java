package hello.hellospring.controller;

import hello.hellospring.Dto.TokenInfo;
import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin(originPatterns = "http://localhost:8080")
@RestController
@RequestMapping("/api")
@Controller
public class LoginController {
    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/insert")
    public TokenInfo login(@RequestBody HashMap<String, String> requestJsonHashMap) {
        String Id = requestJsonHashMap.get("id");
        String Pw = requestJsonHashMap.get("password");

        //Optional<Member> result = memberService.login(Id, Pw);
        TokenInfo result = memberService.login2(Id, Pw);

        System.out.println(result);
        System.out.println("ID : " + Id + " PW : " + Pw);
        return result;
    }
}
