package com.service.user;

import com.dao.AdvertisingDao;
import com.dao.HibernateUtil;
import com.dao.UserAdvertisingDao;
import com.dao.UserHibernateDao;
import com.model.Advertising;
import com.model.User;
import com.model.UserAdvertising;
import com.utils.EncodePasswordUtils;
import com.utils.JsonUtils;
import com.validator.UserValidator;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserHibernateDao userDao;

    @Autowired
    private AdvertisingDao advertisingDao;

    @Autowired
    private UserAdvertisingDao userAdvertisingDao;

    @Autowired
    private UserValidator userValidator;

    public ResponseEntity viewedAdvertising(Integer advertisingId){
        List<UserAdvertising> userAdvertisings = userAdvertisingDao.getAdvertisingByAdvertisingId(advertisingId);
        UserAdvertising advertising = null;
        User currentUser = getCurrentUser();
        for(UserAdvertising item : userAdvertisings){
           if(item.getUserId().equals(currentUser.getId())){
               advertising = item;
               break;
           }
        }
        if(!advertising.isViewed()) {
            advertising.setViewed(true);
            Double cost = advertisingDao.getById(advertisingId).getCost();
            currentUser.setBalance(currentUser.getBalance() + cost);
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(currentUser);
            session.update(advertising);
            session.getTransaction().commit();
            session.close();
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public List<Advertising> getAvailableAdvertisingForCurrentUser(){
        Integer userId = getCurrentUser().getId();
        List<UserAdvertising> advertisingIds = userAdvertisingDao.getAdvertisingByUserId(userId);
        List<Advertising> advertisings = new LinkedList<>();
        advertisingIds.forEach( item -> { if(!item.isViewed()){  advertisings.add(advertisingDao.getById(item.getAdvertisingId()));}});
        return advertisings;
    }

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
        body.put(status, "success");
        body.put(info, "success registration user");
        return new ResponseEntity(JsonUtils.mapToJson(body), HttpStatus.CREATED);
    }


}
