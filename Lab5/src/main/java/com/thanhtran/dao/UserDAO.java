package com.thanhtran.dao;

import com.thanhtran.model.User;
import com.thanhtran.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAO {
    public static User login(String username, String password) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "from User u where u.username = :username and u.password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = query.uniqueResult();
            session.getTransaction().commit();
            session.close();
            return user;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static boolean register(User user) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
