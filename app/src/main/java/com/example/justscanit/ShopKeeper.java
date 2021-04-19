package com.example.justscanit;

public class ShopKeeper {
    String ShopkeeperName;
    String ShopName;
    String LicenseNumber;
    String ShopAddress;
    String email;
    String password;
    String MobileNo;

    public ShopKeeper(){}

    public ShopKeeper(String shopkeeperName, String shopName, String licenseNumber, String shopAddress, String email, String password, String mobileNo) {
        ShopkeeperName = shopkeeperName;
        ShopName = shopName;
        LicenseNumber = licenseNumber;
        ShopAddress = shopAddress;
        this.email = email;
        this.password = password;
        MobileNo = mobileNo;
    }

    public String getShopkeeperName() {
        return ShopkeeperName;
    }

    public String getShopName() {
        return ShopName;
    }

    public String getLicenseNumber() {
        return LicenseNumber;
    }

    public String getShopAddress() {
        return ShopAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNo() {
        return MobileNo;
    }
}
