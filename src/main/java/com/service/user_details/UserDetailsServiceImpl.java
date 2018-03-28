package com.service.user_details;

import com.dao.UserHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    UserHibernateDao userDao;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        com.model.User user = userDao.getByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("User " + phone + " was not found in the database");
        }
        UserDetails userDetails = new User(user.getPhone(), user.getPassword(), new ArrayList<GrantedAuthority>());
        return userDetails;
    }
}
