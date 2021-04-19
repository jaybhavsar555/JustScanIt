package com.example.justscanit;

public class Wholesaler {
    String WholesalerName;
    String CompanyName;
    String DistributionID;
    String emailID;
    String password;
    String MobileNo;

    public Wholesaler(String wholesalerName, String companyName, String distributionID, String emailID, String password, String mobileNo) {
       this.WholesalerName = wholesalerName;
        CompanyName = companyName;
        DistributionID = distributionID;
        this.emailID = emailID;
        this.password = password;
        MobileNo = mobileNo;
    }
    public String getWholesalerName() {
        return WholesalerName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getDistributionID() {
        return DistributionID;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNo() {
        return MobileNo;
    }
}
