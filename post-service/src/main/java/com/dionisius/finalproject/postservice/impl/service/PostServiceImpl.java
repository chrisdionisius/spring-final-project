package com.dionisius.finalproject.postservice.impl.service;

import com.dionisius.finalproject.postservice.api.dto.PostInput;
import com.dionisius.finalproject.postservice.api.dto.PostOutput;
import com.dionisius.finalproject.postservice.api.service.PostService;
import com.dionisius.finalproject.postservice.data.model.Post;
import com.dionisius.finalproject.postservice.impl.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return PostOutput.builder()
                .id(post.get().getId())
                .user_id(post.get().getUser_id())
                .category_id(post.get().getCategory_id())
                .title(post.get().getTitle())
                .content(post.get().getContent())
                .createdAt(post.get().getCreatedAt())
                .createdAt(post.get().getUpdatedAt())
                .build();
    }

    @Override
    public List<PostOutput> getAll() {

        Iterable<Post> posts = postRepository.findAll();
        List<PostOutput> postOutputs = new ArrayList<>();
        for (Post post : posts){
            PostOutput postOutput = PostOutput.builder()
                    .id(post.getId())
                    .user_id(post.getUser_id())
                    .category_id(post.getCategory_id())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreatedAt())
                    .createdAt(post.getUpdatedAt())
                    .build();
            postOutputs.add(postOutput);
        }
        return postOutputs;
    }

    @Override
    public List<PostOutput> getByCategory(Integer category_id) {

        Iterable<Post> posts = postRepository.findAll();
        List<PostOutput> postOutputs = new ArrayList<>();
        for (Post post : posts){
            PostOutput postOutput = PostOutput.builder()
                    .id(post.getId())
                    .user_id(post.getUser_id())
                    .category_id(post.getCategory_id())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreatedAt())
                    .createdAt(post.getUpdatedAt())
                    .build();
            postOutputs.add(postOutput);
        }
        return postOutputs;
    }

    @Override
    public void addOne(PostInput postInput) {
        Post post = Post.builder()
                .title(postInput.getTitle())
                .content(postInput.getContent())
                .build();
        try {
            postRepository.save(post);
        }catch (Exception e){
            throw new RuntimeException("Duplicated");
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()){
            throw new RuntimeException("Not Found");
        }
        postRepository.deleteById(id);
    }

    @Override
    public Post update(Integer id, PostInput postInput) {
        Optional<Post> postUpdated = postRepository.findById(id);
        if (postUpdated.isEmpty()){
            throw new RuntimeException("Not Found");
        }
        postUpdated.get().setTitle(postInput.getTitle());
        postUpdated.get().setContent(postInput.getContent());
        return  postRepository.save(postUpdated.get());
    }
}
