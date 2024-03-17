package com.ssg.springex3.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductVo {
    private Long pno;
    private String name ;
    private LocalDate dueDate;
    private String price ;
    private int quantity ;

}
