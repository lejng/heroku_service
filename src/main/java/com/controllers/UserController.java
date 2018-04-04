package com.controllers;

import com.model.PhoneConfirm;
import com.model.User;
import com.service.phone_confirm.PhoneConfirmService;
import com.service.user.UserService;
import com.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {
    public static final String SEND_CONFIRM_CODE = "/sendConfirmCode";
    public static final String CHECK_CONFIRM_CODE = "/checkConfirmCode";
    public static final String ACCOUNT_INFO = "/account";
    public static final String REGISTRATION = "/registration";
    @Autowired
    private UserService userService;
    @Autowired
    private PhoneConfirmService phoneConfirmService;

    @RequestMapping(value = ACCOUNT_INFO, method = GET)
    @ResponseBody
    public User accountInfo() {
        return userService.getCurrentUser();
    }

    @RequestMapping(value = REGISTRATION, method = POST)
    @ResponseBody
    public ResponseEntity registerUser(@RequestBody User user) {
        Map<String, Object> response = userService.create(user);
        return new ResponseEntity(JsonUtils.mapToJson(response), HttpStatus.OK);
    }

    @RequestMapping(value = SEND_CONFIRM_CODE, method = POST)
    @ResponseBody
    public ResponseEntity sendConfirmCode(@RequestBody PhoneConfirm phoneConfirm) {
        Map<String, Object> body = new HashMap<>();
        body.put("Send", phoneConfirmService.sendConfirmCode(phoneConfirm));
        return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.OK);
    }

    @RequestMapping(value = CHECK_CONFIRM_CODE, method = POST)
    @ResponseBody
    public ResponseEntity checkConfirmCode(@RequestBody PhoneConfirm phoneConfirm) {
        Map<String, Object> body = new HashMap<>();
        body.put("Correct", phoneConfirmService.checkConfirmCode(phoneConfirm));
        return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.OK);
    }
}
