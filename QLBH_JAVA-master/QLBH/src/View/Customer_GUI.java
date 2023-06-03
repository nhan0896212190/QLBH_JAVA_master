/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.CustomerDAO;
import Objects.Customer;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Duy
 */
public class Customer_GUI extends JPanel {

    private CustomerDAO daoC = new CustomerDAO();
    private List<Customer> customers = new ArrayList();
    private Customer customer;
    private JPanel thisPanel;

    public Customer_GUI() {
        this.setLayout(null);
        this.setSize(913, 750);
        thisPanel = this;
        //Lấy ra tất cả khách hàng
        customers = daoC.get();
        this.setBackground(new Color(166, 233, 255));
        String[] ColumnNames = {"Mã khách hàng", "Họ tên", "Địa chỉ", "Số điện thoại"};
        String[][] Data = {};
        JTable table1 = new JTable(Data, ColumnNames);

        JTableHeader header1 = table1.getTableHeader();
        header1.setFont(new Font("Arial", Font.BOLD, 24));
        header1.setBackground(Color.white);
        header1.setForeground(Color.blue);

        table1.setModel(new DefaultTableModel(Data, ColumnNames));
        table1.setRowHeight(20);
        table1.setFont(new Font("Arial", Font.PLAIN, 16));
        table1.setRowSelectionAllowed(true);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table1.getSelectedRow() != -1) {
                    //Lấy ra khách hàng tại vị trí click
                    customer = customers.get(table1.getSelectedRow());
                }
            }
        });
        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(0, 46, 900, 633);
        jsp.setViewportView(table1);
        //Hiển thị tất cả khách hàng lên table
        showTable(table1);
        this.add(jsp);

        JButton button_View_Orders = new JButton("Xem các hoá đơn");
        button_View_Orders.setFocusable(false);
        button_View_Orders.setBounds(100, 4, 180, 34);
        button_View_Orders.setBackground(Color.white);
        button_View_Orders.setHorizontalAlignment(SwingConstants.LEFT);
        button_View_Orders.setIcon(new ImageIcon(getClass().getResource("/images/viewAllOrders.png")));
        button_View_Orders.setMargin(new Insets(0, 0, 0, 0));
        button_View_Orders.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_View_Orders.setFont(new Font("Arial", Font.PLAIN, 16));
        button_View_Orders.setToolTipText("Xem tất cả hoá đơn");
        button_View_Orders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button_View_Orders.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                thisPanel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                OrdersOfCustomer ooc = new OrdersOfCustomer(customer);
                button_View_Orders.setCursor(new Cursor(Cursor.HAND_CURSOR));
                thisPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        this.add(button_View_Orders);

        JButton button_Edit_Customer = new JButton("Sửa");
        button_Edit_Customer.setFocusable(false);
        button_Edit_Customer.setBounds(600, 4, 80, 34);
        button_Edit_Customer.setBackground(Color.white);
        button_Edit_Customer.setHorizontalAlignment(SwingConstants.LEFT);
        button_Edit_Customer.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
        button_Edit_Customer.setMargin(new Insets(0, 0, 0, 0));
        button_Edit_Customer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_Edit_Customer.setFont(new Font("Arial", Font.PLAIN, 16));
        button_Edit_Customer.setToolTipText("Sửa thông tin khách hàng");
        button_Edit_Customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() != -1) {
                    EditCustomer edit = new EditCustomer(thisPanel, table1, customer);
                } else {
                    JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng cần sửa thông tin", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.add(button_Edit_Customer);
        this.setVisible(false);
    }

    public void showTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[4];
        model.setRowCount(0);
        for (Customer customer : customers) {
            row[0] = "" + customer.getId();//kiểu int không phải Object nên đổi sang String
            row[1] = customer.getName();
            row[2] = customer.getAddress();
            row[3] = customer.getPhone();
            model.addRow(row);
        }
    }

    public void setCustomer(Customer c) {
        customer = c;
        customers = daoC.get();
    }

}
