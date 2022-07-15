package com.cts.authorization.service;


import com.cts.authorization.model.UserModel;
import com.cts.authorization.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        // fetching user by userName, if user is null the throw exception
        UserModel userModel = userRepository.findByUserName(userName);
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
        log.info("user successfully located");
        return new User(userModel.getUserName(), userModel.getPassword(), new ArrayList<>());
    }

}