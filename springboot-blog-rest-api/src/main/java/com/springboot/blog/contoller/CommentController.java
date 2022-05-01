package com.springboot.blog.contoller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/posts/{postId}")
public class CommentController {

       private CommentService commentService ;

       @Autowired
       CommentController(CommentService commentService)
       {
           this.commentService=commentService ;
       }

       @PostMapping("/comments")
       public ResponseEntity<CommentDto> createComment(@PathVariable(name="postId") long postId, @Valid @RequestBody CommentDto commentDto)
       {
            return new ResponseEntity<CommentDto>(commentService.createCommentForParticularPost(postId,commentDto), HttpStatus.OK);
       }

       @GetMapping("/comments")
       public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable long postId)
       {
              return new ResponseEntity<List<CommentDto>>(commentService.getCommentByPostId(postId),HttpStatus.OK);
       }

       @GetMapping("/comments/{commentId}")
       public ResponseEntity<CommentDto> getCommentById(@PathVariable(name="postId") long postId , @PathVariable(name="commentId") long commentId)
       {
             return new ResponseEntity<CommentDto>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
       }


       @PutMapping("/comments/{commentId}")
       public ResponseEntity<String> updatePostById(@PathVariable(name="postId") long postId , @PathVariable(name="commentId") long commentId , @Valid @RequestBody CommentDto commentDto)
       {
              commentService.updateCommentById(postId,commentId,commentDto);
              return new ResponseEntity<String>("Comment updated successfully",HttpStatus.OK) ;
       }

       @DeleteMapping("/comments/{commentId}")
       public ResponseEntity<String> updatePostById(@PathVariable(name="postId") long postId , @PathVariable(name="commentId") long commentId)
       {
              commentService.deleteCommentById(postId,commentId);
              return new ResponseEntity<String>("Comment deleted successfully",HttpStatus.OK) ;
       }


}
