package com.sparta.post.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-z0-9]+", message = "알파벳 소문자와 숫자로 구성되어야 합니다.")
    private final String username;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "[A-Za-z0-9]+", message = "알파벳 대소문자와 숫자로 구성되어야 합니다.")
    private final String password;

    public SignupRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
