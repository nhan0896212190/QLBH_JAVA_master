/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.time.LocalDate;

/**
 *
 * @author Duy
 */
public class Employee extends Customer {

    private String username, password, gender;
    private LocalDate dayOfBirth;

    public Employee(String username, String password, String gender, LocalDate dayOfBirth, int id, String name, String address, String phone) {
        super(id, name, address, phone);
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dayOfBirth = dayOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

}
