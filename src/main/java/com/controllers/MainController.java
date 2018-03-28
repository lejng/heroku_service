package com.controllers;

import com.model.User;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "Registration or login for getting money";
    }

    @RequestMapping(value = "/account", method = GET)
    @ResponseBody
    public User accountInfo() {
        return userService.getCurrentUser();
    }
}
