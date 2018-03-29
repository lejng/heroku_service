package com.controllers;

import com.model.User;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/account", method = GET)
    @ResponseBody
    public User accountInfo() {
        return userService.getCurrentUser();
    }

    @RequestMapping(value = "/registration", method = POST)
    @ResponseBody
    public ResponseEntity registerUser(@RequestBody User user) {
        ResponseEntity response = userService.create(user);
        return response;
    }
}
