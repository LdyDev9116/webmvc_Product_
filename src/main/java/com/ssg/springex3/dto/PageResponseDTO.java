package com.ssg.springex3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@Getter
@ToString

public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;
    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;
    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;
    private List<E> dtoList;

    private boolean finished;

    @Builder(builderMethodName = "All")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList,
                           int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        // 마지막 페이지 번호 계산
        this.end = (int) (Math.ceil(this.page / 10.0)) * 10;
        // 시작 페이지 번호 계산
        this.start = this.end - 9;

        // 실제 마지막 페이지 번호 계산
        int last = (int) (Math.ceil((total / (double) size)));

        // 끝 페이지 번호가 실제 마지막 페이지 번호보다 크면 조정
        this.end = Math.min(end, last);

        // 이전 페이지 그룹의 존재 여부
        this.prev = this.start > 1;

        // 다음 페이지 그룹의 존재 여부
        this.next = this.total > this.end * this.size;


    }
}


