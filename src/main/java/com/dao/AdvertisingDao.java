package com.dao;

import com.model.Advertising;
import com.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdvertisingDao extends AbstractDao<Advertising> {

    public AdvertisingDao() {
        super(Advertising.class);
    }

    public List<Advertising> getAvailableAdvertising(Integer userId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Advertising> advertisings = session.getNamedQuery("findAvailableAdvertisingWithUserId").setParameter("userId", userId).list();
        session.getTransaction().commit();
        session.close();
        return advertisings;
    }
}
