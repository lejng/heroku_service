package com.dao;

import com.model.UserAdvertising;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdvertisingDao extends AbstractDao<UserAdvertising>{
    public UserAdvertisingDao() {
        super(UserAdvertising.class);
    }

    public List<UserAdvertising> getAdvertisingByUserId(Integer userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UserAdvertising.class).add(Restrictions.eq("userId", userId));
        List<UserAdvertising> advertisings = criteria.list();
        session.getTransaction().commit();
        session.close();
        return advertisings;
    }

    public List<UserAdvertising> getAdvertisingByAdvertisingId(Integer userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UserAdvertising.class).add(Restrictions.eq("advertisingId", userId));
        List<UserAdvertising> advertisings = criteria.list();
        session.getTransaction().commit();
        session.close();
        return advertisings;
    }
}
