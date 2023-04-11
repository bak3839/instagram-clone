package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.Profile;
import hello.hellospring.repository.JdbcProfileRepository;
import hello.hellospring.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@CrossOrigin(originPatterns = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Controller
public class ProfileController {
    private final ProfileService profileService;
    private final JdbcProfileRepository profileRepository;

    @PostMapping("/upload")
    public Profile create(@RequestParam("imageName") MultipartFile imageName) throws IOException {
        Profile profile = new Profile();
        byte[] imageBytes = imageName.getBytes();
        Long n = 1L;
        profile.setLink(null);
        profile.setMessage(null);
        profile.setImageName(imageBytes);
        profile.setMember_memrberNum(n);

        return profileRepository.save(profile);
    }
}
