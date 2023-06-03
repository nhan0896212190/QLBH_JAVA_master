/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Objects.Customer;
import Objects.Order;
import Objects.OrderItem;
import Objects.Product;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Duy
 */
public class Home extends JPanel {

    private Customer customer;

    public Home() {
        this.setLayout(null);

        this.setSize(913, 750);
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label.setLocation(0, 0);
        label.setSize(913, 750);
        ImageIcon ii = new ImageIcon(getClass().getResource("/images/Home.jpg"));
        Image image = (ii).getImage().getScaledInstance(
                label.getSize().width,
                label.getSize().height,
                Image.SCALE_SMOOTH
        );
        label.setIcon(new ImageIcon(image));

        this.add(label);
        this.setVisible(false);
    }

    public void setCustomer(Customer x) {
        customer = x;
    }

}
