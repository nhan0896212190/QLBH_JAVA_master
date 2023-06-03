/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Duy
 */
public class OrderItem  {

    private Product product;
    private int count;

    public OrderItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Product getProduct() {
        return product;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
