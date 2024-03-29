package hello.hellospring.Token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    // API 호출 시, Member의 정보가 헤더에 담겨져 올텐데, 어떤 Member가 API를 요청했는지 조회하는 코드이다.
    public static String getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authentication information");
        }
        return authentication.getName();
    }
}
