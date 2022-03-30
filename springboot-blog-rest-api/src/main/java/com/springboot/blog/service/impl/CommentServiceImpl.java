package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.BlogApiException;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private ModelMapper modelMapper ;

    private PostRepository postRepository ;

    private CommentRepository commentRepository;

    @Autowired
    CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,ModelMapper modelMapper)
    {
        this.commentRepository = commentRepository ;
        this.postRepository = postRepository ;
        this.modelMapper = modelMapper ;
    }

    @Override
    public CommentDto createCommentForParticularPost(long postId, CommentDto commentDto) {
        Comment comment = mapToComment(commentDto);
        Post post =  postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" ,"Id" ,postId));

        // Because we are maintaining the post_id in comment table that why we are setting post to comment
        comment.setPost(post);

        // save comment entity to the database
        return mapToCommentDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {

        List<Comment> comments =  commentRepository.findByPostId(postId);
        return comments.stream().map( comment -> mapToCommentDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId , long commentId) {

        // check weather post with given id exist or not

         Post post = postRepository.findById(postId).orElseThrow(
                 ()->new ResourceNotFoundException("Post","Id",postId)
         );

         // check weather the comment with given id exist or not

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                         ()  -> new ResourceNotFoundException("Comment","Id",commentId) ) ;


        // check weather this comment associated with given post or not

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        return mapToCommentDto(comment);
    }

    @Override
    public  void updateCommentById(long postId, long commentId,CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","Id",postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()  -> new ResourceNotFoundException("Comment","Id",commentId) ) ;

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }

        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","Id",postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()  -> new ResourceNotFoundException("Comment","Id",commentId) ) ;

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }


    private CommentDto mapToCommentDto(Comment comment)
    {
        CommentDto  commentDto = modelMapper.map(comment,CommentDto.class);

      /*  CommentDto commentDto = new CommentDto() ;
        commentDto.setBody(comment.getBody());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setId(comment.getId());
       */
        return commentDto ;
    }

    private Comment mapToComment(CommentDto commentDto)
    {
        Comment comment = modelMapper.map(commentDto,Comment.class);
        /*
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        */
        return comment ;
    }
}
