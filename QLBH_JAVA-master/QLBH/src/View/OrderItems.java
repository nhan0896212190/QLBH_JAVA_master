/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.OrderDAO;
import Objects.Order;
import Objects.OrderItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Duy
 */
public class OrderItems extends JFrame {

    public OrderItems(Order order) {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
        this.setLayout(null);

        this.setSize(713, 200);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Chi tiết hoá đơn");
        this.setLocationRelativeTo(null);
        String[] ColumnNames = {"Tên hàng", "Số lượng", "Đơn giá", "Thành tiền"};
        Object[][] Data = {};

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(Data, ColumnNames));
        table.setRowSelectionAllowed(true);
        table.setRowHeight(20);
        table.setFont(new Font("Arial", Font.PLAIN, 16));

        table.getColumnModel().getColumn(1).setMinWidth(120);
        table.getColumnModel().getColumn(1).setMaxWidth(120);
        table.getColumnModel().getColumn(2).setMinWidth(120);
        table.getColumnModel().getColumn(2).setMaxWidth(120);
        table.getColumnModel().getColumn(3).setMinWidth(120);
        table.getColumnModel().getColumn(3).setMaxWidth(120);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setBackground(Color.white);
        header.setForeground(Color.blue);
        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(0, 0, this.getSize().width - 13, this.getSize().height - 47);
        jsp.setViewportView(table);
        this.add(jsp);
        showOrderItems(table, order);
        this.setVisible(true);
    }

    private void showOrderItems(JTable table, Order order) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[4];
        DecimalFormat df = new DecimalFormat("###,###");

        List<OrderItem> items = order.getListDetail();
        for (OrderItem item : items) {
            row[0] = item.getProduct().getName();
            row[1] = item.getCount();
            row[2] = df.format(item.getProduct().getPrice());
            row[3] = df.format(item.getCount() * item.getProduct().getPrice());
            model.addRow(row);
        }
    }
}
