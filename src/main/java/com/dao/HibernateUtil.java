package com.dao;

import com.utils.PropertyHelper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public synchronized static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = configureSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory configureSessionFactory(){
        Configuration configuration = new Configuration();
        new PropertyHelper("hibernate/database.properties").getAllProperties()
                .forEach(info -> configuration.setProperty(info.getKey(), info.getValue()));
        new PropertyHelper("hibernate/annotated_classes.properties").getAllProperties()
                .forEach(info -> configuration.addAnnotatedClass(getClassByName(info.getValue())));
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Class getClassByName(String name){
        try {
            return Class.forName(name);
        }catch (ClassNotFoundException e){
            return null;
        }
    }
}
