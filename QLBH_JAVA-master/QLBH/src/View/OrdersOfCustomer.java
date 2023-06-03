/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.OrderDAO;
import Objects.Customer;
import Objects.Order;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Duy
 */
public class OrdersOfCustomer extends JFrame {

    private OrderDAO daoO = new OrderDAO();
    private List<Order> orders;
    private Order order;

    public OrdersOfCustomer(Customer customer) {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
        this.setLayout(null);

        this.setTitle("Danh sách hoá đơn");

        if (customer != null) {//Xem tất cả hoá đơn của khách hàng đã chọn
            this.setSize(613, 300);
            orders = daoO.getListOfCustomer(customer);
        } else {//Nếu chưa chọn khách hàng thì xem tất cả hoá đơn của tất cả khách hàng
            this.setSize(913, 900);
            orders = daoO.get();
        }
        //panelBackGround để chèn hình nền màu xanh
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBounds(0, 0, this.getSize().width, this.getSize().height);
        panelBackGround.setLayout(null);
        panelBackGround.setBackground(new Color(166, 233, 255));
        this.add(panelBackGround);
        JTable table1 = new JTable();
        
        table1.setRowHeight(20);
        table1.setFont(new Font("Arial", Font.PLAIN, 16));

        JTableHeader header1 = table1.getTableHeader();
        header1.setFont(new Font("Arial", Font.BOLD, 20));
        header1.setBackground(Color.white);
        header1.setForeground(Color.blue);
        table1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (table1.getSelectedRow() != -1) {
                    order = daoO.get(Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString()));
                }
            }

        });
        table1.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Số hoá đơn", "Ngày mua", "Tổng số lượng", "Thành tiền"}) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table1.setRowSelectionAllowed(true);
        JScrollPane jsp1 = new JScrollPane();
        jsp1.setBounds(0, 0, this.getSize().width - 15, this.getSize().height - 100);
        jsp1.setViewportView(table1);
        panelBackGround.add(jsp1);
        showTable(table1);

        JButton button_View_OrderItems = new JButton("Xem chi tiết");
        button_View_OrderItems.setFocusable(false);
        button_View_OrderItems.setBounds(jsp1.getSize().width - 180, jsp1.getSize().height + 8, 150, 40);
        button_View_OrderItems.setBackground(Color.white);
        button_View_OrderItems.setMargin(new Insets(0, 0, 0, 0));
        button_View_OrderItems.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_View_OrderItems.setIcon(new ImageIcon(getClass().getResource("/images/viewOrderItem.png")));
        button_View_OrderItems.setFont(new Font("Arial", Font.PLAIN, 16));
        button_View_OrderItems.setToolTipText("Xem chi tiết hoá đơn");
        button_View_OrderItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Nếu có hoá đơn mới hiển thị chi tiết
                if (order != null) {
                    OrderItems items = new OrderItems(order);
                }
            }
        });
        panelBackGround.add(button_View_OrderItems);

        this.setVisible(true);
    }

    //Hiển thị các hoá đơn lên table
    private void showTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[4];
        DecimalFormat df = new DecimalFormat("###,###");
        for (Order order : orders) {
            row[0] = order.getId();
            row[1] = order.getDate();
            row[2] = order.getTotalProduct();
            row[3] = df.format(order.getPrice());
            model.addRow(row);
        }
    }

}
