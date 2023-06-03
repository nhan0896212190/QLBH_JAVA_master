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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Duy
 */
public class EditCustomer extends JFrame {

    private Customer_GUI gui;

    public EditCustomer(JPanel panel, JTable table, Customer oldCustomer) {
        //ép kiểu sang Customer_GUI để gọi các phương thức của Customer_GUI
        gui = (Customer_GUI) panel;
        //Đặt vị trí hiển thị là chính giữa màn hình
        this.setLocationRelativeTo(null);
        //Đặt icon
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
        //Layout null để đặt các component tự do
        this.setLayout(null);
        //Đặt tiêu đề frame
        this.setTitle("Cập nhật khách hàng");
        //Đặt mặt định là khi đóng frame thì chưa kết thúc chương trình
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(400, 260);
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBounds(0, 0, this.getSize().width, this.getSize().height);
        panelBackGround.setLayout(null);
        panelBackGround.setBackground(new Color(166, 233, 255));
        this.add(panelBackGround);

        JLabel label_ID = new JLabel("Mã khách hàng");
        //Đặt vị trí và kích thước
        label_ID.setBounds(15, 8, 120, 24);
        label_ID.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_Name = new JLabel("Tên");
        //Đặt vị trí và kích thước
        label_Name.setBounds(15, 48, 120, 24);
        label_Name.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_Address = new JLabel("Địa chỉ");
        //Đặt vị trí và kích thước
        label_Address.setBounds(15, 88, 120, 24);
        label_Address.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_Phone = new JLabel("Só điện thoại");
        //Đặt vị trí và kích thước
        label_Phone.setBounds(15, 128, 120, 24);
        label_Phone.setFont(new Font("Arial", Font.BOLD, 16));

        panelBackGround.add(label_ID);
        panelBackGround.add(label_Name);
        panelBackGround.add(label_Address);
        panelBackGround.add(label_Phone);

        JTextField tf_ID = new JTextField("" + oldCustomer.getId());
        //Đặt vị trí và kích thước
        tf_ID.setBounds(140, 6, 220, 24);
        tf_ID.setFont(new Font("Arial", Font.PLAIN, 16));
        tf_ID.setEditable(false);

        JTextField tf_Name = new JTextField(oldCustomer.getName());
        //Đặt vị trí và kích thước
        tf_Name.setBounds(140, 46, 220, 24);
        tf_Name.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField tf_Address = new JTextField(oldCustomer.getAddress());
        //Đặt vị trí và kích thước
        tf_Address.setBounds(140, 86, 220, 24);
        tf_Address.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField tf_Phone = new JTextField(oldCustomer.getPhone());
        //Đặt vị trí và kích thước
        tf_Phone.setBounds(140, 126, 220, 24);
        tf_Phone.setFont(new Font("Arial", Font.PLAIN, 16));

        panelBackGround.add(tf_ID);
        panelBackGround.add(tf_Name);
        panelBackGround.add(tf_Address);
        panelBackGround.add(tf_Phone);

        JButton button_Edit = new JButton("Xác nhận");
        //Đặt vị trí và kích thước
        button_Edit.setBounds(140, 170, 130, 36);
        //Đặt con trỏ là hình bàn tay
        button_Edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_Edit.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
        button_Edit.setBackground(Color.white);
        button_Edit.setFont(new Font("Arial", Font.PLAIN, 16));
        button_Edit.setMargin(new Insets(0, 0, 0, 0));
        button_Edit.setFocusable(false);
        //Sự kiện click chuột
        button_Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerDAO daoC = new CustomerDAO();
                Customer newCustomer = new Customer(
                        Integer.parseInt(tf_ID.getText()),
                        tf_Name.getText(),
                        tf_Address.getText(),
                        tf_Phone.getText()
                );
                daoC.update(newCustomer);
                //Sửa xong thì gán customer của Customer_GUI thành customer vừa sửa (Cập nhật lại customer)
                gui.setCustomer(newCustomer);
                gui.showTable(table);
                close();
            }
        });
        panelBackGround.add(button_Edit);
        this.setVisible(true);
    }

    //Hàm xử lí sự kiện trong dùng được từ khoá this nên tạo hàm để đóng frame
    private void close() {
        this.setVisible(false);
    }
}
