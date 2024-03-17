package com.ssg.springex3.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private Long pno;
    private String name ;
    private LocalDate dueDate;
    private String price ;
    private int quantity ;

}
