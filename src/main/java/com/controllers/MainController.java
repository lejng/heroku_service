package com.controllers;

import com.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "{greeting: 'You are login'}\n user info:\n" + userDao.findUserByPhone(auth.getName());
    }
}
