package com.example.SpringSecurity.service;

import com.example.SpringSecurity.model.Users;
import com.example.SpringSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;


    private BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder(12);

    public Users registerUser(Users user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        return userRepo.save(user);
         // Placeholder return
    }

    public String verifyUser(Users user) {
        System.out.println(user.getUsername());
       Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
       if(authentication.isAuthenticated()){
           return jwtService.generateToken(user.getUsername());
       }
       return "failed";
    }
}
