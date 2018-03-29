package com.controllers;

import com.model.Advertising;
import com.model.User;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class AdvertisingController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/myadvertisings", method = GET)
    @ResponseBody
    public List<Advertising> myadvertisings() {
        return userService.getAvailableAdvertisingForCurrentUser();
    }
}
