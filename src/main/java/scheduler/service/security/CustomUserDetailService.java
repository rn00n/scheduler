package scheduler.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import scheduler.advice.exception.CUserNotFoundException;
import scheduler.repository.UserJpaRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepository.findByUid(userPk).orElseThrow(CUserNotFoundException::new);
    }

}

