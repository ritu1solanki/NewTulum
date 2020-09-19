package com.example.tulupcoffee;

public class blog {

    public blog() {
    }

    public String getProductqty() {
        return Productqty;
    }

    public void setProductqty(String productqty) {
        Productqty = productqty;
    }

    public String getDiscountavailavle() {
        return discountavailavle;
    }

    public void setDiscountavailavle(String discountavailavle) {
        this.discountavailavle = discountavailavle;
    }

    public String getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(String discountprice) {
        this.discountprice = discountprice;
    }

    public String getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(String originalprice) {
        this.originalprice = originalprice;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProducticon() {
        return producticon;
    }

    public void setProducticon(String producticon) {
        this.producticon = producticon;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private  String Productqty;
    private String discountavailavle;

   private String discountprice;

    private String originalprice;

    private String  productcategory;

    private String  productdescription;

    private String producticon;

    private String productid;

    private String producttitle;

    private String timestamp;

    private String  uid;

    public blog(String productqty, String discountavailavle, String discountprice, String originalprice, String productcategory, String productdescription, String producticon, String productid, String producttitle, String timestamp, String uid) {
        Productqty = productqty;
        this.discountavailavle = discountavailavle;
        this.discountprice = discountprice;
        this.originalprice = originalprice;
        this.productcategory = productcategory;
        this.productdescription = productdescription;
        this.producticon = producticon;
        this.productid = productid;
        this.producttitle = producttitle;
        this.timestamp = timestamp;
        this.uid = uid;
    }
}
