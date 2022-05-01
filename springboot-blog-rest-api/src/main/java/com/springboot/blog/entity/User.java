package com.springboot.blog.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name="user",uniqueConstraints = {@UniqueConstraint( columnNames = {"username","email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name ;
    private String username ;
    private String email ;
    private String password ;

    @JoinTable(name="user_role",
            joinColumns = {
            @JoinColumn(name= "user_id",referencedColumnName ="id") },
            inverseJoinColumns = {
            @JoinColumn(name="role_id",referencedColumnName = "id") }
           )
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Role> roles ;
}
