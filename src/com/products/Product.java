/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.products;

/**
 *
 * @author Mahal
 */
public class Product {
    private String ID, name, qty, price, re_stock_point;
    private Double payable;
  
    public Product(String ID, String name, String qty, String price, String re_stock_point) {
        this.ID = ID;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.re_stock_point = re_stock_point;
    }
    public Product(String ID, String name, String qty, String price) {
        this.ID = ID;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }
    public Product(String ID, String name, String qty, String price, Double payable) {
        this.ID = ID;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.payable = payable;
    }

    public Double getPayable() {
        return payable;
    }

    public void setPayable(Double payable) {
        this.payable = payable;
    }

   

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRe_stock_point() {
        return re_stock_point;
    }

    public void setRe_stock_point(String re_stock_point) {
        this.re_stock_point = re_stock_point;
    }

    
    
    
}
