package com.service.user;

import com.dao.PhoneConfirmDao;
import com.dao.UserHibernateDao;
import com.model.User;
import com.utils.JsonUtils;
import com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserHibernateDao userDao;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PhoneConfirmDao phoneConfirmDao;

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.getByPhone(auth.getName());
    }

    public ResponseEntity create(User user){
        Map<String, Object> body = userValidator.getUserErrors(user);
        String info = "Info";
        String status = "Status";
        if(!body.isEmpty()){
            body.put(status, "errors");
            body.put(info, "check correct data");
            return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.BAD_REQUEST);
        }
        userDao.insert(userValidator.setDefaultFields(user));
        phoneConfirmDao.deleteById(phoneConfirmDao.getByPhone(user.getPhone()).getId());
        body.put(status, "success");
        body.put(info, "success registration user");
        return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.CREATED);
    }
}
