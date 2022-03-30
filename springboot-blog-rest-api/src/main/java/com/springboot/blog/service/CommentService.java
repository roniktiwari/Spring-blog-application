package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createCommentForParticularPost(long postId,CommentDto commentDto) ;

    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(long postId,long commentId);

    void updateCommentById(long postId, long commentId,CommentDto commentDto);

    void deleteCommentById(long postId,long commentId);
}
