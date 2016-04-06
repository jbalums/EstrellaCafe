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
public class OrderReport {
    
    private String order_no, name, qty, price, re_stock_point, date_ordered;

    public OrderReport(String order_no, String name, String qty, String price, String re_stock_point, String date_ordered) {
        this.order_no = order_no;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.re_stock_point = re_stock_point;
        this.date_ordered = date_ordered;
    }

    public OrderReport(String order_no, String name, String qty, String price, String date_ordered) {
        this.order_no = order_no;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.date_ordered = date_ordered;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
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

    public String getDate_ordered() {
        return date_ordered;
    }

    public void setDate_ordered(String date_ordered) {
        this.date_ordered = date_ordered;
    }
    
    
}
