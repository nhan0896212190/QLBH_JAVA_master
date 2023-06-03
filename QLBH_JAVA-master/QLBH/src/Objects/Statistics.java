/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.time.LocalDate;

/**
 *
 * @author ACER
 */
public class Statistics {

    private LocalDate date;
    private int revenue, count;

    public Statistics(LocalDate date, int count, int revenue) {
        this.date = date;
        this.revenue = revenue;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getRevenue() {
        return revenue;
    }

}
