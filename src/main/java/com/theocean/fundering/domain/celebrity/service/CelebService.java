package com.theocean.fundering.domain.celebrity.service;

import com.theocean.fundering.domain.celebrity.dto.CelebRequestDTO;
import com.theocean.fundering.domain.celebrity.dto.CelebFundingResponseDTO;
import com.theocean.fundering.domain.celebrity.dto.PageResponse;
import com.theocean.fundering.domain.celebrity.repository.CelebRepository;
import com.theocean.fundering.global.errors.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CelebService {
    private final CelebRepository celebRepository;
    @Transactional
    public void register(CelebRequestDTO celebRequestDTO) {
        try{
            celebRepository.save(celebRequestDTO.getEntity());
        }catch (Exception e){
            throw new Exception500("셀럽 등록 실패");
        }
    }

}
