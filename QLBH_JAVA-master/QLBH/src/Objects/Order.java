/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Duy
 */
public class Order {

    private int id;
    private Customer customer;
    private List<OrderItem> listDetail;
    private LocalDate date;
    private Employee employee;

    public Order(int id, Customer customer, List<OrderItem> listDetail, LocalDate date, Employee employee) {
        this.id = id;
        this.customer = customer;
        this.listDetail = listDetail;
        this.date = date;
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<OrderItem> listDetail) {
        this.listDetail = listDetail;
    }

    public int getPrice() {
        int result = 0;
        for (OrderItem item : listDetail) {
            result += (item.getCount() * item.getProduct().getPrice());
        }
        return result;
    }

    public int getTotalProduct() {
        int result = 0;
        for (OrderItem item : listDetail) {
            result += item.getCount();
        }
        return result;
    }
}
