package com.thanhtran.dao;

import com.thanhtran.model.Product;
import com.thanhtran.model.User;
import com.thanhtran.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static Product findById(Long id) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            session.close();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Product save(Product product) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            session.close();

            return product;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static List<Product> findAll() {
        try {
            List<Product> products;
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "FROM Product";
            Query<Product> query = session.createQuery(hql, Product.class);
            products = query.list();
            session.getTransaction().commit();
            session.close();
            return products;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean delete(Product product) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
