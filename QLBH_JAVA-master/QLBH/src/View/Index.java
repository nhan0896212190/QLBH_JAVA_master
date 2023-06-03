/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Duy
 */
public class Index extends JFrame {

    private static Home h = new Home();// Tạo biến h là panel Home

    public static void main(String[] args) {
        Index index = new Index();//Tạo biến index là frame Index
        index.setIcon(index);//Gọi hàm đổi biểu tượng
        index.setLayout(null);//set layout là null để đặt component tự do
        index.setDefaultCloseOperation(EXIT_ON_CLOSE);//đặt mặt định khi thoát là kết thúc chương trình
        index.setTitle("Phần mềm quản lí bán hàng");//Đặt tiêu đề
        index.setSize(913, 750);//Đặt kích thước frame
        index.setLocationRelativeTo(null);//Hiển thi chính giữa màn hình
        index.setResizable(false);//Không cho thay đổi kích thước frame
        Menu menu = new Menu(index);//Tạo biến menu là 1 MenuBar có tham số là frane Index
        index.setJMenuBar(menu);//Thêm MenuBar menu trong frame Index
        index.add(h);//Thêm panel Home trong frame Index
        toggleHome(true);//Hiển thị panel Home 
        index.setVisible(true);//Hiển thị frame Index
    }

    // Tắt/mở panel Home
    public static void toggleHome(boolean type) {
        h.setVisible(type);
    }

    // Đổi biểu tưởng frame
    public void setIcon(JFrame index) {
        index.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/laptop.png")));
    }
}
