package com.springboot.blog.dto;

import com.springboot.blog.entity.Comment;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {

    private Long id ;

    @NotEmpty
    @Size(min = 5 , message = "Title should have at least 5 character ")
    private String title ;

    @NotEmpty
    @Size(min = 10 , message = "Description should have at least 15 character ")
    private String description;

    @NotEmpty
    private String content ;
    private Set<CommentDto> comments;
}
