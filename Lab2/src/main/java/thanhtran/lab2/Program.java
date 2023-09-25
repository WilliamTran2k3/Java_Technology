package thanhtran.lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


public class Program {
	public static void main(String[] args) {
		// Lấy các tham số từ dòng lệnh
        String url = args[0];
        String username = args[1];
        String password = args[2];
		Connection conn = null;
		// Kết nối đến database
		try {
//            Connection conn = DriverManager.getConnection(url, username, password);
			// Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			if (conn != null) { System.out .println("Successfully connected to MySQL database test"); }


		} catch(Exception e) {
			e.printStackTrace();
		}
        String databaseName = "ProductManagement";
        // Tạo cơ sở dữ liệu nếu nó chưa tồn tại
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + databaseName;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createDatabaseSQL);

            // Sử dụng cơ sở dữ liệu "ProductManagement"
            String useDatabaseSQL = "USE " + databaseName;
            stmt.executeUpdate(useDatabaseSQL);

            // Xóa bảng "Product" nếu nó đã tồn tại
            String dropTableSQL = "DROP TABLE IF EXISTS Product";
            stmt.executeUpdate(dropTableSQL);

            // Tạo lại bảng "Product" từ đầu
            String createTableSQL = "CREATE TABLE Product (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "price DOUBLE)";
            stmt.executeUpdate(createTableSQL);
        } catch(Exception e) {
            e.printStackTrace();
        }


        Scanner sc = new Scanner(System.in);

        ProductDAO productDAO = null;
        try  {
            // Khởi tạo giá trị cho productDAO, em truyền connection vào DAO để dùng lệnh SQL.
            productDAO = new ProductDAO(conn);
        } catch(Exception e) {
            System.out.println("Error");
        }
        int choice = 0;
        while(choice != 6) {
            System.out.println("Product Management");
            System.out.println("1. Read product list");
            System.out.println("2. Read a product by input id");
            System.out.println("3. Add a new product, the result is the product id (auto increment)");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    List<Product> productList = productDAO.readAll();
                    if(productList != null) {
                        for(Product product : productList) {
                            System.out.println(product);
                        }
                    }
                    break;
                case 2:
					System.out.print("Enter id to read: ");
					int id = sc.nextInt();
					Product product = productDAO.read(id);
                    if(product == null) {
                        System.out.println("Not found");
                    } else {
                        System.out.println(product);
                    }
                    break;
                case 3:
					System.out.print("Enter id: ");
					int idProduct = sc.nextInt();
					// Tiêu thụ dòng trống
					sc.nextLine();
					System.out.print("Enter name: ");
					String nameProduct = sc.nextLine();
					System.out.print("Enter price: ");
					Double priceProduct = sc.nextDouble();
					try {
						productDAO.add(new Product(idProduct, nameProduct, priceProduct));
					} catch(Exception e) {
						e.printStackTrace();
					}
                    break;
                case 4:
					System.out.print("Enter id: ");
					int idUpdate = sc.nextInt();
					// Tiêu thụ dòng trống
					sc.nextLine();
					System.out.print("Enter name: ");
					String nameUpdate = sc.nextLine();
					System.out.print("Enter price: ");
					Double priceUpdate = sc.nextDouble();
					try {
						productDAO.update(new Product(idUpdate, nameUpdate, priceUpdate));
					} catch(Exception e) {
						e.printStackTrace();
					}
                    break;
                case 5:
					System.out.print("Enter id: ");
					int idDelete = sc.nextInt();
					productDAO.delete(idDelete);
                    break;
            }
        }
	}
}
