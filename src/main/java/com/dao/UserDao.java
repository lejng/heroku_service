package com.dao;

import com.model.User;
import com.utils.EncodePasswordUtils;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserDao {
    private List<User> users;

    public UserDao(){
        users = new LinkedList<>();
        User user = new User();
        user.setPhone("375291111111");
        user.setPassword(EncodePasswordUtils.encodePassword("1234"));
        users.add(user);
    }

    public User findUserByPhone(String phone) {
        for(User user : users){
            if(user.getPhone().equals(phone)){
                return user;
            }
        }
        return null;
    }

    public List<User> getAll(){
        return users;
    }
}
