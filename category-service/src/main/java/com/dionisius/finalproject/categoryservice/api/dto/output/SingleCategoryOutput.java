package com.dionisius.finalproject.categoryservice.api.dto.output;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SingleCategoryOutput {
    private Integer id;
    private String name;
    private List<PostOutput> postOutput;
}
