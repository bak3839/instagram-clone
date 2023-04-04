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
@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final ProfileService profileService;
    private final JdbcProfileRepository profileRepository;

    @PostMapping("/profile/edit")
    public Profile create(@RequestParam("message") String message,
                          @RequestParam("link") String link,
                          @RequestParam("imageName") MultipartFile imageName) throws IOException {
        Profile profile = new Profile();
        byte[] imageBytes = imageName.getBytes();

        profile.setLink(link);
        profile.setMessage(message);
        profile.setImageName(imageBytes);

        return profileRepository.save(profile);
    }
}
