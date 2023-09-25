package thanhtran.lab2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Integer>{

    private Connection conn;
    public ProductDAO(Connection conn) {
        this.conn = conn;
    }
    @Override
    public Integer add(Product item) throws SQLException {
        String sql = "Insert into product values(?,?,?)";
        PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(sql);
        ptm.setInt(1, item.getId());
        ptm.setString(2, item.getName());
        ptm.setDouble(3, item.getPrice());
        return ptm.executeUpdate();
    }

    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<Product>();
        try {
            String sql = "Select * from product";
            Statement stm = (Statement) conn.createStatement();
            ResultSet data = stm.executeQuery(sql);
            while(data.next()) {
                Integer id = data.getInt(1);
                String name = data.getString(2);
                Double price = data.getDouble(3);
                Product product = new Product(id, name, price);
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Product read(Integer id) {
        List<Product> productList = new ArrayList<Product>();
        try {
            String sql = "Select * from product where product.id=?";
            PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(sql);
            ptm.setInt(1, id);
            ResultSet data = ptm.executeQuery();
            while(data.next()) {
                Integer idP = data.getInt("id");
                String name = data.getString("name");
                Double price = data.getDouble("price");
                productList.add(new Product(idP, name, price));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(productList.isEmpty()) return null;
        return productList.get(0);
    }

    @Override
    public boolean update(Product item) {
        try {
            String sql = "Update product set name = ?, price = ? where id = ?";
            PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(sql);
            ptm.setString(1, item.getName());
            ptm.setDouble(2, item.getPrice());
            ptm.setInt(3, item.getId());
            return ptm.executeUpdate() == 1;
        } catch(Exception e) {
            e.printStackTrace();
        }
        // Trường hợp lỗi
        return false;
    }

    @Override
    public boolean delete(Integer id) {

        try {
            String sql = "Delete from product where id = ?";
            PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(sql);
            ptm.setInt(1, id);
            return ptm.executeUpdate() == 1;
        } catch(Exception e) {
            e.printStackTrace();
        }
        // Trường hợp lỗi
        return false;
    }

}
