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
public class Order {
    public String ID, date_ordered, total_qty_ordered, trans_ID, amount_paid, change;

    public Order(String ID, String date_ordered, String total_qty_ordered, String trans_ID, String amount_paid, String change) {
        this.ID = ID;
        this.date_ordered = date_ordered;
        this.total_qty_ordered = total_qty_ordered;
        this.trans_ID = trans_ID;
        this.amount_paid = amount_paid;
        this.change = change;
    }

    public Order(String date_ordered, String total_qty_ordered, String trans_ID, String amount_paid, String change) {
        this.date_ordered = date_ordered;
        this.total_qty_ordered = total_qty_ordered;
        this.trans_ID = trans_ID;
        this.amount_paid = amount_paid;
        this.change = change;
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

    public String getTotal_qty_ordered() {
        return total_qty_ordered;
    }

    public void setTotal_qty_ordered(String total_qty_ordered) {
        this.total_qty_ordered = total_qty_ordered;
    }

    public String getTrans_ID() {
        return trans_ID;
    }

    public void setTrans_ID(String trans_ID) {
        this.trans_ID = trans_ID;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
    
    
}
