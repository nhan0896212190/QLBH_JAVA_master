/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objects.Customer;
import Objects.Order;
import Objects.OrderItem;
import View.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Duy
 */
public class OrderDAO implements DAO<Order, Integer> {

    @Override
    public List<Order> get() {
        Connection conn = ConnectDB.getConnect();
        List<Order> results = new ArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HOADON");
            CustomerDAO daoC = new CustomerDAO();
            EmployeeDAO daoE = new EmployeeDAO();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("SOHD"),
                        daoC.get(rs.getInt("MAKH")),
                        getListDetail(rs.getInt("SOHD")),
                        rs.getObject("NGAYBAN", LocalDate.class),
                        daoE.get(rs.getInt("MANV"))
                );
                results.add(order);
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    @Override
    public void save(Order t) {
        Connection conn = ConnectDB.getConnect();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO HOADON VALUES (?,'" + LocalDate.now() + "',?,?)");
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getCustomer().getId());
            ps.setInt(3, t.getEmployee().getId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Order t) {

    }

    @Override
    public void update(Order t) {

    }

    public List<OrderItem> getListDetail(int k) {
        List<OrderItem> results = new ArrayList();
        Connection conn = ConnectDB.getConnect();
        ProductDAO daoP = new ProductDAO();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CHITIETHOADON WHERE SOHD=" + k);
            while (rs.next()) {
                OrderItem item = new OrderItem(daoP.get(rs.getInt("MAMH")), rs.getInt("SOLUONG"));
                results.add(item);
            }
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    public int generateIdOrder() {
        Connection conn = ConnectDB.getConnect();
        int i = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HOADON");
            while (rs.next()) {
                if (i + 1 == rs.getInt("SOHD")) {
                    i++;
                } else {
                    conn.close();
                    return i + 1;
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i + 1;
    }

    public void saveListOrderItem(Order t) {
        Connection conn = ConnectDB.getConnect();
        List<OrderItem> listDetail = t.getListDetail();
        try {
            for (int i = 0; i < listDetail.size(); i++) {
                if (listDetail.get(i).getCount() != 0) {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO CHITIETHOADON VALUES (?,?,?)");
                    ps.setInt(1, t.getId());
                    ps.setInt(2, listDetail.get(i).getProduct().getId());
                    ps.setInt(3, listDetail.get(i).getCount());
                    ps.executeUpdate();
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Làm gọn lại list orderItem
    public List<OrderItem> updateOrderItem(List<OrderItem> items) {
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).getProduct().getId() == items.get(j).getProduct().getId()) {
                    items.get(i).setCount(items.get(i).getCount() + items.get(j).getCount());
                    items.remove(items.get(j));
                }
            }
        }
        return items;
    }

    public List<Order> getListOfCustomer(Customer customer) {
        Connection conn = ConnectDB.getConnect();
        List<Order> results = new ArrayList();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HOADON WHERE MAKH=" + customer.getId());
            CustomerDAO daoC = new CustomerDAO();
            EmployeeDAO daoE = new EmployeeDAO();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("SOHD"),
                        daoC.get(rs.getInt("MAKH")),
                        getListDetail(rs.getInt("SOHD")),
                        rs.getObject("NGAYBAN", LocalDate.class),
                        daoE.get(rs.getInt("MANV"))
                );
                results.add(order);
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }

    @Override
    public Order get(Integer k) {
        Connection conn = ConnectDB.getConnect();
        Order order = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HOADON WHERE SOHD=" + k);
            CustomerDAO daoC = new CustomerDAO();
            EmployeeDAO daoE = new EmployeeDAO();

            if (rs.next()) {
                order = new Order(
                        rs.getInt("SOHD"),
                        daoC.get(rs.getInt("MAKH")),
                        getListDetail(rs.getInt("SOHD")),
                        rs.getObject("NGAYBAN", LocalDate.class),
                        daoE.get(rs.getInt("MANV"))
                );
            }
            conn.close();
            return order;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void ExportOrder(Order order) {
        try {
            JasperDesign jdesign = JRXmlLoader.load(getClass().getResource("/Report/report1.jrxml").toString().substring(6));
            JasperReport jreport = JasperCompileManager.compileReport(jdesign);
            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText("SELECT SOHD,TENNV,DAY(NGAYBAN) AS NGAY,MONTH(NGAYBAN) AS THANG, YEAR(NGAYBAN) AS NAM,TENKH,KHACHHANG.DIACHI,KHACHHANG.SDT\n"
                    + "FROM HOADON,KHACHHANG,NHANVIEN\n"
                    + "WHERE HOADON.MAKH=KHACHHANG.MAKH AND NHANVIEN.MANV=HOADON.MANV AND SOHD=" + order.getId());
            jdesign.setQuery(updateQuery);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("sohd", order.getId());
            JasperPrint jprint = JasperFillManager.fillReport(jreport, params, ConnectDB.getConnect());
            JasperViewer.viewReport(jprint);
//            String path = new File("src/fileOrders").getAbsolutePath() + "\\HD" + order.getId() + ".pdf";
//            JasperExportManager.exportReportToPdfFile(jprint, path);
        } catch (JRException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
