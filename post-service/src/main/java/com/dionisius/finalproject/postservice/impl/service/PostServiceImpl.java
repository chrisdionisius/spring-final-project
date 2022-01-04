package com.dionisius.finalproject.postservice.impl.service;

import com.dionisius.finalproject.postservice.api.dto.PostInput;
import com.dionisius.finalproject.postservice.api.dto.PostOutput;
import com.dionisius.finalproject.postservice.api.service.CommentService;
import com.dionisius.finalproject.postservice.api.service.KafkaPost;
import com.dionisius.finalproject.postservice.api.service.PostService;
import com.dionisius.finalproject.postservice.data.model.Post;
import com.dionisius.finalproject.postservice.impl.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    @Qualifier("commentServiceImpl")
    private CommentService commentService;

    @Autowired
    private KafkaPost kafkaPost;

    @Override
    public PostOutput getOne(Integer id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()){
            throw new RuntimeException("Not Found");
        }

        return PostOutput.builder()
                .id(post.get().getId())
                .user_id(post.get().getUser_id())
                .category_id(checkCategory(post.get().getCategory_id()))
                .title(post.get().getTitle())
                .content(post.get().getContent())
                .comments(commentService.getCommentByPost(id))
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
                    .category_id(checkCategory(post.getCategory_id()))
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
                    .category_id(checkCategory(post.getCategory_id()))
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
                .user_id(postInput.getUser_id())
                .category_id(postInput.getCategory_id())
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
        if (post.isEmpty()) {
            throw new RuntimeException("Not Found");
        }
        String oldPost = null;
        try {
            oldPost = new JSONObject()
                    .put("data", post)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        kafkaPost.sendLog(oldPost);
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

    private String checkCategory(Integer id){
        String result = null;
        try{
            final String uri = "http://192.168.56.1:8701/category/"+id;
            RestTemplate restTemplate = new RestTemplate();
            return result = restTemplate.getForObject(uri, String.class);
        }catch (Exception e){
           return result = null;
        }
    }
}
