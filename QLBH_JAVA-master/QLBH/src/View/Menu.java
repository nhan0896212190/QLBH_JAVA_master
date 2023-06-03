/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Objects.Employee;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Duy
 */
public class Menu extends JMenuBar {

    private Employee empl;
    private JMenuBar thisMenu;//Tạo biến thisMenu
    private JPanel thisPanel = new JPanel();

    public Menu(JFrame frame) {
        thisMenu = this;//Gán bằng menuBar hiện tại
        //Menu tài khoản
        JMenu menu0 = new JMenu("Trang chủ");
        menu0.setFont(new Font("Arial", Font.BOLD, 16));
        menu0.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Menu hoá đơn
        JMenu menu1 = new JMenu("Hóa đơn");
        menu1.setFont(new Font("Arial", Font.BOLD, 16));
        //Đặt chú thích
        menu1.setToolTipText("Cửa sổ hoá đơn");
        //Đặt biểu tượng con trỏ là bàn tay
        menu1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Menu mặt hàng
        JMenu menu2 = new JMenu("Mặt hàng");
        menu2.setFont(new Font("Arial", Font.BOLD, 16));
        //Đặt chú thích
        menu2.setToolTipText("Cửa sổ mặt hàng");
        menu2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Menu thống kê
        JMenu menu3 = new JMenu("Thống kê");
        menu3.setFont(new Font("Arial", Font.BOLD, 16));
        //Đặt chú thích
        menu3.setToolTipText("Cửa sổ Thống kê");
        //Đặt biểu tượng con trỏ là bàn tay
        menu3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Menu khách hàng
        JMenu menu4 = new JMenu("Khách hàng");
        menu4.setFont(new Font("Arial", Font.BOLD, 16));
        //Đặt chú thích
        menu4.setToolTipText("Cửa sổ khách hàng");
        //Đặt biểu tượng con trỏ là bàn tay
        menu4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Menu khách hàng
        JMenu menu5 = new JMenu("Đăng nhập");
        menu5.setFont(new Font("Arial", Font.BOLD, 16));
        //Đặt chú thích
        menu5.setToolTipText("Đăng nhập");
        //Đặt biểu tượng con trỏ là bàn tay
        menu5.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.add(menu0);
        this.add(menu1);
        this.add(menu2);
        this.add(menu3);
        this.add(menu4);
        this.add(menu5);
        menu0.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                menu0.setSelected(true);
            }
        });
        menu1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                menu1.setSelected(true);
            }
        });
        menu2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                menu2.setSelected(true);
            }
        });
        menu3.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                menu3.setSelected(true);
            }
        });
        menu4.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                menu4.setSelected(true);
            }
        });
        menu5.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                menu5.setSelected(true);
            }
        });

        menu0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menu0.setSelected(false);
            }
        });
        menu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menu1.setSelected(false);
            }
        });
        menu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menu2.setSelected(false);
            }
        });
        menu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menu3.setSelected(false);
            }
        });
        menu4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menu4.setSelected(false);
            }
        });
        menu5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                menu5.setSelected(false);
            }
        });
        frame.add(thisPanel);
        menu5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (empl == null) {
                    Login_GUI login = new Login_GUI(thisMenu, menu5);
                }
            }
        });
        menu0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                menu1.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                frame.setSize(913, 750);
                if (thisPanel != null) {
                    thisPanel.setVisible(false);
                    thisPanel.getParent().remove(thisPanel);
                    thisPanel = null;
                    Index.toggleHome(true);
                }
                frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                menu1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        menu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (empl != null) {
                    frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    menu1.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    frame.setSize(913, 750);
                    if (thisPanel != null) {
                        thisPanel.setVisible(false);
                        thisPanel.getParent().remove(thisPanel);
                    }
                    Order_GUI order = new Order_GUI(empl);
                    frame.add(order);
                    order.setVisible(true);
                    thisPanel = order;
                    Index.toggleHome(false);
                    frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    menu1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
        menu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (empl != null) {
                    frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    menu2.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    frame.setSize(913, 750);
                    if (thisPanel != null) {
                        thisPanel.setVisible(false);
                        thisPanel.getParent().remove(thisPanel);
                    }
                    Product_GUI product = new Product_GUI(empl);
                    thisPanel = product;
                    frame.add(product);
                    product.setVisible(true);
                    Index.toggleHome(false);
                    frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    menu2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
        menu3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (empl != null) {
                    frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    menu3.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    frame.setSize(913, 750);
                    if (thisPanel != null) {
                        thisPanel.setVisible(false);
                        thisPanel.getParent().remove(thisPanel);
                    }
                    Statistics_GUI statistics = new Statistics_GUI();
                    frame.add(statistics);
                    statistics.setVisible(true);
                    thisPanel = statistics;
                    Index.toggleHome(false);
                    frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    menu3.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
        menu4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (empl != null) {
                    frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    menu4.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                    frame.setSize(913, 750);
                    if (thisPanel != null) {
                        thisPanel.setVisible(false);
                        thisPanel.getParent().remove(thisPanel);
                    }
                    Customer_GUI customer = new Customer_GUI();
                    frame.add(customer);
                    customer.setVisible(true);
                    thisPanel = customer;
                    Index.toggleHome(false);
                    frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    menu4.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
    }

    public void setEmployee(Employee e) {
        empl = e;
    }

    //Đăng nhập xong thì đổi menu Đăng nhập -> Tài khoản (gồm thông tin cá nhân và đăng xuất)
    public void toggleMenu(JMenu menu) {
        menu.setText("Tài khoản");
        JMenuItem item1 = new JMenuItem("Thông tin cá nhân");
        item1.setToolTipText("Xem thông tin cá nhân");
        JMenuBar mnb = this;
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditEmployee edit = new EditEmployee(mnb, empl);
            }
        });
        JMenuItem item2 = new JMenuItem("Đăng xuất");
        item2.setToolTipText("Đăng xuất");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empl = null;
                if (thisPanel != null) {
                    thisPanel.setVisible(false);
                    thisPanel.getParent().remove(thisPanel);
                    thisPanel = null;
                    Index.toggleHome(true);
                }
                menu.removeAll();
                menu.setText("Đăng nhập");
                menu.setToolTipText("Đăng nhập");
            }
        });
        menu.add(item1);
        menu.add(item2);
    }
}
