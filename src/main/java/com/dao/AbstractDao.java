package com.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

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

    public Integer insert(T entity){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Integer id = (Integer)session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void update(T entity){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public List<T> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<T> allRecords = session.createCriteria(clazz).list();
        session.getTransaction().commit();
        session.close();
        return allRecords;
    }
}
