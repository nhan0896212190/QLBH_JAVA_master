/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.ProductDAO;
import Objects.Product;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Duy
 */
public class EditProduct extends JFrame {

    private Product_GUI p;
    private String imagePath = "";

    public EditProduct(Product product, JPanel panel, JTable table, JLabel label_Image) {
        p = (Product_GUI) panel;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
        this.setLayout(null);
        this.setTitle("Cập nhật sản phẩm");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(700, 380);
        JPanel panelBackGround = new JPanel();
        panelBackGround.setBounds(0, 0, this.getSize().width, this.getSize().height);
        panelBackGround.setLayout(null);
        panelBackGround.setBackground(new Color(166, 233, 255));
        this.add(panelBackGround);

        JLabel label_ID_Product = new JLabel("Mã hàng");
        label_ID_Product.setBounds(15, 14, 110, 24);
        label_ID_Product.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_Name_Product = new JLabel("Tên hàng");
        label_Name_Product.setBounds(15, 54, 110, 24);
        label_Name_Product.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_Inventory_Product = new JLabel("Tồn kho");
        label_Inventory_Product.setBounds(15, 94, 110, 24);
        label_Inventory_Product.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_GroupProduct = new JLabel("Nhóm hàng");
        label_GroupProduct.setBounds(15, 134, 110, 24);
        label_GroupProduct.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_Supplier = new JLabel("Nhà cung cấp");
        label_Supplier.setBounds(15, 174, 110, 24);
        label_Supplier.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label_Price_Product = new JLabel("Đơn giá");
        label_Price_Product.setBounds(15, 214, 110, 24);
        label_Price_Product.setFont(new Font("Arial", Font.BOLD, 16));

        panelBackGround.add(label_ID_Product);
        panelBackGround.add(label_Name_Product);
        panelBackGround.add(label_Inventory_Product);
        panelBackGround.add(label_GroupProduct);
        panelBackGround.add(label_Supplier);
        panelBackGround.add(label_Price_Product);

        JTextField tf_ID_Product = new JTextField("" + product.getId());
        tf_ID_Product.setBounds(135, 14, 200, 24);
        tf_ID_Product.setEditable(false);
        tf_ID_Product.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField tf_Name_Product = new JTextField(product.getName());
        tf_Name_Product.setBounds(135, 54, 200, 24);
        tf_Name_Product.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField tf_Inventory_Product = new JTextField("" + product.getInventory());
        tf_Inventory_Product.setBounds(135, 94, 200, 24);
        tf_Inventory_Product.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField tf_GroupProduct = new JTextField(product.getGroup());
        tf_GroupProduct.setBounds(135, 134, 200, 24);
        tf_GroupProduct.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField tf_Supplier = new JTextField(product.getSupplier());
        tf_Supplier.setBounds(135, 174, 200, 24);
        tf_Supplier.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField tf_Price_Product = new JTextField("" + product.getPrice());
        tf_Price_Product.setBounds(135, 214, 200, 24);
        tf_Price_Product.setFont(new Font("Arial", Font.BOLD, 16));

        JButton button_Edit_Product = new JButton("Cập nhật");
        button_Edit_Product.setBounds(120, 274, 120, 36);
        button_Edit_Product.setHorizontalAlignment(SwingConstants.LEFT);
        button_Edit_Product.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
        button_Edit_Product.setFont(new Font("Arial", Font.PLAIN, 16));
        button_Edit_Product.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_Edit_Product.setMargin(new Insets(0, 0, 0, 0));
        button_Edit_Product.setFocusable(false);

        imagePath = product.getImage();

        panelBackGround.add(button_Edit_Product);
        panelBackGround.add(tf_ID_Product);
        panelBackGround.add(tf_Name_Product);
        panelBackGround.add(tf_Inventory_Product);
        panelBackGround.add(tf_GroupProduct);
        panelBackGround.add(tf_Supplier);
        panelBackGround.add(tf_Price_Product);

        JLabel label_Image_Product = new JLabel();
        label_Image_Product.setBounds(360, 10, 300, 300);
        label_Image_Product.setBorder(new LineBorder(Color.white, 1, false));
        if (product.getImage() != null) {
            label_Image_Product.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(product.getImage())).getImage().getScaledInstance(
                    label_Image_Product.getSize().width,
                    label_Image_Product.getSize().height,
                    Image.SCALE_SMOOTH
            )));
        }
        JButton button_Upload_Image_Product = new JButton("Ảnh");
        button_Upload_Image_Product.setBounds(245, 274, 80, 36);
        button_Upload_Image_Product.setHorizontalAlignment(SwingConstants.LEFT);
        button_Upload_Image_Product.setIcon(new ImageIcon(getClass().getResource("/images/picture.png")));
        button_Upload_Image_Product.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_Upload_Image_Product.setMargin(new Insets(0, 0, 0, 0));
        button_Upload_Image_Product.setFont(new Font("Arial", Font.PLAIN, 16));
        button_Upload_Image_Product.setFocusable(false);
        button_Upload_Image_Product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path currentDir = Paths.get("");
                JFileChooser jfc = new JFileChooser();
                jfc.setMultiSelectionEnabled(false);
                jfc.setApproveButtonText("Chọn hình ảnh");
                jfc.setDialogTitle("Chọn hình ảnh");
                jfc.setCurrentDirectory(new File(currentDir.toAbsolutePath() + "/src/images"));
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG file", "jpg", "png", "jpeg");
                jfc.setFileFilter(imageFilter);
                if (jfc.showOpenDialog(button_Upload_Image_Product) == JFileChooser.APPROVE_OPTION) {
                    java.io.File f = jfc.getSelectedFile();
                    ImageIcon ii = new ImageIcon(f.getAbsolutePath());
                    Image image = (ii).getImage().getScaledInstance(
                            label_Image_Product.getSize().width,
                            label_Image_Product.getSize().height,
                            Image.SCALE_SMOOTH
                    );
                    imagePath = jfc.getSelectedFile().toString();
                    label_Image_Product.setIcon(new ImageIcon(image));
                }
            }
        });
        panelBackGround.add(label_Image_Product);
        panelBackGround.add(button_Upload_Image_Product);

        button_Edit_Product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductDAO daoP = new ProductDAO();
                Product pro = new Product(
                        Integer.parseInt(tf_ID_Product.getText()),
                        tf_Name_Product.getText(),
                        (!imagePath.equals("")) ? "/images/" + imagePath.split("images")[1].substring(1) : "",
                        tf_GroupProduct.getText(),
                        tf_Supplier.getText(),
                        Integer.parseInt(tf_Price_Product.getText()),
                        Integer.parseInt(tf_Inventory_Product.getText())
                );
                daoP.update(pro);
                ImageIcon ii = new ImageIcon(getClass().getResource(pro.getImage()));
                Image image = (ii).getImage().getScaledInstance(label_Image.getSize().width, label_Image.getSize().height, Image.SCALE_SMOOTH);
                label_Image.setIcon(new ImageIcon(image));
                p.showListProductOnTable(table, daoP.get(), "Sửa");
                imagePath = null;
                close();
            }
        });

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void close() {
        this.setVisible(false);
    }
}
