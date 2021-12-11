package com.dionisius.finalproject.categoryservice.api.dto.input;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryInput {
    private String name;
}
