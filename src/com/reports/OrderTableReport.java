/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reports;

import com.DBCon.DBClass;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mahal
 */
public class OrderTableReport {
    public DBClass db = new DBClass(); 
    public String ID, date_ordered, product_order, qty_ordered, payable;

    public OrderTableReport(String ID, String date_ordered, String product_order, String qty_ordered, String payable) {
        this.ID = ID;
        this.date_ordered = date_ordered;
        this.product_order = product_order;
        this.qty_ordered = qty_ordered;
        this.payable = payable;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate_ordered() {
        return date_ordered;
    }

    public void setDate_ordered(String date_ordered) {
        this.date_ordered = date_ordered;
    }

    public String getProduct_order() {
        return product_order;
    }

    public void setProduct_order(String product_order) {
        this.product_order = product_order;
    }

    public String getQty_ordered() {
        return qty_ordered;
    }

    public void setQty_ordered(String qty_ordered) {
        this.qty_ordered = qty_ordered;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }
}
