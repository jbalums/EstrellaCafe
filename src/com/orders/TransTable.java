/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orders;

/**
 *
 * @author Mahal
 */
public class TransTable {
    public String pname, price, qty, payable;

    public TransTable(String pname, String price, String qty, String payable) {
        this.pname = pname;
        this.price = price;
        this.qty = qty;
        this.payable = payable;
    }

    
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }
    
    
}
