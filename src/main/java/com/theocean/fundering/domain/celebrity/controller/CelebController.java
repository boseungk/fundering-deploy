package com.theocean.fundering.domain.celebrity.controller;

import com.theocean.fundering.domain.celebrity.dto.CelebRequestDTO;
import com.theocean.fundering.domain.celebrity.dto.CelebFundingResponseDTO;
import com.theocean.fundering.domain.celebrity.dto.PageResponse;
import com.theocean.fundering.domain.celebrity.service.CelebService;
import com.theocean.fundering.global.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CelebController {
    private final CelebService celebService;
    @PostMapping("/celebs/register")
    public ResponseEntity<?> registerCeleb(@RequestBody @Valid CelebRequestDTO celebRequestDTO, Error error){
        celebService.register(celebRequestDTO);
        return ResponseEntity.ok(ApiUtils.success(null));
    }
    @GetMapping("/celebs/{celebId}/posts")
    public ResponseEntity<?> findAllPosting(@PathVariable Long celebId,
                                            @RequestParam Long postId,
                                            @PageableDefault(direction = Sort.Direction.DESC) Pageable pageable){
        PageResponse<CelebFundingResponseDTO> page = celebService.findAllPosting(celebId, postId, pageable);
        return ResponseEntity.ok(ApiUtils.success(page));
    }
}
