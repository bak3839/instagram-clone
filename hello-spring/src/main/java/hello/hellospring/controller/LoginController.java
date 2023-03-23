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
    public TokenInfo login(@RequestBody HashMap<String, String> requestJsonHashMap) {
        String Id = requestJsonHashMap.get("id");
        String Pw = requestJsonHashMap.get("password");

        //Optional<Member> result = memberService.login(Id, Pw);
        TokenInfo result = memberService.login2(Id, Pw);

        Cookie cookie1 = new Cookie("accessToken", result.getAccessToken());
        Cookie cookie2 = new Cookie("refreshToken", result.getRefreshToken());

        cookie1.setPath("/");
        cookie2.setPath("/");
        cookie1.setSecure(true);
        cookie2.setSecure(true);
        cookie1.setMaxAge(3600);
        cookie2.setMaxAge(3600);
        cookie1.setHttpOnly(true);
        cookie2.setHttpOnly(true);

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        System.out.println(result);
        System.out.println("ID : " + Id + " PW : " + Pw);
        return result;
    }
}
