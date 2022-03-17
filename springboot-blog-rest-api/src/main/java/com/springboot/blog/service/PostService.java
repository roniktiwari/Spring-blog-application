package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;

import java.util.List;

public interface  PostService {

    public PostDto createPost(PostDto post) ;

    public List<PostDto> getAllPosts();
}
