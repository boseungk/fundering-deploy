package com.theocean.fundering.domain.member.controller;

import com.theocean.fundering.domain.member.dto.EmailRequestDTO;
import com.theocean.fundering.domain.member.dto.MemberSettingRequestDTO;
import com.theocean.fundering.domain.member.dto.MemberSettingResponseDTO;
import com.theocean.fundering.domain.member.dto.MemberSignUpRequestDTO;
import com.theocean.fundering.domain.member.service.MemberService;
import com.theocean.fundering.global.jwt.userInfo.CustomUserDetails;
import com.theocean.fundering.global.utils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "MEMBER", description = "회원 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "이메일 중복 체크", description = "회원가입 시 사용 중인 이메일인지 확인한다.")
    @PostMapping("/signup/check")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<?> checkEmail(
            @RequestBody @Valid @Schema(implementation = EmailRequestDTO.class) final EmailRequestDTO emailRequestDTO,
            @Parameter(hidden = true) final Error error
    ){
        memberService.sameCheckEmail(emailRequestDTO.getEmail());
        return ApiResult.success(null);
    }

    @Operation(summary = "회원가입", description = "이메일, 닉네임, 비밀번호를 입력받아 회원가입한다.")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<?> signUp(
            @RequestBody @Valid @Schema(implementation = MemberSignUpRequestDTO.class) final MemberSignUpRequestDTO requestDTO,
            @Parameter(hidden = true) final Error error
    ){
        memberService.signUp(requestDTO);
        return ApiResult.success(null);
    }

    @Operation(summary = "회원정보 조회", description = "토큰을 기반으로 사용자의 회원정보를 조회한다.", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MemberSettingResponseDTO.class)))
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/setting")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<?> findAllUserSetting(
            @AuthenticationPrincipal final CustomUserDetails userDetails
    ){
        final var responseDTO = memberService.findAllUserSetting(userDetails.getId());
        return ApiResult.success(responseDTO);
    }

    @Operation(summary = "회원정보 수정", description = "토큰을 기반으로 사용자의 회원정보를 수정한다.")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/user/setting")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<?> updateUserSetting(
            @RequestBody @Valid @Schema(implementation = MemberSettingRequestDTO.class) final MemberSettingRequestDTO requestDTO,
            @RequestPart("thumbnail") final MultipartFile thumbnail,
            @AuthenticationPrincipal final CustomUserDetails userDetails,
            @Parameter(hidden = true) final Error error
    ){
        memberService.updateUserSetting(requestDTO, userDetails.getId(), thumbnail);
        return ApiResult.success(null);
    }

    @Operation(summary = "회원 탈퇴", description = "토큰을 기반으로 사용자의 회원정보를 삭제한다.")
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/user/setting/cancellation")
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<?> cancellationUser(
            @AuthenticationPrincipal final CustomUserDetails userDetails
    ){
        memberService.cancellationUser(userDetails.getId());
        return ApiResult.success(null);
    }
}