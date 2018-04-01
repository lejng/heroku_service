package com.dao;

import com.model.PhoneConfirm;
import com.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class PhoneConfirmDao extends AbstractDao<PhoneConfirm> {
    public PhoneConfirmDao() {
        super(PhoneConfirm.class);
    }

    public PhoneConfirm getByPhone(String phone){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(PhoneConfirm.class).add(Restrictions.eq("phone", phone));
        PhoneConfirm phoneConfirm = (PhoneConfirm)criteria.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return phoneConfirm;
    }
}
