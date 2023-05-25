package com.team2.findmytown.service;


import com.team2.findmytown.domain.entity.UserEntity;
import com.team2.findmytown.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity findUser = userRepository.findByEmail(email);

        if(findUser != null){
            UserDetailsImpl userDetails = new UserDetailsImpl(findUser);
            return  userDetails;
        }

        return null;
    }
}
