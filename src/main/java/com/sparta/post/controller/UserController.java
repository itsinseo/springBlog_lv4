package com.sparta.post.controller;

import com.sparta.post.dto.LoginRequestDto;
import com.sparta.post.dto.LoginResponseDto;
import com.sparta.post.dto.SignupRequestDto;
import com.sparta.post.dto.SignupResponseDto;
import com.sparta.post.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API
    @PostMapping("/user/signup")
    public SignupResponseDto signup(@RequestBody @Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : fieldErrors) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new SignupResponseDto("회원 가입 실패", HttpStatus.BAD_REQUEST.value());
        }

        return userService.signup(signupRequestDto);
    }

    // 로그인 API
    @PostMapping("/user/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse res) {
        return userService.login(loginRequestDto, res);
    }
}
