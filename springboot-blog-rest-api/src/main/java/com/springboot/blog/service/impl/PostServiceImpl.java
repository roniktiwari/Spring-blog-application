package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository ;

    @Autowired
    public PostServiceImpl(PostRepository repository)
    {
        this.postRepository = repository ;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post post  = mapToPost(postDto);
        Post post_entity = postRepository.save(post);

        // Entity to dto
        PostDto post_dto = mapToPostDto(post_entity);
        return post_dto ;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> postList =   postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>() ;
        return postList.stream().map( post -> mapToPostDto(post))
                .collect(Collectors.toList());
    }


    private Post mapToPost(PostDto postDto)
    {
        Post post  = new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        return post ;
    }

    private PostDto mapToPostDto(Post post )
    {
        PostDto post_dto = new PostDto();
        post_dto.setContent(post.getContent());
        post_dto.setDescription(post.getDescription());
        post_dto.setTitle(post.getTitle());
        post_dto.setId(post.getId());
        return post_dto ;
    }



}
