package com.theocean.fundering.domain.news.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class NewsRequest {

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class saveDTO {
        private final String title;
        private final String content; // 마크다운 형식의 문자열이 저장됨
        private final boolean viewRestriction;
    }
}
