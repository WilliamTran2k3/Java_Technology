package org.example;

import org.example.DAO.ManufactureDAO;
import org.example.DAO.PhoneDAO;
import org.example.POJO.Manufacture;
import org.example.POJO.Phone;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ManufactureDAO manufactureDAO = new ManufactureDAO();
        Manufacture m1 = new Manufacture("1", "Nokia", "VietNam", 10000),
                    m2 = new Manufacture("2", "SamSung", "VietNam", 6000),
                    m3 = new Manufacture("3", "Xiaomi", "VietNam", 8000),
                    m4 = new Manufacture("4", "IPhone", "VietNam", 2000);

        manufactureDAO.add(m1);
        manufactureDAO.add(m2);
        manufactureDAO.add(m3);
        manufactureDAO.add(m4);

        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.add(new Phone("1", "Nokia1", 8000000, "Blue", "VietNam", 2, m1));
        phoneDAO.add(new Phone("2", "SamSung2", 6000000, "Blue", "VietNam", 2, m2));
        phoneDAO.add(new Phone("3", "Xiaomi3", 55000000, "Blue", "VietNam", 2, m3));
        phoneDAO.add(new Phone("4", "IPhone4", 4008000, "Blue", "VietNam", 2, m4));


        System.out.println("\n==============================\n");
        // Lấy phone có giá cao nhất
        Phone highestPhone = phoneDAO.getHighestSelling();
        System.out.println("Phone with highest price: " + highestPhone);

        System.out.println("\n==============================\n");
        // Danh sách các phone đã sort theo country
        List<Phone> sortedPhone = phoneDAO.sortByCountryName();
        for(Phone phone : sortedPhone) {
            System.out.println(phone);
        }

        System.out.println("\n==============================\n");
        // Kiểm tra xem có điện thoại nào trong database có giá hon 50 triệu không
        boolean check = phoneDAO.checkPhoneGreaterThan50M();
        System.out.println("Exist a phone that > 50 million: " + check);

        System.out.println("\n==============================\n");
        // Kiểm tra xem có điện thoại nào màu hồng và giá > 15tr không
        Phone pinkPhone = phoneDAO.getPinkColorAndGreater15MPhone();
        System.out.println("Phone with pink color and price greater than 15M: " + pinkPhone);


        System.out.println("\n==============================\n");
        // Kiểm tra xem có manufacture nào > 100 employee không
        boolean checkManu = manufactureDAO.checkAllManufactureGreater100();
        System.out.println("Exist a manufacture that has over 100 employees: " + checkManu);


        System.out.println("\n==============================\n");
        // Lấy tổng số lượng employee từ tất cả manufacture
        Long sum = manufactureDAO.sumAllEmployee();
        System.out.println("Sum employees of all manufactures: " + sum);

        System.out.println("\n==============================\n");
        // Lấy manufacture cuối thỏa: ở US
        Manufacture mUS = manufactureDAO.getLastManufacturerBasedInUS();
        System.out.println("Manufacture meet the criteria: based in the US: " + mUS);

    }
}