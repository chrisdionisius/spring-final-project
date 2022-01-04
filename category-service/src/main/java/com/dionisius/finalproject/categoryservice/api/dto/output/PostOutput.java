package com.dionisius.finalproject.categoryservice.api.dto.output;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostOutput {

    private Integer id;
    private Long user_id;
    private String category_id;
    private String title;
    private String content;
    private Date updatedAt;
    private Date createdAt;
}
