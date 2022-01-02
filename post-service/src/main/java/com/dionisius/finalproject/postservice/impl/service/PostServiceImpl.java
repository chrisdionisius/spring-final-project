package com.dionisius.finalproject.postservice.impl.service;

import com.dionisius.finalproject.postservice.api.dto.PostInput;
import com.dionisius.finalproject.postservice.api.dto.PostOutput;
import com.dionisius.finalproject.postservice.api.service.PostService;
import com.dionisius.finalproject.postservice.data.model.Post;
import com.dionisius.finalproject.postservice.impl.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostOutput getOne(Integer id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()){
            throw new RuntimeException("Not Found");
        }
        return null;

    }

    @Override
    public List<PostOutput> getAll() {
        return null;
    }

    @Override
    public List<PostOutput> getByCategory() {
        return null;
    }

    @Override
    public void addOne(PostInput categoryInput) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Post update(Integer id, PostInput categoryInput) {
        return null;
    }
}
