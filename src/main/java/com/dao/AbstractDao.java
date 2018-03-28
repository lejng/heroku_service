package com.dao;

import org.hibernate.Query;
import org.hibernate.Session;

public class AbstractDao<T> {
    protected Class clazz;

    public AbstractDao(Class clazz){
        this.clazz = clazz;
    }

    public T getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        T entity = (T)session.get(clazz, id);
        session.getTransaction().commit();
        session.close();
        return entity;
    }

    public void deleteById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "delete " + clazz.getName() + " where id = :id";
        Query query = session.createQuery(hql).setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void insert(T entity){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(T entity){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }
}
