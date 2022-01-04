package com.dionisius.finalproject.postservice.api.service;

import com.dionisius.finalproject.postservice.api.dto.PostInput;
import com.dionisius.finalproject.postservice.api.dto.PostOutput;
import com.dionisius.finalproject.postservice.api.dto.SinglePostOutput;
import com.dionisius.finalproject.postservice.data.model.Post;

import java.util.List;

public interface PostService {
    SinglePostOutput getOne(Integer id);
    List<PostOutput> getAll();
    List<PostOutput> getByCategory(Integer category_id);
    void addOne(PostInput postInput);
    void delete(Integer id);
    Post update(Integer id, PostInput postInput);
}
