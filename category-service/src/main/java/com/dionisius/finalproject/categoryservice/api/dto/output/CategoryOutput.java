package com.dionisius.finalproject.categoryservice.api.dto.output;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryOutput {
    private Integer id;
    private String name;
}
