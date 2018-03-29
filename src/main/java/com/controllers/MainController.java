package com.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MainController {

    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "Registration or login for getting money";
    }
}
