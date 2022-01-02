package com.dionisius.finalproject.postservice.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostInput {
    private String title;
    private String content;
}
