package com.example.justscanit;

public class Products {
    String productname;
    String productcategory;
    String productprice;
    String productbarcode;

    public Products()
    {

    }

    public Products(String productname, String productcategory, String productprice, String productbarcode) {
        this.productname = productname;
        this.productcategory = productcategory;
        this.productprice = productprice;
        this.productbarcode = productbarcode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductbarcode() {
        return productbarcode;
    }

    public void setProductbarcode(String productbarcode) {
        this.productbarcode = productbarcode;
    }
}

