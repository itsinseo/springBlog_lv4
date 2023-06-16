package com.sparta.post.dto;

import lombok.Getter;

@Getter
public class DeleteResponseDto {
    private final boolean success;

    public DeleteResponseDto() {
        success = true;
    }
}
