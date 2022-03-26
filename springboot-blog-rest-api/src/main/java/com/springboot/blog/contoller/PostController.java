package com.springboot.blog.contoller;


import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.entity.Post;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private PostService postService ;

    public PostController(PostService postService) {
        this.postService = postService ;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts (
            @RequestParam(name="pageNo" , required = false , defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize",required = false, defaultValue="2") int pageSize,
            @RequestParam(name="sortBy", required = false,defaultValue ="id") String sortBy
    )
    {

        return new ResponseEntity<PostResponse>(postService.getAllPosts(pageNo,pageSize,sortBy),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id )
    {
        return new ResponseEntity<PostDto>(postService.getPostById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post_dto , @PathVariable(name="id") long id )
    {
        PostDto updatedPost = postService.updatePost(post_dto,id);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostUsingId(@PathVariable(name="id") long postId) {
        // delete a particular post
        postService.deletePostUsingId(postId);
        return new ResponseEntity<String>("Post got deleted sucessfully with the Id "+postId,HttpStatus.OK);
    }

}
