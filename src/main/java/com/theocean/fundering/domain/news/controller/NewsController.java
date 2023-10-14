package com.theocean.fundering.domain.news.controller;

import com.theocean.fundering.domain.news.dto.NewsRequest;
import com.theocean.fundering.domain.news.dto.NewsResponse;
import com.theocean.fundering.domain.news.service.NewsService;
import com.theocean.fundering.global.jwt.userInfo.CustomUserDetails;
import com.theocean.fundering.global.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    // (기능) 펀딩 업데이트 작성
    @PostMapping("/posts/{postId}/updates")
    public ResponseEntity<?> createNews(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable long postId, @RequestBody NewsRequest.saveDTO request) {

        Long writerId = 1L;

        // FundingUpdateRequest는 제목, 내용(마크다운), 후원자에게만 공개할 지 여부 등을 필드로 가집니다.
        newsService.createNews(writerId, postId, request);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // (기능) 펀딩 업데이트 조회
    @GetMapping("/posts/{postId}/updates")
    public ResponseEntity<?> getUpdates(@PathVariable long postId,
                                         @RequestParam(required = false, defaultValue = "0") long cursor,
                                         @RequestParam(required = false, defaultValue = "6") int pageSize) {

        NewsResponse.findAllDTO response = newsService.getNews(postId, cursor, pageSize);

        return ResponseEntity.ok(ApiUtils.success(response));
    }

}
