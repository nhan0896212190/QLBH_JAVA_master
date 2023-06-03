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
public class Product {

    private String name, image, group, supplier;
    private int id, price, inventory;

    public Product(int id, String name, String image, String group, String supplier, int price, int inventory) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.group = group;
        this.supplier = supplier;
        this.price = price;
        this.inventory = inventory;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public int getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

}
