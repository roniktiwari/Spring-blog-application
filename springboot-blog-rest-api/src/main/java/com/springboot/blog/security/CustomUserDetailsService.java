package com.springboot.blog.security;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository ;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {


       User user =  userRepository
                .findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("user not found with username or email"));
       // pass the user to spring security user
        System.out.println("---------------------->"+user+"<-------------------------");
       return new org.springframework.
               security.core
               .userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority > mapRolesToAuthority(Set<Role> roles) {
        return roles.stream().map( role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()) ;
    }
}
