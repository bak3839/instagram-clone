package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Profile {
    private Long profileNum;
    private byte[] imageName;
    private String message;
    private String link;
    private Long member_memrberNum;
}
