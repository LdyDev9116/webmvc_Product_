package com.ssg.springex3.service;

import com.ssg.springex3.dto.PageRequestDTO;
import com.ssg.springex3.dto.PageResponseDTO;
import com.ssg.springex3.dto.ProductDTO;

public interface ProductService {
    // 상품 등록
    void register(ProductDTO productDTO);

    // 특정 상품 조회 (메서드 시그니처는 주석으로 유지되지만, 필요 시 주석을 해제하고 사용할 수 있음)
     ProductDTO getOne(Long pno);

    // 상품 삭제
    void deleteOne(ProductDTO productDTO);

    // 상품 수정
    void modify(ProductDTO productDTO);

    // 상품 목록 조회
    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);
}