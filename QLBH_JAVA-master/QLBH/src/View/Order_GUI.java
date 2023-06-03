/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Objects.Customer;
import Objects.Employee;
import Objects.Order;
import Objects.OrderItem;
import Objects.Product;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Objects.Statistics;
import DAO.StatisticsDAO;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.SwingConstants;

/**
 *
 * @author Duy
 */
public class Order_GUI extends JPanel {

    private OrderDAO daoO = new OrderDAO();
    private List<OrderItem> items = new ArrayList();
    private Order order;
    private ProductDAO daoP = new ProductDAO();
    private List<Product> products;
    private int idOrder = 0;
    private OrderItem item;
    private Customer customer;

    public Order_GUI(Employee employee) {
        this.setSize(913, 750);
        this.setLayout(null);
        this.setBackground(new Color(166, 233, 255));
        JPanel p1 = this;
        DecimalFormat df = new DecimalFormat("###,###");
        JTable table1 = new JTable();
        table1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Số hóa đơn", "Mặt hàng", "Số lượng", "Đơn giá", "Thành tiền"}
        ) {
            //Cho phép sửa ô 1,2
            boolean[] canEdit = new boolean[]{
                false, true, true, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        table1.setRowSelectionAllowed(true);
        table1.setRowHeight(26);
        table1.setFont(new Font("Arial", Font.PLAIN, 16));

        table1.getColumnModel().getColumn(0).setMinWidth(150);
        table1.getColumnModel().getColumn(0).setMaxWidth(150);
        table1.getColumnModel().getColumn(2).setMinWidth(150);
        table1.getColumnModel().getColumn(2).setMaxWidth(150);
        table1.getColumnModel().getColumn(3).setMinWidth(150);
        table1.getColumnModel().getColumn(3).setMaxWidth(150);
        table1.getColumnModel().getColumn(4).setMinWidth(150);
        table1.getColumnModel().getColumn(4).setMaxWidth(150);

        JComboBox jcb = new JComboBox();
        jcb.setFont(new Font("Arial", Font.PLAIN, 16));

        JTableHeader header1 = table1.getTableHeader();
        header1.setFont(new Font("Arial", Font.BOLD, 24));
        header1.setForeground(Color.blue);
        header1.setBackground(Color.white);
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        JScrollPane jsp1 = new JScrollPane();
        jsp1.setBounds(0, 90, 900, 700);
        jsp1.setViewportView(table1);
        this.add(jsp1);
        products = daoP.get();
        for (Product pro : products) {
            jcb.addItem(pro.getName());
        }

        JLabel label_Customer = new JLabel("Khách hàng: ");
        //Đặt vị trí và kích thước
        label_Customer.setBounds(0, 46, 110, 24);
        label_Customer.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(label_Customer);

        JLabel label_Customer_Result = new JLabel();
        //Đặt vị trí và kích thước
        label_Customer_Result.setBounds(115, 46, 200, 24);
        label_Customer_Result.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(label_Customer_Result);

        JPanel panelTool = new JPanel();
        panelTool.setLayout(null);
        panelTool.setBounds(0, 5, 913, 40);
        JButton button_new = new JButton("Thêm");
        button_new.setFocusable(false);
        button_new.setBounds(50, 0, 140, 36);
        button_new.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_new.setMargin(new Insets(0, 0, 0, 0));
        button_new.setIcon(new ImageIcon(getClass().getResource("/images/new.png")));
        button_new.setHorizontalAlignment(SwingConstants.LEFT);
        button_new.setFont(new Font("Arial", Font.PLAIN, 16));
        button_new.setIconTextGap(30);
        button_new.setToolTipText("Thêm mới");
        button_new.setBackground(Color.white);
        button_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idOrder = daoO.generateIdOrder();
                Object[] row = new Object[5];
                row[0] = idOrder;
                row[2] = 1;
                row[3] = "";
                row[4] = "";
                model.addRow(row);
                //Thêm comboBox vào cột 1
                table1.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jcb));
            }
        });

        JButton button_refresh = new JButton("Tải lại");
        button_refresh.setFocusable(false);
        button_refresh.setBounds(250, 0, 140, 36);
        button_refresh.setMargin(new Insets(0, 0, 0, 0));
        button_refresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_refresh.setIcon(new ImageIcon(getClass().getResource("/images/refresh.png")));
        button_refresh.setHorizontalAlignment(SwingConstants.LEFT);
        button_refresh.setFont(new Font("Arial", Font.PLAIN, 16));
        button_refresh.setIconTextGap(30);
        button_refresh.setToolTipText("Tải lại");
        button_refresh.setBackground(Color.white);
        button_refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                customer = null;
                items = new ArrayList();
                label_Customer_Result.setText("");
            }
        });

        JButton button_save = new JButton("Xuất hoá đơn");
        button_save.setFocusable(false);
        button_save.setBounds(450, 0, 140, 36);
        button_save.setMargin(new Insets(0, 0, 0, 0));
        button_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_save.setIcon(new ImageIcon(getClass().getResource("/images/invoice.png")));
        button_save.setHorizontalAlignment(SwingConstants.LEFT);
        button_save.setFont(new Font("Arial", Font.PLAIN, 16));
        button_save.setToolTipText("Xuất hoá đơn ra file PDF");
        button_save.setBackground(Color.white);
        button_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customer != null) {
                    order = new Order(idOrder, customer, items, LocalDate.now(), employee);
                    for (int i = 0; i < items.size(); i++) {
                        try {
                            items.get(i).setCount(Integer.parseInt(table1.getValueAt(i, 2).toString()));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Số lượng mặt hàng " + i + " không hợp lệ", "Chú ý", WARNING_MESSAGE);
                        }
                    }
                    items = daoO.updateOrderItem(items);
                    order.setListDetail(items);
                    if (order.getTotalProduct() > 0) {
                        daoO.save(order);
                        int price = order.getPrice();
                        StatisticsDAO daoR = new StatisticsDAO();
                        Statistics re = new Statistics(LocalDate.now(), order.getTotalProduct(), price);
                        daoR.save(re);
                        daoO.saveListOrderItem(order);
                        for (OrderItem item : items) {
                            daoP.updateInventory(item);
                        }
                        model.setRowCount(0);
                        daoO.ExportOrder(order);
                    } else {
                        JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ", "Chú ý", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng", "Chú ý", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panelTool.add(button_new);
        panelTool.add(button_refresh);
        panelTool.add(button_save);
        panelTool.setBackground(new Color(166, 233, 255));
        this.add(panelTool);

        JButton button_Add_Customer = new JButton("Chọn khách hàng");
        button_Add_Customer.setBounds(650, 0, 200, 36);
        button_Add_Customer.setMargin(new Insets(0, 0, 0, 0));
        button_Add_Customer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button_Add_Customer.setIcon(new ImageIcon(getClass().getResource("/images/selectCustomer.png")));
        button_Add_Customer.setFont(new Font("Arial", Font.PLAIN, 16));
        button_Add_Customer.setHorizontalAlignment(SwingConstants.LEFT);
        button_Add_Customer.setIconTextGap(20);
        button_Add_Customer.setToolTipText("Chọn khách hàng");
        button_Add_Customer.setFocusable(false);
        button_Add_Customer.setBackground(Color.white);
        button_Add_Customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCustomer add = new AddCustomer(p1, label_Customer_Result);
            }
        });
        panelTool.add(button_Add_Customer);
        //Chỉnh số lượng thì tự động cập nhật thành tiền
        table1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int index = table1.getSelectedRow();
                int count = 0;
                //Nhập số lượng
                if (index != -1) {
                    try {
                        count = Integer.parseInt(table1.getValueAt(index, 2).toString());
                    } catch (NumberFormatException ex) {
                        count = 0;
                    }
                }
                if (count > 0 && !items.isEmpty()) {
                    if (index < items.size()) {
                        Product product = items.get(index).getProduct();
                        if (product.getInventory() < count) {
                            JOptionPane.showMessageDialog(null, "Số lượng không đủ, mặt hàng này chỉ còn: " + product.getInventory(), "Chú ý", JOptionPane.WARNING_MESSAGE);
                            table1.setValueAt(0, index, 2);
                            table1.setValueAt(0, index, 4);
                        } else {
                            table1.setValueAt(df.format(items.get(index).getProduct().getPrice() * count), index, 4);
                        }
                    }
                }
                //Nhập số âm thì sửa lại thành 0
                if (index != -1 && count <= 0) {
                    table1.setValueAt(0, index, 2);
                    table1.setValueAt(0, index, 4);
                }
            }
        });
        //Chọn mặt hàng
        jcb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int indexClick = table1.getSelectedRow();
                if (e.getStateChange() == ItemEvent.SELECTED) {//Nếu là sự kiện chọn item của combobox 
                    if (model.getRowCount() == indexClick + 1) {//Chọn xong mặt hàng tự động thêm 1 hàng mới trong bảng
                        Object[] row = new Object[5];
                        row[0] = idOrder;
                        row[2] = 1;
                        row[3] = "";
                        row[4] = "";
                        model.addRow(row);
                    }
                    item = new OrderItem(products.get(jcb.getSelectedIndex()), 0);
                    model.setValueAt(df.format(products.get(jcb.getSelectedIndex()).getPrice()), indexClick, 3);
                    if (items.size() - indexClick >= 1) {//Sửa lại item đã chọn trước đó
                        items.remove(indexClick);
                        items.add(indexClick, item);
                    } else if (items.size() - indexClick == 0) {//item chưa có trong list thì thêm mới
                        items.add(item);
                    }
                }
            }
        });
        this.setVisible(false);

    }

    public void showListOrderOnTable(JTable table, List<Order> os) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[4];
        DecimalFormat df = new DecimalFormat("###,###");
        model.setRowCount(0);
        for (Order order : os) {
            row[0] = order.getId();
            row[1] = order.getDate();
            row[2] = order.getTotalProduct();
            row[3] = df.format(order.getPrice());
            model.addRow(row);
        }
    }

    public void setCustomer(Customer x) {
        customer = x;
    }

}
