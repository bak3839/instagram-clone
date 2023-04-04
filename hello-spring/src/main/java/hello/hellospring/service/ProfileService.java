package hello.hellospring.service;

import hello.hellospring.domain.Profile;
import hello.hellospring.repository.JdbcProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
     private JdbcProfileRepository ProfileRepository;

//     private Profile edit(Profile profile) {
//
//     }
}
