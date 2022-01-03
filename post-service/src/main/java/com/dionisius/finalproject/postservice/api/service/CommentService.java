package com.dionisius.finalproject.postservice.api.service;

import com.dionisius.finalproject.postservice.api.dto.CommentInput;
import com.dionisius.finalproject.postservice.api.dto.CommentOutput;
import com.dionisius.finalproject.postservice.data.model.Comment;
import com.dionisius.finalproject.postservice.data.model.Post;

import java.util.List;

public interface CommentService {
    CommentOutput getOneComment(Integer id);
    List<CommentOutput> getAllComment();
    List<CommentOutput> getCommentByPost(Integer post_id);
    void addOneComment(CommentInput commentInput);
    void deleteComment(Integer id);
    Comment updateComment(Integer id, CommentInput commentInput);
}
