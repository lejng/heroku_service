package com.controllers;

import com.model.Advertising;
import com.service.advertising.AdvertisingService;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class AdvertisingController {
    public static final String AVAILABLE_ADVERTISING_FOR_VIEW = "availableAdvertisingsForView";
    @Autowired
    private AdvertisingService advertisingService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = AVAILABLE_ADVERTISING_FOR_VIEW, method = GET)
    @ResponseBody
    public List<Advertising> availableAdvertisingsForView() {
        return advertisingService.getAvailableAdvertisingForCurrentUser();
    }
}
