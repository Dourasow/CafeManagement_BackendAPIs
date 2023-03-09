package com.cafemangementsystem.controllerImpl;

import com.cafemangementsystem.constants.CafeConstants;
import com.cafemangementsystem.controller.UserController;
import com.cafemangementsystem.service.UserService;
import com.cafemangementsystem.utils.CafeUtils;
import com.cafemangementsystem.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try
        {
            return userService.signUp(requestMap);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try
        {
            return userService.login(requestMap);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @Override
//    public ResponseEntity<List<UserWrapper>> getAllUsers() {
//        try
//        {
//            return userService.getAllUsers();
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<List<UserWrapper>> (new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
