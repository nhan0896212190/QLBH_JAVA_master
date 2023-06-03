/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.removeUnicode;
import DAO.ProductDAO;
import Objects.Employee;
import Objects.Product;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Duy
 */
public class Product_GUI extends JPanel {

    private JPanel thisPanel = this;//Tạo biến thisPanel để chỉ chính nó
    private List<Product> products;
    private ProductDAO daoP = new ProductDAO();
    private String imagePath = "";//lưu đường dẫn hình ảnh

    public Product_GUI(Employee emp) {
        this.setSize(900, 750);
        //Layout null để đặt component tự do
        this.setLayout(null);
        //Lấy list mặt hàng
        products = daoP.get();
        this.setBackground(new Color(166, 233, 255));

        JTable table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã hàng", "Tên hàng", "Hàng tồn", "Đơn giá"}
        ){//Cho phép sửa ô 1,2
            boolean[] canEdit = new boolean[]{
                false,  false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }});
        //Chiều cao một hàng là 22
        table.setRowHeight(22);
        //Chiều rộng các cột 0,2,3 là 120
        table.getColumnModel().getColumn(0).setMinWidth(120);
        table.getColumnModel().getColumn(0).setMaxWidth(120);
        table.getColumnModel().getColumn(2).setMinWidth(120);
        table.getColumnModel().getColumn(2).setMaxWidth(120);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        table.getColumnModel().getColumn(3).setMaxWidth(150);

        table.setFont(new Font("Arial", Font.PLAIN, 16));

        JTableHeader header = table.getTableHeader();
        //Header của table màu trắng
        header.setBackground(Color.white);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        header.setForeground(Color.blue);
        //Cho phép chọn ô hoặc hàng
        table.setRowSelectionAllowed(true);
        //tạo thanh cuộn cho bảng
        JScrollPane jsp = new JScrollPane();
        //Đặt vị trí và kích thước
        jsp.setBounds(0, 380, 900, 300);
        jsp.setViewportView(table);
        this.add(jsp);
        //Hiển thị tất cả sản phẩm trong bảng và không cập nhật lại products
        showListProductOnTable(table, products, "");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 45, 380, 230);
        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.white, 1, true), "Thông tin", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", 0, 18), Color.blue));
        panel.setBackground(new Color(166, 233, 255));
        this.add(panel);

        JLabel label_Name_Product = new JLabel("Tên hàng");
        //Đặt vị trí và kích thước
        label_Name_Product.setBounds(30, 32, 120, 24);
        label_Name_Product.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label_Name_Product);

        JLabel label_Inventory_Product = new JLabel("Tồn kho");
        //Đặt vị trí và kích thước
        label_Inventory_Product.setBounds(30, 72, 120, 24);
        label_Inventory_Product.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label_Inventory_Product);

        JLabel label_Price_Product = new JLabel("Đơn giá");
        //Đặt vị trí và kích thước
        label_Price_Product.setBounds(30, 112, 120, 24);
        label_Price_Product.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label_Price_Product);

        JLabel label_Supplier = new JLabel("Nhà cung cấp");
        //Đặt vị trí và kích thước
        label_Supplier.setBounds(30, 152, 120, 24);
        label_Supplier.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label_Supplier);

        JLabel label_GroupProduct = new JLabel("Nhóm hàng");
        //Đặt vị trí và kích thước
        label_GroupProduct.setBounds(30, 192, 120, 24);
        label_GroupProduct.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label_GroupProduct);

        JTextField tf_Name_Product = new JTextField();
        //Đặt vị trí và kích thước
        tf_Name_Product.setBounds(153, 30, 200, 24);
        tf_Name_Product.setFont(new Font("Arial", Font.BOLD, 16));
        //Không được thao tác
        tf_Name_Product.setEnabled(false);
        panel.add(tf_Name_Product);

        JTextField tf_Inventory_Product = new JTextField();
        //Đặt vị trí và kích thước
        tf_Inventory_Product.setBounds(153, 70, 200, 24);
        tf_Inventory_Product.setFont(new Font("Arial", Font.BOLD, 16));
        //Không được thao tác
        tf_Inventory_Product.setEnabled(false);
        panel.add(tf_Inventory_Product);

        JTextField tf_Price_Product = new JTextField();
        //Đặt vị trí và kích thước
        tf_Price_Product.setBounds(153, 110, 200, 24);
        tf_Price_Product.setFont(new Font("Arial", Font.BOLD, 16));
        //Không được thao tác
        tf_Price_Product.setEnabled(false);
        panel.add(tf_Price_Product);

        JTextField tf_Supplier = new JTextField();
        //Đặt vị trí và kích thước
        tf_Supplier.setBounds(153, 150, 200, 24);
        tf_Supplier.setFont(new Font("Arial", Font.BOLD, 16));
        //Không được thao tác
        tf_Supplier.setEnabled(false);
        panel.add(tf_Supplier);

        JTextField tf_GroupProduct = new JTextField();
        //Đặt vị trí và kích thước
        tf_GroupProduct.setBounds(153, 190, 200, 24);
        tf_GroupProduct.setFont(new Font("Arial", Font.BOLD, 16));
        //Không được thao tác
        tf_GroupProduct.setEnabled(false);
        panel.add(tf_GroupProduct);

        JLabel label_Image_Product = new JLabel();
        //Đặt vị trí và kích thước
        label_Image_Product.setBounds(500, 55, 300, 300);
        //Đương viền màu trắng, độ rộng 1, không cong 4 góc
        label_Image_Product.setBorder(new LineBorder(Color.white, 1, false));
        this.add(label_Image_Product);

        JButton button_Upload_Image_Product = new JButton("Ảnh");
        //Căn lề trái icon
        button_Upload_Image_Product.setHorizontalAlignment(SwingConstants.LEFT);
        //Đặt icon
        button_Upload_Image_Product.setIcon(new ImageIcon(getClass().getResource("/images/picture.png")));
        //Đặt vị trí và kích thước
        button_Upload_Image_Product.setBounds(400, 285, 80, 30);
        //Đặt con trỏ là hình bàn tay
        button_Upload_Image_Product.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_Upload_Image_Product.setMargin(new Insets(0, 0, 0, 0));
        button_Upload_Image_Product.setBackground(Color.white);
        button_Upload_Image_Product.setFont(new Font("Arial", Font.PLAIN, 16));
        button_Upload_Image_Product.setFocusable(false);
        //Tạo chú giải
        button_Upload_Image_Product.setToolTipText("Tải ảnh lên");
        //Sự kiện click chuột
        button_Upload_Image_Product.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path currentDir = Paths.get("");
                JFileChooser jfc = new JFileChooser();
                //Không cho chọn nhiều ảnh
                jfc.setMultiSelectionEnabled(false);
                //set text cho nút chọn ảnh
                jfc.setApproveButtonText("Chọn hình ảnh");
                //Đặt tiêu đề hộp thoại
                jfc.setDialogTitle("Chọn hình ảnh");
                //Đặt đường dẫn khi mở hộp thoại chọn hình ảnh
                jfc.setCurrentDirectory(new File(currentDir.toAbsolutePath() + "/src/images"));
                //Các loại hình ảnh
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("JPG file", "jpg", "png", "jpeg");
                jfc.setFileFilter(imageFilter);
                //Khi chọn hình ảnh
                if (jfc.showOpenDialog(button_Upload_Image_Product) == JFileChooser.APPROVE_OPTION) {
                    //Lấy file đã chọn
                    File f = jfc.getSelectedFile();
                    //ii là hình ảnh chưa thu nhỏ theo tỉ lệ
                    ImageIcon ii = new ImageIcon(f.getAbsolutePath());
                    //image là ii chia tỉ lệ theo kích thước label
                    Image image = (ii).getImage().getScaledInstance(
                            label_Image_Product.getSize().width,
                            label_Image_Product.getSize().height,
                            Image.SCALE_SMOOTH
                    );
                    //Lấy đường dẫn hình ảnh vừa chọn
                    imagePath = jfc.getSelectedFile().toString();
                    //Đặt hình ảnh là image vừa chia tỉ lệ theo kích thước label
                    label_Image_Product.setIcon(new ImageIcon(image));
                }
            }
        });
        //Không cho thao tác
        button_Upload_Image_Product.setEnabled(false);
        this.add(button_Upload_Image_Product);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon ii = new ImageIcon(getClass().getResource(daoP.get(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())).getImage()));
                //image là ii chia tỉ lệ theo kích thước label
                Image image = (ii).getImage().getScaledInstance(
                        label_Image_Product.getSize().width,
                        label_Image_Product.getSize().height,
                        Image.SCALE_SMOOTH
                );
                label_Image_Product.setIcon(new ImageIcon(image));
            }
        });

        JButton button_new = new JButton("Thêm");
        button_new.setFocusable(false);
        //Đặt vị trí và kích thước
        button_new.setBounds(70, 5, 90, 36);
        button_new.setMargin(new Insets(0, 0, 0, 0));
        //Đặt icon
        button_new.setIcon(new ImageIcon(getClass().getResource("/images/newProduct.png")));
        //icon căn lề trái
        button_new.setHorizontalAlignment(SwingConstants.LEFT);
        button_new.setFont(new Font("Arial", Font.PLAIN, 16));
        button_new.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_new.setBackground(Color.white);
        //Khoảng cách giữa icon và text
        button_new.setToolTipText("Thêm mới");
        button_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emp.getUsername().contains("admin")) {
                    //Cho phép thao tác các ô textfeild và button
                    tf_Name_Product.setEnabled(true);
                    tf_Price_Product.setEnabled(true);
                    tf_Inventory_Product.setEnabled(true);
                    tf_GroupProduct.setEnabled(true);
                    tf_Supplier.setEnabled(true);
                    button_Upload_Image_Product.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "ADMIN mới được thêm", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton button_refresh = new JButton("Tải lại");
        button_refresh.setFocusable(false);
        button_refresh.setBounds(240, 5, 100, 36);
        button_refresh.setMargin(new Insets(0, 0, 0, 0));
        button_refresh.setIcon(new ImageIcon(getClass().getResource("/images/refresh.png")));
        button_refresh.setHorizontalAlignment(SwingConstants.LEFT);
        button_refresh.setFont(new Font("Arial", Font.PLAIN, 16));
        button_refresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_refresh.setBackground(Color.white);
        button_refresh.setToolTipText("Tải lại");
        button_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Không cho thao tác các ô textfield và button (reset lại)
                tf_Name_Product.setEnabled(false);
                tf_Price_Product.setEnabled(false);
                tf_Inventory_Product.setEnabled(false);
                tf_GroupProduct.setEnabled(false);
                tf_Supplier.setEnabled(false);
                tf_Name_Product.setText("");
                tf_Price_Product.setText("");
                tf_Inventory_Product.setText("");
                tf_GroupProduct.setText("");
                tf_Supplier.setText("");
                button_Upload_Image_Product.setEnabled(false);
                imagePath = "";
                label_Image_Product.setIcon(null);
                showListProductOnTable(table, products, "");
            }
        });

        JButton button_edit = new JButton("Sửa");
        button_edit.setFocusable(false);
        button_edit.setBounds(410, 5, 80, 36);
        button_edit.setMargin(new Insets(0, 0, 0, 0));
        button_edit.setIcon(new ImageIcon(getClass().getResource("/images/update.png")));
        button_edit.setHorizontalAlignment(SwingConstants.LEFT);
        button_edit.setFont(new Font("Arial", Font.PLAIN, 16));
        button_edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_edit.setBackground(Color.white);
        button_edit.setToolTipText("Sửa");
        button_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emp.getUsername().contains("admin")) {
                    //Mở frame EditProduct để chỉnh sửa mặt hàng
                    //truyền vào products tại vị trí click để sửa, thisPanel để sửa xong thì gọi hàm để cập nhật lại, table để sửa xong thì hiển thị lại
                    if (table.getSelectedRow() != -1) {
                        EditProduct edit = new EditProduct(daoP.get(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString())), thisPanel, table, label_Image_Product);
                    } else {
                        JOptionPane.showMessageDialog(null, "Chưa chọn mặt hàng cần sửa", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "ADMIN mới được sửa", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton button_delete = new JButton("Xoá");
        button_delete.setFocusable(false);
        button_delete.setBounds(590, 5, 80, 36);
        button_delete.setMargin(new Insets(0, 0, 0, 0));
        button_delete.setIcon(new ImageIcon(getClass().getResource("/images/remove.png")));
        button_delete.setHorizontalAlignment(SwingConstants.LEFT);
        button_delete.setBackground(Color.white);
        button_delete.setFont(new Font("Arial", Font.PLAIN, 16));
        button_delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_delete.setToolTipText("Xoá");
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emp.getUsername().contains("admin")) {

                    if (table.getSelectedRow() != -1) {
                        Product pro=daoP.get(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
                        daoP.delete(pro);
                        showListProductOnTable(table, daoP.get(), "Sửa");
                        label_Image_Product.setIcon(null);

                    } else {
                        JOptionPane.showMessageDialog(null, "Chưa chọn mặt hàng cần xoá", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ADMIN mới được thêm", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton button_save = new JButton("Lưu");
        button_save.setFocusable(false);
        button_save.setBounds(750, 5, 80, 36);
        button_save.setMargin(new Insets(0, 0, 0, 0));
        button_save.setIcon(new ImageIcon(getClass().getResource("/images/save.png")));
        button_save.setHorizontalAlignment(SwingConstants.LEFT);
        button_save.setBackground(Color.white);
        button_save.setFont(new Font("Arial", Font.PLAIN, 16));
        button_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_save.setToolTipText("Lưu thêm mới");
        button_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emp.getUsername().contains("admin")) {
                    Product product = new Product(
                            daoP.generateIdProduct(),
                            tf_Name_Product.getText(),
                            (label_Image_Product.getIcon() != null) ? "/images/" + imagePath.split("images")[1].substring(1) : null,
                            tf_GroupProduct.getText(),
                            tf_Supplier.getText(),
                            Integer.parseInt(tf_Price_Product.getText()),
                            Integer.parseInt(tf_Inventory_Product.getText())
                    );
                    daoP.save(product);
                    products.add(product);
                    //Hiển thị lại products và cập nhật lại
                    showListProductOnTable(table, products, "Sửa");
                    //Thêm xong thì không cho thao tác tiếp
                    tf_Name_Product.setEnabled(false);
                    tf_Price_Product.setEnabled(false);
                    tf_Inventory_Product.setEnabled(false);
                    tf_GroupProduct.setEnabled(false);
                    tf_Supplier.setEnabled(false);
                    tf_Name_Product.setText("");
                    tf_Price_Product.setText("");
                    tf_Inventory_Product.setText("");
                    tf_GroupProduct.setText("");
                    tf_Supplier.setText("");
                    imagePath = null;
                    button_Upload_Image_Product.setEnabled(false);
                    label_Image_Product.setIcon(null);
                } else {
                    JOptionPane.showMessageDialog(null, "ADMIN mới được thêm", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        button_save.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(button_new);
        this.add(button_refresh);
        this.add(button_edit);
        this.add(button_delete);
        this.add(button_save);

        JLabel label_Search = new JLabel("Tìm kiếm theo tên");
        label_Search.setBounds(105, 301, 250, 24);
        label_Search.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(label_Search);

        JTextField tf_Search_Product = new JTextField("Tìm kiếm");
        tf_Search_Product.setBounds(105, 331, 380, 24);
        tf_Search_Product.setFont(new Font("Arial", Font.PLAIN, 16));
        //Sự kiện sau khi nhập 1 phím
        tf_Search_Product.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //Hiển thị products theo keyword vừa nhập
                showListProductOnTable(table, search(tf_Search_Product.getText(), products), "");
            }
        });
        //Tạo holderplace
        tf_Search_Product.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tf_Search_Product.getText().equals("Tìm kiếm")) {
                    tf_Search_Product.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tf_Search_Product.getText().equals("")) {
                    tf_Search_Product.setText("Tìm kiếm");
                }
            }

        });
        //Nhập enter
        tf_Search_Product.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    showListProductOnTable(table, search(tf_Search_Product.getText(), products), "");
                }
            }
        });
        this.add(tf_Search_Product);
        this.setVisible(false);
    }

    public void showListProductOnTable(JTable table, List<Product> ps, String action) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[4];
        if (action.equals("Sửa")) {
            products = ps;
        }
        //Hiển thị số nguyên theo dạng
        DecimalFormat df = new DecimalFormat("###,###");
        //Đặt số lượng là 0 rồi bắt đầu thêm mới lại
        model.setRowCount(0);
        for (Product product : ps) {
            row[0] = product.getId();
            row[1] = product.getName();
            row[2] = product.getInventory();
            row[3] = df.format(product.getPrice());
            model.addRow(row);
        }
    }

    //Tìm kiếm
    private List<Product> search(String keyword, List<Product> products) {
        List<Product> result = new ArrayList();
        keyword = removeUnicode.stripAccents(keyword).toLowerCase();
        for (Product product : products) {
            if (removeUnicode.stripAccents(product.getName()).toLowerCase().contains(keyword)) {
                result.add(product);
            }
        }
        return result;
    }

}
