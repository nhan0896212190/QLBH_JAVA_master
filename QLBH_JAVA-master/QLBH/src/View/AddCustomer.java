/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.CustomerDAO;
import Objects.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Duy
 */
public class AddCustomer extends JFrame {

    private Order_GUI o;
    private CustomerDAO daoC = new CustomerDAO();
    private List<Customer> customers = new ArrayList();
    private Customer customer;

    public AddCustomer(JPanel p, JLabel lb) {

        o = (Order_GUI) p;

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
        this.setLayout(null);
        this.setTitle("Thêm khách hàng");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(1120, 600);
        this.setResizable(false);
        customers = daoC.get();

        JPanel pabel_BackGround = new JPanel();
        pabel_BackGround.setLayout(null);
        pabel_BackGround.setBounds(0, 0, this.getSize().width, this.getSize().height);
        pabel_BackGround.setBackground(new Color(166, 233, 255));
        this.add(pabel_BackGround);

        JLabel label_Customer = new JLabel("Thêm khách hàng");
        label_Customer.setFont(new Font("Arial", Font.BOLD, 26));
        label_Customer.setBounds(850, 10, 460, 60);
        pabel_BackGround.add(label_Customer);

        JLabel label_Name_Customer = new JLabel("Tên");
        label_Name_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        label_Name_Customer.setBounds(820, 80, 90, 24);
        pabel_BackGround.add(label_Name_Customer);

        JTextField tf_Name_Customer = new JTextField();
        tf_Name_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        tf_Name_Customer.setBounds(910, 80, 170, 24);
        pabel_BackGround.add(tf_Name_Customer);

        JLabel label_Address_Customer = new JLabel("Địa chỉ");
        label_Address_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        label_Address_Customer.setBounds(820, 120, 90, 24);
        pabel_BackGround.add(label_Address_Customer);

        JTextField tf_Address_Customer = new JTextField();
        tf_Address_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        tf_Address_Customer.setBounds(910, 120, 170, 24);
        pabel_BackGround.add(tf_Address_Customer);

        JLabel label_Phone_Customer = new JLabel("SĐT");
        label_Phone_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        label_Phone_Customer.setBounds(820, 160, 90, 24);
        pabel_BackGround.add(label_Phone_Customer);

        JTextField tf_Phone_Customer = new JTextField();
        tf_Phone_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        tf_Phone_Customer.setBounds(910, 160, 170, 24);
        pabel_BackGround.add(tf_Phone_Customer);

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã khách", "Tên", "Địa chỉ", "Số điện thoại"}
        ){
            //không cho phép sửa
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table.setRowHeight(22);
        table.getColumnModel().getColumn(0).setMinWidth(120);
        table.getColumnModel().getColumn(0).setMaxWidth(120);
        table.getColumnModel().getColumn(1).setMinWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMinWidth(170);
        table.getColumnModel().getColumn(3).setMaxWidth(170);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customer = daoC.get(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
                tf_Name_Customer.setText(customer.getName());
                tf_Address_Customer.setText(customer.getAddress());
                tf_Phone_Customer.setText(customer.getPhone());
            }
        });
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.white);
        header.setForeground(Color.blue);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        table.setRowSelectionAllowed(true);
        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(2, 2, 800, 550);
        jsp.setViewportView(table);
        pabel_BackGround.add(jsp);

        showCustomers(table);

        JButton button_Add_Customer = new JButton("Thêm mới");
        button_Add_Customer.setBounds(830, 200, 130, 36);
        button_Add_Customer.setBackground(Color.white);
        button_Add_Customer.setMargin(new Insets(0, 0, 0, 0));
        button_Add_Customer.setIcon(new ImageIcon(getClass().getResource("/images/addCustomer.png")));
        button_Add_Customer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Add_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        button_Add_Customer.setToolTipText("Thêm mới khách hàng");
        button_Add_Customer.setFocusable(false);
        button_Add_Customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = new Customer(
                        daoC.generateIdCustomer(),
                        tf_Name_Customer.getText(),
                        tf_Address_Customer.getText(),
                        tf_Phone_Customer.getText()
                );
                customer = c;
                customers.add(customer);
                showCustomers(table);
                daoC.save(customer);
            }
        });
        pabel_BackGround.add(button_Add_Customer);

        JButton button_Select_Customer = new JButton("Chọn");
        button_Select_Customer.setBounds(980, 200, 100, 36);
        button_Select_Customer.setBackground(Color.white);
        button_Select_Customer.setMargin(new Insets(0, 0, 0, 0));
        button_Select_Customer.setIcon(new ImageIcon(getClass().getResource("/images/confirm.png")));
        button_Select_Customer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Select_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        button_Select_Customer.setToolTipText("Chọn khách hàng này");
        button_Select_Customer.setFocusable(false);
        button_Select_Customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customer != null) {
                    o.setCustomer(customer);
                    lb.setText("<html><i>" + customer.getName() + "</i></html>");
                    close();
                } else {
                    JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        pabel_BackGround.add(button_Select_Customer);

        this.setVisible(true);
    }

    //Kiểm tra khách hàng có trong list không ? 
    private int check(Customer x, List<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            if (x.getName().equals(customers.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    //Hiển thị bảng khách hàng
    private void showCustomers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[4];
        DecimalFormat df = new DecimalFormat("###,###");
        model.setRowCount(0);
        for (Customer customer : customers) {
            row[0] = customer.getId();
            row[1] = customer.getName();
            row[2] = customer.getAddress();
            row[3] = customer.getPhone();
            model.addRow(row);
        }
    }

    private void close() {
        this.setVisible(false);
    }
}
