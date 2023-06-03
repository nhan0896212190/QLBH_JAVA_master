/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objects.Customer;
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
public class CustomerDAO implements DAO<Customer, Integer> {

    @Override
    public List<Customer> get() {
        Connection conn = ConnectDB.getConnect();
        List<Customer> array = new ArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM KHACHHANG");
            while (rs.next()) {
                Customer cus = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                array.add(cus);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    @Override
    public Customer get(Integer k) {
        Connection conn = ConnectDB.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM KHACHHANG WHERE MAKH=" + k);
            if (rs.next()) {
                Customer cus = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                conn.close();
                return cus;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void save(Customer t) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO KHACHHANG VALUES (?,?,?,?)");//Chạy câu lệnh SQL (query9)
            //Gán giá trị vào ?
            ps.setInt(1, t.getId());
            ps.setString(2, t.getName());
            ps.setString(3, t.getAddress());
            ps.setString(4, t.getPhone());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Customer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Customer t) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE KHACHHANG SET TENKH=?, DIACHI=?, SDT=? WHERE MAKH=?");
            ps.setString(1, t.getName());
            ps.setString(2, t.getAddress());
            ps.setString(3, t.getPhone());
            ps.setInt(4, t.getId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Khởi tạo id khách hàng
    public int generateIdCustomer() {
        Connection conn = ConnectDB.getConnect();
        int i = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM KHACHHANG");
            while (rs.next()) {
                if (i + 1 == rs.getInt("MAKH")) {
                    i++;
                } else {
                    conn.close();
                    return i + 1;
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i + 1;
    }
}
