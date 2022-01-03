package com.dionisius.finalproject.postservice.api.dto;

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

    private List<CommentOutput> comments;

    private Date updatedAt;
    private Date createdAt;
}
