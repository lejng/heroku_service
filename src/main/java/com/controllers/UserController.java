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
    @Autowired
    private UserService userService;
    @Autowired
    private PhoneConfirmService phoneConfirmService;

    @RequestMapping(value = "/account", method = GET)
    @ResponseBody
    public User accountInfo() {
        return userService.getCurrentUser();
    }

    @RequestMapping(value = "/registration", method = POST)
    @ResponseBody
    public ResponseEntity registerUser(@RequestBody User user) {
        System.out.println(user);
        ResponseEntity response = userService.create(user);
        return response;
    }

    @RequestMapping(value = "/sendConfirmCode", method = POST)
    @ResponseBody
    public ResponseEntity sendConfirmCode(@RequestBody PhoneConfirm phoneConfirm) {
        System.out.println(phoneConfirm);
        phoneConfirmService.sendConfirmCode(phoneConfirm);
        Map<String, Object> body = new HashMap<>();
        body.put("Answer", "check you phone, sms was send");
        return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/checkConfirmCode", method = POST)
    @ResponseBody
    public ResponseEntity checkConfirmCode(@RequestBody PhoneConfirm phoneConfirm) {
        System.out.println(phoneConfirm);
        Map<String, Object> body = new HashMap<>();
        boolean isCorrect = phoneConfirmService.checkConfirmCode(phoneConfirm);
        if(!isCorrect){
            body.put("Answer", "incorrect confirm code");
            return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.BAD_REQUEST);
        }
        body.put("Answer", "confirm code correct");
        return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.ACCEPTED);
    }
}
