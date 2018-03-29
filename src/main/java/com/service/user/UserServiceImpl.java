package com.service.user;

import com.dao.UserHibernateDao;
import com.model.User;
import com.utils.EncodePasswordUtils;
import com.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserHibernateDao userDao;

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.getByPhone(auth.getName());
    }

    public ResponseEntity create(User user){
        Map<String, Object> body = getUserErrors(user);
        String info = "Info";
        String status = "Status";
        if(!body.isEmpty()){
            body.put(status, "errors");
            body.put(info, "check correct data");
            return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.BAD_REQUEST);
        }
        userDao.insert(setDefaultFields(user));
        body.put(status, "success");
        body.put(info, "success registration user");
        return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.CREATED);
    }

    private User setDefaultFields(User user){
        user.setPassword(EncodePasswordUtils.encodePassword(user.getPassword()));
        user.setConfirm(false);
        user.setBalance(0.0);
        return user;
    }

    private Map<String, Object> getUserErrors(User user){
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
