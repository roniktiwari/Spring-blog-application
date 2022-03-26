package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface  PostService {

    public PostDto createPost(PostDto post) ;

    public List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePost(PostDto post_dto, long id);
}
