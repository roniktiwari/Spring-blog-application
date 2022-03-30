package com.springboot.blog.entity;


import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id ;
        private String name;
        private String email;
        private String body ;

        // setup many to one relationship with post
        @ManyToOne(fetch = FetchType.LAZY)
        //  we use @JoinColumn annotation to specify the foreign key name
        //  ( post_id will be same as primary key of Post table which is id)
        @JoinColumn(name = "post_id",nullable = false)
        private Post post ;


}
