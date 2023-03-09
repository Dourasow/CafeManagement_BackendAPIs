package com.cafemangementsystem.security;

import com.cafemangementsystem.model.Role;
import com.cafemangementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.cafemangementsystem.model.User userDetails;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.cafemangementsystem.model.User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("UserName not found!"));
        return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles)
    {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

        public com.cafemangementsystem.model.User getUserDetails()
    {
        return userDetails;
    }
}
