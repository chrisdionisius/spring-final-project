package com.dionisius.finalproject.postservice.impl.repository;

import com.dionisius.finalproject.postservice.data.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Integer> {

}
