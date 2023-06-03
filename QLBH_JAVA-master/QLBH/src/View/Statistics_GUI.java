/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.ProductDAO;
import DAO.StatisticsDAO;
import Objects.Product;
import Objects.Statistics;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Duy
 */
public class Statistics_GUI extends JPanel {

    private ProductDAO daoP = new ProductDAO();//Biến daoP là 1 DAO của mặt hàng
    private List<Product> products;//Biến products là 1 list mặt hàng
    private ChartPanel barPanel;//Biến barPanel là 1 panel biểu đồ
    private StatisticsDAO daoR = new StatisticsDAO();//Biến daoR là 1 DAO của doanh thu

    public Statistics_GUI() {
        this.setSize(913, 750);
        //Layout null để đặt component tự do
        this.setLayout(null);
        //Tạo p là chính nó, vì trong xử lí sự kiện không dùng từ khoá this để chỉ panel
        JPanel p = this;
        this.setBackground(new Color(166, 233, 255));
        //Lấy tất cả mặt hàng
        products = daoP.get();
        //Định dạng số nguyên theo dạng 100,000,000,000
        DecimalFormat df = new DecimalFormat("###,###");

        //Ô thống kê 1
        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        //Layout null để set component tự do
        p1.setLayout(null);
        //Đặt vị trí và kích thước
        p1.setBounds(10, 0, 220, 130);
        //Đặt đường viền là màu đen, độ rộng = 1, độ cong 4 góc
        p1.setBorder(new LineBorder(Color.BLACK, 1, false));
        JLabel pic1 = new JLabel();
        pic1.setIcon(new ImageIcon(getClass().getResource("/images/chart.png")));
        //Đặt vị trí và kích thước
        pic1.setBounds(5, 2, 96, 96);
        p1.add(pic1);
        JLabel pic2 = new JLabel("<html>Doanh thu<br>2021-05-17</html>", SwingConstants.RIGHT);
        pic2.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic2.setBounds(100, 5, 100, 50);
        p1.add(pic2);
        JLabel pic3 = new JLabel("0 VNĐ", SwingConstants.RIGHT);
        pic3.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic3.setBounds(0, 100, 210, 20);
        p1.add(pic3);
        this.add(p1);

        //Ô thống kê 2
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        //Layout null để set component tự do
        p2.setLayout(null);
        //Đặt vị trí và kích thước
        p2.setBounds(240, 0, 220, 130);
        //Đặt đường viền là màu đen, độ rộng = 1, độ cong 4 góc
        p2.setBorder(new LineBorder(Color.BLACK, 1, false));;
        JLabel pic4 = new JLabel();
        pic4.setIcon(new ImageIcon(getClass().getResource("/images/chart.png")));
        //Đặt vị trí và kích thước
        pic4.setBounds(5, 2, 96, 96);
        p2.add(pic4);
        JLabel pic5 = new JLabel("<html>Doanh thu<br>2021-05</html>", SwingConstants.RIGHT);
        pic5.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic5.setBounds(100, 5, 100, 50);
        p2.add(pic5);
        JLabel pic6 = new JLabel("0 VNĐ", SwingConstants.RIGHT);
        pic6.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic6.setBounds(0, 100, 210, 20);
        p2.add(pic6);
        this.add(p2);

        //Ô thống kê 3
        JPanel p3 = new JPanel();
        p3.setBackground(Color.white);
        //Layout null để set component tự do
        p3.setLayout(null);
        //Đặt vị trí và kích thước
        p3.setBounds(470, 0, 220, 130);
        //Đặt đường viền là màu đen, độ rộng = 1, độ cong 4 góc
        p3.setBorder(new LineBorder(Color.BLACK, 1, false));
        JLabel pic7 = new JLabel();
        pic7.setIcon(new ImageIcon(getClass().getResource("/images/chart.png")));
        //Đặt vị trí và kích thước
        pic7.setBounds(5, 2, 96, 96);
        p3.add(pic7);
        JLabel pic8 = new JLabel("<html>Doanh thu<br>năm 2021</html>", SwingConstants.RIGHT);
        pic8.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic8.setBounds(100, 5, 100, 50);
        p3.add(pic8);
        JLabel pic9 = new JLabel("0 VNĐ", SwingConstants.RIGHT);
        pic9.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic9.setBounds(0, 100, 210, 20);
        p3.add(pic9);
        this.add(p3);

        //Ô thống kê 4
        JPanel p4 = new JPanel();
        p4.setBackground(Color.white);
        //Layout null để set component tự do
        p4.setLayout(null);
        //Đặt vị trí và kích thước
        p4.setBounds(10, 140, 220, 130);
        //Đặt đường viền là màu đen, độ rộng = 1, độ cong 4 góc
        p4.setBorder(new LineBorder(Color.BLACK, 1, false));
        JLabel pic10 = new JLabel();
        pic10.setIcon(new ImageIcon(getClass().getResource("/images/device.png")));
        //Đặt vị trí và kích thước
        pic10.setBounds(5, 2, 96, 96);
        p4.add(pic10);
        JLabel pic11 = new JLabel("Tổng", SwingConstants.RIGHT);
        pic11.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic11.setBounds(100, 5, 100, 50);
        p4.add(pic11);
        JLabel pic12 = new JLabel("" + products.size() + " mặt hàng", SwingConstants.RIGHT);
        pic12.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic12.setBounds(0, 100, 210, 20);
        p4.add(pic12);
        this.add(p4);

        //Ô thống kê 5
        JPanel p5 = new JPanel();
        p5.setBackground(Color.white);
        //Layout null để set component tự do
        p5.setLayout(null);
        //Đặt vị trí và kích thước
        p5.setBounds(240, 140, 220, 130);
        //Đặt đường viền là màu đen, độ rộng = 1, độ cong 4 góc
        p5.setBorder(new LineBorder(Color.BLACK, 1, false));;
        JLabel pic13 = new JLabel();
        pic13.setIcon(new ImageIcon(getClass().getResource("/images/device.png")));
        //Đặt vị trí và kích thước
        pic13.setBounds(5, 2, 96, 96);
        p5.add(pic13);
        JLabel pic14 = new JLabel("Đã bán", SwingConstants.RIGHT);
        pic14.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic14.setBounds(100, 5, 100, 50);
        p5.add(pic14);
        JLabel pic15 = new JLabel("", SwingConstants.RIGHT);
        pic15.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic15.setBounds(0, 100, 210, 20);
        p5.add(pic15);
        this.add(p5);

        //Ô thống kê 6
        JPanel p6 = new JPanel();
        p6.setBackground(Color.white);
        //Layout null để set component tự do
        p6.setLayout(null);
        //Đặt vị trí và kích thước
        p6.setBounds(470, 140, 220, 130);
        //Đặt đường viền là màu đen, độ rộng = 1, độ cong 4 góc
        p6.setBorder(new LineBorder(Color.BLACK, 1, false));;
        JLabel pic16 = new JLabel();
        pic16.setIcon(new ImageIcon(getClass().getResource("/images/device.png")));
        //Đặt vị trí và kích thước
        pic16.setBounds(5, 2, 96, 96);
        p6.add(pic16);
        JLabel pic17 = new JLabel("Hết hàng", SwingConstants.RIGHT);
        pic17.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic17.setBounds(100, 5, 100, 50);
        p6.add(pic17);
        JLabel pic18 = new JLabel("" + daoP.countProductSelledAll(products) + " mặt hàng", SwingConstants.RIGHT);
        pic18.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic18.setBounds(0, 100, 210, 20);
        p6.add(pic18);
        this.add(p6);
        //Tạo biến barChartData để gán giá trị vào biểu đồ
        DefaultCategoryDataset barChartData = new DefaultCategoryDataset();
        JLabel pic19 = new JLabel("Chọn thời gian");
        pic19.setFont(new Font("Arial", Font.PLAIN, 16));
        //Đặt vị trí và kích thước
        pic19.setBounds(700, 200, 150, 40);
        this.add(pic19);
        //Ô chọn thời gian
        JDateChooser jdc = new JDateChooser();
        //Đặt vị trí và kích thước
        jdc.setBounds(700, 240, 150, 30);
        //Đặt ngày mặt định là hôm nay
        jdc.setDate(Date.from(Instant.now()));
        jdc.setFont(new Font("Arial", Font.PLAIN, 16));
        jdc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jdc.setToolTipText("Chọn thời gian để thống kê");
        jdc.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //Vì lần chạy đầu tiên sẽ có sự kiện là graphicsConfiguration và ancestor
                //System.out.println(evt.getPropertyName());
                //Để giảm thời gian chạy thì cho khác sự kiện graphicsConfiguration
                //Khi chạy đến sự kiện ancestor, tự động tạo biểu đồ cho tiện
                if (!evt.getPropertyName().equals("graphicsConfiguration")) {
                    LocalDate date;
                    JPanel pa = new JPanel();
                    if (jdc.getDate() == null) {
                        date = LocalDate.now();
                    } else {
                        date = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(jdc.getDate()));
                    }
                    //Lấy doanh thu theo năm
                    List<Statistics> revenueYear = daoR.getMonth(0, date.getYear());
                    //Tính tổng doanh thu
                    int sumRevenue2 = 0;
                    for (Statistics r : revenueYear) {
                        sumRevenue2 += r.getRevenue();
                    }
                    //Hiển thị doanh thu theo năm
                    pic9.setText("" + df.format(sumRevenue2) + " VNĐ");
                    //Lấy doanh thu theo tháng
                    List<Statistics> revenueMonth = daoR.getMonth(date.getMonthValue(), date.getYear());
                    //Hiển thị tiêu đề ô thống kê 1
                    pic2.setText("Ngày " + date.getDayOfMonth());
                    //Hiển thị tiêu đề ô thống kê 2
                    pic5.setText("Tháng " + date.getMonthValue());
                    //Hiển thị tiêu đề ô thống kê 3
                    pic8.setText("Năm " + date.getYear());
                    //Tính tổng doanh thu
                    int sumRevenue1 = 0;
                    for (Statistics r : revenueMonth) {
                        sumRevenue1 += r.getRevenue();
                    }
                    //Đếm số lượng mặt hàng bán được
                    int sumCount = 0;
                    for (Statistics r : revenueMonth) {
                        sumCount += r.getCount();
                    }
                    //Hiển thị doanh thu theo ngày
                    pic3.setText("" + df.format(daoR.get(date).getRevenue()) + " VNĐ");
                    //Hiển thị doanh thu theo tháng
                    pic6.setText("" + df.format(sumRevenue1) + " VNĐ");
                    //Hiển thị doanh thu theo năm
                    pic15.setText("" + sumCount + " mặt hàng");
                    //nếu đang hiển thị biểu đồ thì remove (remove cái cũ để hiển thị cái mới)
                    if (barPanel != null) {
                        barPanel.setVisible(false);
                        barPanel.getParent().remove(barPanel);
                    }
                    //Lấy ngày cuối cùng của tháng đã chọn
                    int lastDay = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
                    //Nếu có doanh thu theo tháng
                    if (!revenueMonth.isEmpty()) {
                        //Lấy ngày cuối cùng trong tháng
                        int j = 0;
                        for (int i = 1; i <= lastDay; i++) {
                            if (j < revenueMonth.size() && revenueMonth.get(j).getDate().getDayOfMonth() == i) {
                                barChartData.setValue(revenueMonth.get(j).getRevenue(), "Ngày", "" + revenueMonth.get(j).getDate().getDayOfMonth());
                                j++;
                            } else {
                                barChartData.setValue(0, "Ngày", "" + i);
                            }
                        }
                        //Tạo tiêu đề biểu đồ
                        JFreeChart barChart1 = ChartFactory.createBarChart("Thống kê doanh thu tháng " + date.getMonthValue(), "Ngày", "Doanh thu", barChartData, PlotOrientation.VERTICAL, false, true, false);
                        //Đặt background màu trắng 
                        barChart1.getPlot().setBackgroundPaint(new Color(166, 233, 255));
                        CategoryPlot barchrt1 = barChart1.getCategoryPlot();
                        //set  bar chart color (nguồn: stackoverflow.com/questions/24555380/jfreechart-bar-chart-custom-color)
                        ((BarRenderer) barchrt1.getRenderer()).setBarPainter(new StandardBarPainter());
                        BarRenderer r = (BarRenderer) barChart1.getCategoryPlot().getRenderer();
                        r.setSeriesPaint(0, Color.blue);
                        //Đặt đường đứt khúc màu đen
                        barchrt1.setRangeGridlinePaint(Color.black);
                        //Tạo panel biểu đồ (hiển thị cái mới)
                        barPanel = new ChartPanel(barChart1);
                        //Đặt vị trí và kích thước
                        barPanel.setBounds(0, 280, 900, 400);
                        //Gán p là barPanel để có gì remove cái cũ hiển thị cái mới
                        p.add(barPanel);
                    } else {//Nếu không có doanh thu theo tháng
                        //Gán doanh thu các ngày là 0
                        for (int j = 0; j <= lastDay; j++) {
                            //Gán giá trị vào biểu đồ
                            barChartData.setValue(0, "Ngày", "" + j);
                        }
                        //Tạo tiêu đề biểu đồ
                        JFreeChart barChart1 = ChartFactory.createBarChart("Thống kê doanh thu tháng " + date.getMonthValue(), "Ngày", "Doanh thu", barChartData, PlotOrientation.VERTICAL, false, true, false);
                        //Đặt background màu trắng 
                        barChart1.getPlot().setBackgroundPaint(new Color(166, 233, 255));
                        CategoryPlot barchrt1 = barChart1.getCategoryPlot();

                        //set  bar chart color (nguồn: stackoverflow.com/questions/24555380/jfreechart-bar-chart-custom-color)
                        ((BarRenderer) barchrt1.getRenderer()).setBarPainter(new StandardBarPainter());
                        BarRenderer r = (BarRenderer) barChart1.getCategoryPlot().getRenderer();
                        r.setSeriesPaint(0, Color.blue);
                        //Đặt đường đứt khúc màu đen
                        barchrt1.setRangeGridlinePaint(Color.black);
                        //Tạo panel biểu đồ (hiển thị cái mới)
                        barPanel = new ChartPanel(barChart1);
                        //Đặt vị trí và kích thước
                        barPanel.setBounds(0, 280, 900, 400);
                        //Gán p là barPanel để có gì remove cái cũ hiển thị cái mới
                        p.add(barPanel);
                    }
                }
            }
        });
        this.add(jdc);

        this.setVisible(false);
    }

}
