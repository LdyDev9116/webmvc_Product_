package com.ssg.springex3.mapper;

import com.ssg.springex3.domain.ProductVo; // 변경됨
import com.ssg.springex3.dto.PageRequestDTO;

import java.util.List;

public interface ProductMapper {
    String getTime();

    void insert(ProductVo productVo); // 변경됨

    List<ProductVo> selectAll(); // 변경됨

    ProductVo selectOne(Long pno); // 변경됨

    void delete(ProductVo productVo); // 변경됨

    void update(ProductVo productVo); // 변경됨

    List<ProductVo> selectList(PageRequestDTO pageRequestDTO); // 변경됨

    int getCount(PageRequestDTO pageRequestDTO); // 파라미터 타입은 동일하지만, 사용하는 context는 변경될 수 있음.
}
