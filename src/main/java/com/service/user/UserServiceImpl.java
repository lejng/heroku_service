package com.service.user;

import com.dao.PhoneConfirmDao;
import com.dao.UserHibernateDao;
import com.model.User;
import com.utils.EncodePasswordUtils;
import com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    private static final double DEFAULT_BALANCE = 0.0;
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

    public Map<String, Object> create(User user){
        Map<String, Object> body = userValidator.getUserErrors(user);
        if(!body.isEmpty()){
            body.put("isCreate", false);
        }else {
            userDao.insert(setDefaultFields(user));
            phoneConfirmDao.deleteById(phoneConfirmDao.getByPhone(user.getPhone()).getId());
            body.put("isCreate", true);
        }
        return body;
    }

    private User setDefaultFields(User user){
        user.setPassword(EncodePasswordUtils.encodePassword(user.getPassword()));
        user.setBalance(DEFAULT_BALANCE);
        return user;
    }
}
