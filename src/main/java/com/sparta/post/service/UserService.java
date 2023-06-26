package com.sparta.post.service;

import com.sparta.post.dto.LoginRequestDto;
import com.sparta.post.dto.LoginResponseDto;
import com.sparta.post.dto.SignupRequestDto;
import com.sparta.post.dto.SignupResponseDto;
import com.sparta.post.entity.User;
import com.sparta.post.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();

        // username 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자명입니다.");
        }

        String password = signupRequestDto.getPassword();
        User user = new User(username, password);
        userRepository.save(user);

        return new SignupResponseDto("회원가입 성공", HttpStatus.OK.value());
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }
}
