/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objects.Employee;
import java.sql.Connection;
import java.sql.Date;
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
public class EmployeeDAO implements DAO<Employee, Integer> {

    @Override
    public List<Employee> get() {
        Connection conn = ConnectDB.getConnect();
        List<Employee> results = new ArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NHANVIEN");
            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("GIOITINH"),
                        rs.getDate("NGAYSINH").toLocalDate(),
                        rs.getInt("MANV"),
                        rs.getString("TENNV"),
                        rs.getString("DIACHI"),
                        rs.getString("SDT")
                );
                results.add(employee);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    @Override
    public Employee get(Integer k) {
        Connection conn = ConnectDB.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NHANVIEN WHERE MANV=" + k);
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("GIOITINH"),
                        rs.getDate("NGAYSINH").toLocalDate(),
                        rs.getInt("MANV"),
                        rs.getString("TENNV"),
                        rs.getString("DIACHI"),
                        rs.getString("SDT")
                );
                conn.close();
                return employee;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void save(Employee t) {
    }

    @Override
    public void delete(Employee t) {
    }

    @Override
    public void update(Employee t) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE NHANVIEN SET TENNV=?, GIOITINH=?, NGAYSINH=?, DIACHI=?, SDT=?, USERNAME=?, PASSWORD=? WHERE MANV=?");
            ps.setString(1, t.getName());
            ps.setString(2, t.getGender());
            ps.setDate(3, Date.valueOf(t.getDayOfBirth()));
            ps.setString(4, t.getAddress());
            ps.setString(5, t.getPhone());
            ps.setString(6, t.getUsername());
            ps.setString(7, t.getPassword());
            ps.setInt(8, t.getId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee login(String username, String password) {
        List<Employee> employees = get();
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }
}
