package com.springboot.blog.contoller;


import com.springboot.blog.dto.LoginDto;
import com.springboot.blog.dto.SignUpDto;
import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private RoleRepository roleRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new ResponseEntity<String>("User succesfully authenticated", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?>  registerUser(@RequestBody SignUpDto signUpDto){

        if(userRepository.existsByUsername(signUpDto.getUsername())){
           return new ResponseEntity<>("Username already exits",HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email already used",HttpStatus.BAD_REQUEST);
        }
        System.out.println(signUpDto);
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN").get();

        user.setRoles(Collections.singleton(role));
        userRepository.save(user);

        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);

    }


}
