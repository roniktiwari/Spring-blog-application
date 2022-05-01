package com.springboot.blog.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {

    private long id ;
    // name should not be null or empty
    @NotEmpty
    private String name;

    @NotEmpty(message = "Email should not be empty or null")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10 , message = "Minimum 10 character needed in the description ")
    private String body ;

}
