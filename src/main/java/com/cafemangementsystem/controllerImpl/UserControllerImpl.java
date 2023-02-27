package com.cafemangementsystem.controllerImpl;

import com.cafemangementsystem.constants.CafeConstants;
import com.cafemangementsystem.controller.UserController;
import com.cafemangementsystem.service.UserService;
import com.cafemangementsystem.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
