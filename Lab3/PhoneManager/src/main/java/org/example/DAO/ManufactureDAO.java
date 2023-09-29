package org.example.DAO;

import org.example.Exception.InvalidOperationException;
import org.example.Hibernate.HibernateUtils;
import org.example.POJO.Manufacture;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ManufactureDAO implements CRUDInterface<Manufacture> {
    @Override
    public boolean add(Manufacture manufacture) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(manufacture);

            session.getTransaction().commit();
            session.close();
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public Manufacture get(String id) {
        Manufacture manu = new Manufacture();
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        manu = session.get(Manufacture.class, id);

        session.getTransaction().commit();
        session.close();
        return  manu;
    }

    @Override
    public List<Manufacture> getAll() {
        List<Manufacture> lists = new ArrayList<Manufacture>();
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query<Manufacture> q = session.createQuery("FROM Manufacture", Manufacture.class);
        lists = q.getResultList();

        session.getTransaction().commit();
        session.close();
        return lists;
    }

    @Override
    public boolean remove(String id) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "delete from Manufacture where Id= :Id";
            session.createQuery(hql).setString("Id", id).executeUpdate();

            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Manufacture manufacture) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            session.delete(manufacture);

            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Manufacture manufacture) {

        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            session.beginTransaction();

            CriteriaUpdate<Manufacture> criteriaUpdate = cb.createCriteriaUpdate(Manufacture.class);
            Root<Manufacture> root = criteriaUpdate.from(Manufacture.class);
            criteriaUpdate.set("Name", "newPrice");
            criteriaUpdate.where(cb.equal(root.get("Id"), "125"));

            session.createQuery(criteriaUpdate).executeUpdate();

            session.getTransaction().commit();
            session.close();
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra > 100 employees
    public boolean checkAllManufactureGreater100() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Manufacture> query = session.createQuery("FROM Manufacture m WHERE m.Employee > 100", Manufacture.class);
            List<Manufacture> manuList = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return manuList.size() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Long sumAllEmployee() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Long> query = session.createQuery("SELECT SUM(m.Employee) FROM Manufacture m", Long.class);
            Long sum = query.uniqueResult();

            session.getTransaction().commit();
            session.close();
            return sum;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public Manufacture getLastManufacturerBasedInUS() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            // Create an HQL query to retrieve manufacturers based in the US
            Query<Manufacture> query = session.createQuery("FROM Manufacture m WHERE m.Location = 'US' ORDER BY m.Id DESC", Manufacture.class);
            List<Manufacture> usManufacturers = query.getResultList();

            session.getTransaction().commit();
            session.close();

            if (usManufacturers.isEmpty()) {
                throw new InvalidOperationException("No manufacturer based in the US found.");
            }

            // Return the last manufacturer in the list
            return usManufacturers.get(0);
        } catch (Exception e) {
            // Handle exceptions as needed
            throw new RuntimeException("An error occurred while retrieving the last US manufacturer.", e);
        }
    }
}
