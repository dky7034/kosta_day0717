package com.kosta.vo;

public class GoodsVO {
    private int no;
    private String item;
    private int price;
    private int qty;
    private String fname;

    // 생성자
    public GoodsVO() {}
    public GoodsVO(int no, String item, int price, int qty, String fname) {
        this.no = no;
        this.item = item;
        this.price = price;
        this.qty = qty;
        this.fname = fname;
    }

    // getter and setter
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
