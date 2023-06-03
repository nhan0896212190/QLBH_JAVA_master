/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objects.Statistics;
import java.time.LocalDate;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class StatisticsDAO implements DAO<Statistics, LocalDate> {

    @Override
    public List<Statistics> get() {
        Connection conn = ConnectDB.getConnect();
        List<Statistics> res = new ArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DOANHTHU ");

            while (rs.next()) {
                Statistics re = new Statistics(rs.getObject("NGAY", LocalDate.class), rs.getInt("SOLUONG"), rs.getInt("TIEN"));
                res.add(re);
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public Statistics get(LocalDate k) {
        Connection conn = ConnectDB.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DOANHTHU WHERE NGAY='" + k + "'");

            if (rs.next()) {
                Statistics re = new Statistics(k, rs.getInt("SOLUONG"), rs.getInt("TIEN"));
                return re;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Statistics(k, 0, 0);
    }

    @Override
    public void save(Statistics t) {
        Connection conn = ConnectDB.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DOANHTHU WHERE NGAY='" + t.getDate() + "'");

            if (rs.next()) {
                int soluong = rs.getInt("SOLUONG");
                int tien = rs.getInt("TIEN");
                PreparedStatement ps = conn.prepareStatement("UPDATE DOANHTHU SET SOLUONG=?, TIEN=? WHERE NGAY='" + t.getDate() + "'");
                ps.setInt(1, soluong + t.getCount());
                ps.setInt(2, tien + t.getRevenue());
                ps.executeUpdate();
            } else {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO DOANHTHU(NGAY,SOLUONG,TIEN) VALUES('" + t.getDate() + "',?,?)");
                ps.setInt(1, t.getCount());
                ps.setInt(2, t.getRevenue());
                ps.executeUpdate();
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Statistics t) {
    }

    @Override
    public void update(Statistics t) {
    }

    public List<Statistics> getMonth(int month, int year) {
        List<Statistics> result = new ArrayList();
        List<Statistics> res = get();
        if (month == 0) {
            for (Statistics r : res) {
                if (r.getDate().getYear() == year) {
                    result.add(r);
                }
                if (!result.isEmpty() && r.getDate().getYear() != year) {
                    return result;
                }
            }
        } else {

            for (Statistics r : res) {
                if (r.getDate().getMonthValue() == month && r.getDate().getYear() == year) {
                    result.add(r);
                }
                if (!result.isEmpty() && (r.getDate().getMonthValue() != month || r.getDate().getYear() != year)) {
                    return result;
                }
            }
        }
        return result;
    }

}
