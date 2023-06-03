/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objects.OrderItem;
import Objects.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duy
 */
public class ProductDAO implements DAO<Product, Integer> {

    @Override
    public List<Product> get() {
        Connection conn = ConnectDB.getConnect();
        List<Product> results = new ArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MATHANG");

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("MAMH"),
                        rs.getString("TENMH"),
                        rs.getString("HINHANH"),
                        rs.getString("NHOMHANG"),
                        rs.getString("NHACUNGCAP"),
                        rs.getInt("GIABAN"),
                        rs.getInt("TONKHO"));
                results.add(product);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    @Override
    public Product get(Integer k) {
        Connection conn = ConnectDB.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MATHANG WHERE MAMH=" + k);
            if (rs.next()) {
                Product product = new Product(
                        rs.getInt("MAMH"),
                        rs.getString("TENMH"),
                        rs.getString("HINHANH"),
                        rs.getString("NHOMHANG"),
                        rs.getString("NHACUNGCAP"),
                        rs.getInt("GIABAN"),
                        rs.getInt("TONKHO"));
                conn.close();
                return product;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void save(Product t) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO MATHANG VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, t.getId());
            ps.setString(2, t.getName());
            ps.setString(3, t.getImage());
            ps.setInt(4, t.getInventory());
            ps.setInt(5, t.getPrice());
            ps.setString(6, t.getGroup());
            ps.setString(7, t.getSupplier());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Product t) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM MATHANG WHERE MAMH=?");
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Product t) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE MATHANG SET TENMH=?, HINHANH=?, TONKHO=?, GIABAN=?, NHOMHANG=?, NHACUNGCAP=? WHERE MAMH=?");
            ps.setString(1, t.getName());
            ps.setString(2, t.getImage());
            ps.setInt(3, t.getInventory());
            ps.setInt(4, t.getPrice());
            ps.setString(5, t.getGroup());
            ps.setString(6, t.getSupplier());
            ps.setInt(7, t.getId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countProductSelledAll(List<Product> products) {
        int result = 0;
        for (Product product : products) {
            if (product.getInventory() == 0) {
                result++;
            }
        }
        return result;
    }

    public int generateIdProduct() {
        Connection conn = ConnectDB.getConnect();
        int i = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MATHANG");

            while (rs.next()) {
                if (i + 1 == rs.getInt("MAMH")) {
                    i++;
                } else {
                    conn.close();
                    return i + 1;
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i + 1;
    }

    public void updateInventory(OrderItem item) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE MATHANG SET TONKHO=? WHERE MAMH=?");
            ps.setInt(1, item.getProduct().getInventory() - item.getCount());
            ps.setInt(2, item.getProduct().getId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
