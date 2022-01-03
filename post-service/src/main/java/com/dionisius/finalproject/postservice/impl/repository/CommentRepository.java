package com.dionisius.finalproject.postservice.impl.repository;

import com.dionisius.finalproject.postservice.data.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Integer> {
}
