package org.example.DAO;

import org.example.POJO.Manufacture;
import org.example.POJO.Phone;

import java.util.List;
import org.example.Hibernate.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneDAO implements CRUDInterface<Phone> {
    @Override
    public boolean add(Phone phone) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(phone);

            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Phone get(String id) {
        Phone phone = new Phone();
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        phone = session.get(Phone.class, id);

        session.getTransaction().commit();
        session.close();
        return phone;
    }

    @Override
    public List<Phone> getAll() {
        List<Phone> lists = new ArrayList<Phone>();
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("FROM Phone");
        lists = q.getResultList();

        session.getTransaction().commit();
        session.close();
        return  lists;
    }

    @Override
    public boolean remove(String id) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "delete from Phone where Id= :Id";
            session.createQuery(hql).setString("Id", id).executeUpdate();

            session.getTransaction().commit();
            session.close();
            return  true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(Phone phone) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            session.delete(phone);

            session.getTransaction().commit();
            session.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Phone p) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Phone.class);
            Phone phone = (Phone) criteria.uniqueResult();


            Scanner sc = new Scanner(System.in);
            System.out.println("Enter new name:");
            String name = sc.nextLine();

            System.out.println("Enter new country:");
            String country = sc.nextLine();

            System.out.println("Enter new color:");
            String color = sc.nextLine();

            System.out.println("Enter new price:");
            int price = sc.nextInt();

            System.out.println("Enter new quantity:");
            int quantity = sc.nextInt();
            System.out.println("Enter phone id:");
            String id = sc.nextLine();
            ManufactureDAO manufactureDAO = new ManufactureDAO();
            List<Manufacture> list = manufactureDAO.getAll();
            for (Manufacture item : list) {
                if(id.equals(item.getId())){
                    phone.setManufacture(item);
                }
            }

            // Set lại giá trị cho phone
            p.setName(name);
            p.setCountry(country);
            p.setColor(color);
            p.setPrice(price);
            p.setQuanlity(quantity);


            // Update
            session.update(p);

            session.getTransaction().commit();
            session.close();
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    // Lấy phone có giá cao nhất ra
    public Phone getHighestSelling() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            // Sử dụng truy vấn Hibernate để tìm điện thoại có giá cao nhất
            Query<Phone> query = session.createQuery("FROM Phone WHERE Price = (SELECT MAX(p.Price) FROM Phone p)", Phone.class);
            List<Phone> phonesWithMaxPrice = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return phonesWithMaxPrice.get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // Sort by country name
    public List<Phone> sortByCountryName() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Phone> query = session.createQuery("FROM Phone p ORDER BY p.Country ASC, p.Price DESC", Phone.class);
            List<Phone> sortedPhones = query.getResultList();

            session.getTransaction().commit();
            session.close();
            return sortedPhones;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Kiểm tra điện thoại có > 50tr hay không
    public boolean checkPhoneGreaterThan50M() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Phone> query = session.createQuery("FROM Phone p WHERE p.Price > 50000000", Phone.class);
            List<Phone> sortedPhones = query.getResultList();

            session.getTransaction().commit();
            session.close();
            // Nếu list rỗng thì trả về false vì không có phone nào thỏa điều kiện
            if(sortedPhones.size() == 0) return false;
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // Lấy ra điện thoại có màu "Pink" và > 15 triệu
    public Phone getPinkColorAndGreater15MPhone() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            Query<Phone> query = session.createQuery("FROM Phone p WHERE p.Color = 'Pink' AND p.Price >= 15000000", Phone.class);
            List<Phone> phoneList = query.getResultList();

            session.getTransaction().commit();
            session.close();
            if(phoneList.size() == 0)
                return null;
            return phoneList.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
