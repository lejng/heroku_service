package com.controllers;

import com.model.Advertising;
import com.service.advertising.AdvertisingService;
import com.service.user.UserService;
import com.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AdvertisingController {
    @Autowired
    private AdvertisingService advertisingService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/availableAdvertisingsForView", method = GET)
    @ResponseBody
    public List<Advertising> availableAdvertisingsForView() {
        return advertisingService.getAvailableAdvertisingForCurrentUser();
    }
}
