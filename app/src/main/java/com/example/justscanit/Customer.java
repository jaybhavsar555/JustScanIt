package com.example.justscanit;

public class Customer {
    String name;
    String Email;
    String MobileNo;
    String Password;

    public Customer(String name, String email, String mobileNo, String password) {
        this.name = name;
        Email = email;
        MobileNo = mobileNo;
        Password = password;
    }
    public String getPassword() {
        return Password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
