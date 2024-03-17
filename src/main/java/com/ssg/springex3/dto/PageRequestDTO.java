package com.ssg.springex3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Builder  // 빌더 패턴을 사용할 수 있도록 지정
@Data  // Lombok을 사용하여 게터, 세터, toString 등을 자동으로 생성
@AllArgsConstructor  // 모든 필드를 인자로 받는 생성자 생성
@NoArgsConstructor  // 디폴트 생성자 생성
public class PageRequestDTO {
    @Builder.Default  // 빌더 패턴에서 기본값을 설정할 수 있도록 함
    @Min(value = 1)  // 최소값이 1인 제약 조건 설정
    @Positive  // 양수여야 함을 나타내는 제약 조건 설정
    private int page = 1;  // 기본값이 1인 page 필드 선언

    @Builder.Default  // 빌더 패턴에서 기본값을 설정할 수 있도록 함
    @Min(value = 10)  // 최소값이 10인 제약 조건 설정
    @Max(value = 100)  // 최대값이 100인 제약 조건 설정
    @Positive  // 양수여야 함을 나타내는 제약 조건 설정
    private int size = 10;  // 기본값이 10인 size 필드 선언

    private String link;  // 링크 정보를 저장하는 필드
    private String[] types;  // 타입 정보를 저장하는 배열 필드
    private String keyword;  // 검색 키워드를 저장하는 필드
    private boolean finished;  // 완료 여부를 저장하는 필드
    private LocalDate from;  // 시작 날짜를 저장하는 필드
    private LocalDate to;  // 종료 날짜를 저장하는 필드

    // 페이지당 건너뛸 행의 수를 계산하는 메서드
    public int getSkip() {
        return (page - 1) * 10;
    }

    // 링크 정보를 반환하는 메서드
    public String getLink() {
        if (link == null) {  // 링크가 없는 경우
            StringBuilder builder = new StringBuilder();  // StringBuilder 객체 생성
            builder.append("page=" + this.page);  // 페이지 정보 추가
            builder.append("&size=" + this.size);  // 크기 정보 추가
            link = builder.toString();  // 링크 생성
        }
        return link;  // 링크 반환
    }

    // 해당하는 타입이 존재하는지 확인하는 메서드
    public boolean checkType(String type) {
        if (types != null) {  // 타입 배열이 비어있지 않은 경우
            for (String t : types) {  // 모든 타입에 대해 반복
                if (t.equals(type)) {  // 해당 타입이 존재하는 경우
                    return true;  // true 반환
                }
            }
        }
        return false;  // 타입이 존재하지 않는 경우 false 반환
    }
}
