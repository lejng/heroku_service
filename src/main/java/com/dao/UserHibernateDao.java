package com.dao;

import com.model.User;
import com.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class UserHibernateDao extends AbstractDao<User> {

    public UserHibernateDao() {
        super(User.class);
    }

    public User getByPhone(String phone){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("phone", phone));
        User user = (User)criteria.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
