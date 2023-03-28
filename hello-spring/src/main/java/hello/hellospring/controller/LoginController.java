package hello.hellospring.controller;

import hello.hellospring.Dto.TokenInfo;
import hello.hellospring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    public String login(@RequestBody HashMap<String, String> requestJsonHashMap) {
        String Id = requestJsonHashMap.get("id");
        String Pw = requestJsonHashMap.get("password");

        //Optional<Member> result = memberService.login(Id, Pw);
        TokenInfo result = memberService.login2(Id, Pw);

        Cookie cookie = new Cookie("refreshToken", result.getRefreshToken());

        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(86400); // 1일
        cookie.setHttpOnly(true);

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.addCookie(cookie);

        System.out.println(result);
        System.out.println("ID : " + Id + " PW : " + Pw);
        return result.getAccessToken();
    }
}
