package com.cafemangementsystem.JWT;

import com.cafemangementsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private com.cafemangementsystem.model.User userDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside LoadUserByUsername {}", username);
        userDetails = userRepository.findByEmailId(username);
        if(!Objects.isNull(userDetails))
        return new User(userDetails.getEmail(), userDetails.getPassword(), new ArrayList<>());
        else
            throw  new UsernameNotFoundException("User Not Found");
    }

    public com.cafemangementsystem.model.User getUserDetails()
    {
        return userDetails;
    }

}
