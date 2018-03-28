package com.dao;

import com.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserHibernateDao {

    public User getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = (User)session.get(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    public void deleteById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "delete " + User.class.getName() + " where id = :id";
        Query query = session.createQuery(hql).setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public User getByPhone(String phone){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("phone", phone));
        User user = (User)criteria.uniqueResult();
        session.getTransaction().commit();
        return user;
    }
}
