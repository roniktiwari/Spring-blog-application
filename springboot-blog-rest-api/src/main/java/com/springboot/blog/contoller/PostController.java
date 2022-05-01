package com.springboot.blog.contoller;


import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.entity.Post;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private PostService postService ;

    public PostController(PostService postService) {
        this.postService = postService ;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody  PostDto postDto) {
        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts (
            @RequestParam(name="pageNo" , required = false , defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(name="pageSize",required = false, defaultValue=AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(name="sortBy", required = false,defaultValue =AppConstants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue =AppConstants.DEFAULT_SORT_DIRECTION) String sortDir
    )
    {

        return new ResponseEntity<PostResponse>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(  @PathVariable(name="id") long id )
    {
        return new ResponseEntity<PostDto>(postService.getPostById(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost( @Valid @RequestBody PostDto post_dto , @PathVariable(name="id") long id )
    {
        PostDto updatedPost = postService.updatePost(post_dto,id);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostUsingId(@PathVariable(name="id") long postId) {
        // delete a particular post
        postService.deletePostUsingId(postId);
        return new ResponseEntity<String>("Post got deleted sucessfully with the Id "+postId,HttpStatus.OK);
    }

}
