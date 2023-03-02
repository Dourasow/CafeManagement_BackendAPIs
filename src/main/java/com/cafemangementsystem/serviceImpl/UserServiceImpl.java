package com.cafemangementsystem.serviceImpl;

import com.cafemangementsystem.JWT.CustomerUserDetailsServices;
import com.cafemangementsystem.JWT.JwtFilter;
import com.cafemangementsystem.JWT.JwtUtils;
import com.cafemangementsystem.constants.CafeConstants;
import com.cafemangementsystem.model.User;
import com.cafemangementsystem.repository.UserRepository;
import com.cafemangementsystem.service.UserService;
import com.cafemangementsystem.utils.CafeUtils;
import com.cafemangementsystem.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserDetailsServices customerUserDetailsServices;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside Signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userRepository.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email Already Exist", HttpStatus.BAD_REQUEST);
                }
            } else {
                CafeUtils.getResponseEntity(CafeConstants.INVALID_RESPONSE, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap)
    {
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
                && requestMap.containsKey("password"))
        {
            return true;
        }else
        {
            return false;
        }
    }

    private User getUserFromMap(Map<String, String> requestMap)
    {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }

    //for Login
    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("inside login");
        try
        {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    requestMap.get("email"), requestMap.get("password")));
            if(auth.isAuthenticated())
            {
                if(customerUserDetailsServices.getUserDetails().getStatus().equalsIgnoreCase("true"))
                {
                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtils.generateToken(
                            customerUserDetailsServices.getUserDetails().getEmail(), customerUserDetailsServices.getUserDetails().getRole())+ "\"}",
                            HttpStatus.OK);
                }else
                {
                    return new ResponseEntity<String>("{\"message\":\"" + "Wait for admin approval."+"\"}", HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e)
        {
            log.error("{}", e);
//            e.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Wrong Credentials."+"\"}", HttpStatus.BAD_REQUEST);

    }

    //Implementing the GetAllUsers Method
    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try
        {
            if(jwtFilter.isAdmin())
            {
                return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
            }else
            {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
