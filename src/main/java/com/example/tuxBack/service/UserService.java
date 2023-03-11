package com.example.tuxBack.service;

import com.example.tuxBack.domain.Role;
import com.example.tuxBack.dto.UserDto;
import com.example.tuxBack.domain.entity.UserEntity;
import com.example.tuxBack.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long joinUser(UserDto userDto) {
        // 비밀번호 암호화

        userDto.setPassword1(passwordEncoder.encode(userDto.getPassword1()));

        return userRepository.save(userDto.toEntity()).getUid();
    }

    @Transactional
    public boolean checkEmailDuplicate(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public boolean checkIdDuplicate(String id){
        return userRepository.existsById(id);
    }

    @Transactional
    public boolean checkStudentNumDuplicate(String studentNum){
        return userRepository.existsByStudentNum(studentNum);
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByEmail(userEmail);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@naver.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}
