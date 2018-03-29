package com.validator;

import com.model.User;
import com.utils.EncodePasswordUtils;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserValidator {

    public User setDefaultFields(User user){
        user.setPassword(EncodePasswordUtils.encodePassword(user.getPassword()));
        user.setConfirm(false);
        user.setBalance(0.0);
        return user;
    }

    public Map<String, Object> getUserErrors(User user){
        Map<String, Object> body = new HashMap<>();
        if(isPhoneCorrect(user.getPhone())){
            body.put("Phone","check phone field");
        }
        if(isNameCorrect(user.getName())){
            body.put("Name", "check name field");
        }
        if(isSurnameCorrect(user.getSurname())){
            body.put("Surname", "check surname field");
        }
        if(isPasswordCorrect(user.getPassword())){
            body.put("Password", "check password field");
        }
        return body;
    }

    private boolean isPhoneCorrect(String phone){
        if(phone.length() > 10){
            return false;
        }
        return true;
    }

    private boolean isNameCorrect(String name){
        if(name.length() > 3){
            return false;
        }
        return true;
    }

    private boolean isSurnameCorrect(String surname){
        if(surname.length() > 3){
            return false;
        }
        return true;
    }

    private boolean isPasswordCorrect(String password){
        if(password.length() > 4){
            return false;
        }
        return true;
    }
}
