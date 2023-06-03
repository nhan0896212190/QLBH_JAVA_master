/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.EmployeeDAO;
import Objects.Employee;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Duy
 */
public class EditEmployee extends JFrame {
    
    private Menu jmb;
    
    public EditEmployee(JMenuBar menu, Employee employee) {
        //ép kiểu sang Menu để gọi các phương thức của Menu
        jmb = (Menu) menu;
        //Đặt icon
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
        //Layout null để đặt các component tự do
        this.setLayout(null);
        //Đặt tiêu đề frame
        this.setTitle("Thông tin cá nhân");
        //Đặt mặt định là khi đóng frame thì chưa kết thúc chương trình
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        //Đặt vị trí hiển thị là chính giữa màn hình
        this.setLocationRelativeTo(null);
        this.setSize(360, 410);
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBounds(0, 0, this.getSize().width, this.getSize().height);
        panelBackGround.setLayout(null);
        panelBackGround.setBackground(new Color(166, 233, 255));
        this.add(panelBackGround);
        
        JLabel label_ID = new JLabel("Mã nhân viên");
        //Đặt vị trí và kích thước
        label_ID.setBounds(15, 8, 120, 24);
        label_ID.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel label_Name = new JLabel("Tên");
        //Đặt vị trí và kích thước
        label_Name.setBounds(15, 48, 120, 24);
        label_Name.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel label_Gender = new JLabel("Giới tính");
        //Đặt vị trí và kích thước
        label_Gender.setBounds(15, 88, 120, 24);
        label_Gender.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel label_DateOfBirth = new JLabel("Ngày sinh");
        //Đặt vị trí và kích thước
        label_DateOfBirth.setBounds(15, 128, 120, 24);
        label_DateOfBirth.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel label_Address = new JLabel("Địa chỉ");
        //Đặt vị trí và kích thước
        label_Address.setBounds(15, 168, 120, 24);
        label_Address.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel label_Phone = new JLabel("Só điện thoại");
        //Đặt vị trí và kích thước
        label_Phone.setBounds(15, 208, 120, 24);
        label_Phone.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel label_Username = new JLabel("Tài khoản");
        //Đặt vị trí và kích thước
        label_Username.setBounds(15, 248, 120, 24);
        label_Username.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel label_Password = new JLabel("Mật khẩu");
        //Đặt vị trí và kích thước
        label_Password.setBounds(15, 288, 120, 24);
        label_Password.setFont(new Font("Arial", Font.BOLD, 16));
        
        panelBackGround.add(label_ID);
        panelBackGround.add(label_Name);
        panelBackGround.add(label_Gender);
        panelBackGround.add(label_DateOfBirth);
        panelBackGround.add(label_Address);
        panelBackGround.add(label_Phone);
        panelBackGround.add(label_Username);
        panelBackGround.add(label_Password);
        
        JTextField tf_ID = new JTextField("" + employee.getId());
        //Đặt vị trí và kích thước
        tf_ID.setBounds(125, 6, 200, 24);
        tf_ID.setFont(new Font("Arial", Font.PLAIN, 16));
        tf_ID.setEditable(false);
        
        JTextField tf_Name = new JTextField(employee.getName());
        //Đặt vị trí và kích thước
        tf_Name.setBounds(125, 46, 200, 24);
        tf_Name.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JTextField tf_Gender = new JTextField(employee.getGender());
        //Đặt vị trí và kích thước
        tf_Gender.setBounds(125, 86, 200, 24);
        tf_Gender.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JTextField tf_DateOfBirth = new JTextField(employee.getDayOfBirth().toString());
        //Đặt vị trí và kích thước
        tf_DateOfBirth.setBounds(125, 126, 200, 24);
        tf_DateOfBirth.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JTextField tf_Address = new JTextField(employee.getAddress());
        //Đặt vị trí và kích thước
        tf_Address.setBounds(125, 166, 200, 24);
        tf_Address.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JTextField tf_Phone = new JTextField(employee.getPhone());
        //Đặt vị trí và kích thước
        tf_Phone.setBounds(125, 206, 200, 24);
        tf_Phone.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JTextField tf_Username = new JTextField(employee.getUsername());
        //Đặt vị trí và kích thước
        tf_Username.setBounds(125, 246, 200, 24);
        tf_Username.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JTextField tf_Password = new JTextField(employee.getPassword());
        //Đặt vị trí và kích thước
        tf_Password.setBounds(125, 286, 200, 24);
        tf_Password.setFont(new Font("Arial", Font.PLAIN, 16));
        
        panelBackGround.add(tf_ID);
        panelBackGround.add(tf_Name);
        panelBackGround.add(tf_Gender);
        panelBackGround.add(tf_DateOfBirth);
        panelBackGround.add(tf_Address);
        panelBackGround.add(tf_Phone);
        panelBackGround.add(tf_Username);
        panelBackGround.add(tf_Password);
        panelBackGround.setSize(360, 450);
        
        JButton button_Edit = new JButton("Cập nhật");
        //Căn lề trái icon
        button_Edit.setHorizontalAlignment(SwingConstants.LEFT);
        //Đặt icon
        button_Edit.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
        //Đặt vị trí và kích thước
        button_Edit.setBounds(125, 320, 120, 36);
        //Đặt con trỏ là hình bàn tay
        button_Edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_Edit.setFont(new Font("Arial", Font.PLAIN, 16));
        button_Edit.setMargin(new Insets(0, 0, 0, 0));
        button_Edit.setBackground(Color.white);
        button_Edit.setFocusable(false);
        //Sự kiện click chuột
        button_Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeDAO daoE = new EmployeeDAO();
                Employee employee = new Employee(
                        tf_Username.getText(),
                        tf_Password.getText(),
                        tf_Gender.getText(),
                        LocalDate.parse(tf_DateOfBirth.getText()),
                        Integer.parseInt(tf_ID.getText()),
                        tf_Name.getText(),
                        tf_Address.getText(),
                        tf_Phone.getText()
                );
                daoE.update(employee);
                //Sửa xong thì gán employee của Menu thành employee vừa sửa (Cập nhật lại employee)
                jmb.setEmployee(employee);
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
