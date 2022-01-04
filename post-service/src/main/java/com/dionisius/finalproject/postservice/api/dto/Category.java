package com.dionisius.finalproject.postservice.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {
    private Integer id;
    private String name;
}
