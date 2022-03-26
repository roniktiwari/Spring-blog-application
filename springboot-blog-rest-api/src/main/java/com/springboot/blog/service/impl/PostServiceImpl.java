package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
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
    public PostResponse getAllPosts(int pageNo , int pageSize,String sortBy) {
        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));

        Page<Post> postPage =   postRepository.findAll(pageable);

        // get content from page
        List<Post> postList = postPage.getContent() ;

        // convert all Post into Dto
        List<PostDto> postDtoList = postList.stream().map( post -> mapToPostDto(post))
                .collect(Collectors.toList());

        // construct post response
        PostResponse response = new PostResponse();
        response.setPostDtoList(postDtoList);
        response.setPageNo(postPage.getNumber());
        response.setPageSize(postPage.getSize());
        response.setTotalElements(postPage.getTotalElements());
        response.setTotalPages(postPage.getTotalPages());
        response.setLast(postPage.isLast());

        return response ;
    }

    @Override
    public PostDto getPostById(long id) {
          Post post  =  postRepository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
          return mapToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto post_dto, long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        post.setTitle(post_dto.getTitle());
        post.setDescription(post_dto.getDescription());
        post.setContent(post_dto.getContent());
        return mapToPostDto(postRepository.save(post));
    }

    @Override
    public void deletePostUsingId(long postId) {
        Post post =postRepository.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post","Id",postId));
        if(!ObjectUtils.isEmpty(post)) {
            postRepository.deleteById(postId);
        }
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
