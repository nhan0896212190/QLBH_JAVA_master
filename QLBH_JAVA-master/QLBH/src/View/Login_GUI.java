/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.EmployeeDAO;
import Objects.Customer;
import Objects.Employee;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Duy
 */
public class Login_GUI extends JFrame {

    private EmployeeDAO daoE = new EmployeeDAO();
    private Menu menuBar;

    public Login_GUI(JMenuBar menubar, JMenu menu) {
        menuBar = (Menu) menubar;
        JFrame f = this;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
        this.setLayout(null);
        this.setSize(356, 180);
        this.setTitle("Đăng nhập");
        this.setLocationRelativeTo(null);
        JPanel bg = new JPanel();
        bg.setLayout(null);
        bg.setSize(356, 180);
        bg.setLocation(0, 0);
        bg.setOpaque(true);
        bg.setBackground(new Color(135, 206, 255));

        this.add(bg);
        JLabel label_username = new JLabel("Tài khoản ");
        label_username.setBounds(20,22,100,20);
        label_username.setFont(new Font("Arial", Font.BOLD, 16));

        bg.add(label_username);

        JLabel label_password = new JLabel("Mật khẩu ");
        label_password.setFont(new Font("Arial", Font.BOLD, 16));
        label_password.setBounds(20,62,100,20);
        bg.add(label_password);

        JTextField tf_username = new JTextField();
        tf_username.setBounds(120, 20, 200, 24);
        tf_username.setFont(new Font("Arial", Font.PLAIN, 16));
        bg.add(tf_username);

        JPasswordField tf_password = new JPasswordField();
        tf_password.setBounds(120, 60, 200, 24);
        tf_password.setFont(new Font("Arial", Font.PLAIN, 16));
        bg.add(tf_password);

        JButton button_login = new JButton("Đăng nhập");
        button_login.setSize(120, 30);
        button_login.setMargin(new Insets(0, 0, 0, 0));
        button_login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_login.setContentAreaFilled(false);
        button_login.setBorder(new LineBorder(Color.black, 1, false));

        button_login.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                button_login.setForeground(Color.blue);
                button_login.setBorder(new LineBorder(Color.blue, 1, false));
            }
        });
        button_login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                button_login.setForeground(Color.black);
                button_login.setBorder(new LineBorder(Color.black, 1, false));
            }
        });
        button_login.setFont(new Font("Arial", Font.PLAIN, 16));
        button_login.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        button_login.setLocation(120, 95);
        button_login.setFocusable(false);
        button_login.setToolTipText("Đăng nhập");
        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee em = daoE.login(tf_username.getText(), String.valueOf(tf_password.getPassword()));
                if (em != null) {
                    menuBar.setEmployee(em);
                    menuBar.toggleMenu(menu);
                    f.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập không thành công", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        tf_username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Employee em = daoE.login(tf_username.getText(), String.valueOf(tf_password.getPassword()));
                    if (em != null) {
                        menuBar.setEmployee(em);
                        menuBar.toggleMenu(menu);
                        f.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Đăng nhập không thành công", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        tf_password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Employee em = daoE.login(tf_username.getText(), String.valueOf(tf_password.getPassword()));
                    if (em != null) {
                        menuBar.setEmployee(em);
                        menuBar.toggleMenu(menu);
                        f.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Đăng nhập không thành công", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        bg.add(button_login);
        this.setResizable(false);
        this.setVisible(true);
    }

}
