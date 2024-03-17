package com.ssg.springex3.service;


import com.ssg.springex3.domain.ProductVo;
import com.ssg.springex3.dto.PageRequestDTO;
import com.ssg.springex3.dto.PageResponseDTO;
import com.ssg.springex3.dto.ProductDTO;
import com.ssg.springex3.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // 이 클래스를 스프링 서비스로 선언
@Log4j2 // 로그를 위한 Lombok 어노테이션
@RequiredArgsConstructor // 필요한 final 필드를 포함한 생성자를 자동으로 생성 (Lombok 어노테이션)
public class ProductServiceImpl implements ProductService {

    // MyBatis의 TodoMapper와 ModelMapper에 대한 의존성 주입
    private final ProductMapper productMapper; // MyBatis 매퍼
    private final ModelMapper modelMapper; // 객체 매핑을 위한 ModelMapper

    @Override // TodoService 인터페이스의 메소드 재정의
    public void register(ProductDTO productDTO) { // DTO를 받아서 VO로 변환 후 DB에 삽입
        log.info(modelMapper); // ModelMapper 정보 로깅
        ProductVo productVo = modelMapper.map(productDTO, ProductVo.class); // DTO를 VO로 매핑
        log.info(productVo); // 변환된 VO 정보 로깅
        productMapper.insert(productVo); // 매퍼를 통해 VO를 DB에 삽입
    }


    @Override
    public ProductDTO getOne(Long pno){
        ProductVo productVo = productMapper.selectOne(pno);

        if (productVo != null) {
            return modelMapper.map(productVo, ProductDTO.class);
        }
        return null;
    }


    @Override
    public void deleteOne(ProductDTO productDTO) {
        ProductVo productVo = modelMapper.map(productDTO, ProductVo.class );
        productMapper.delete(productVo);
    }

    @Override
    public void modify(ProductDTO productDTO) {
        ProductVo productVo = modelMapper.map(productDTO, ProductVo.class );
        productMapper.update(productVo);
    }
    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        // 상품 목록을 가져오는 부분, TodoVo 대신 ProductVo 사용
        List<ProductVo> vos = productMapper.selectList(pageRequestDTO);

        // ProductVo 목록을 ProductDTO 목록으로 변환
        List<ProductDTO> dtos = vos.stream()
                .map(vo -> modelMapper.map(vo, ProductDTO.class)) // 여기서는 ProductVo를 ProductDTO로 매핑
                .collect(Collectors.toList());

        // 전체 상품 개수를 가져옴
        int total = productMapper.getCount(pageRequestDTO);

        // 페이지 응답 DTO 생성, TodoDTO 대신 ProductDTO 사용
        PageResponseDTO<ProductDTO> pageResponseDTO = PageResponseDTO.<ProductDTO>All() // 메서드 체인에서 TodoDTO 대신 ProductDTO 사용
                .dtoList(dtos)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();


        return pageResponseDTO;
    }





}
