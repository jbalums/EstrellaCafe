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
public class Trans {
    private String ID, product_id, qty_ordered, order_ID, price, qty_left;

    public Trans(String product_id, String qty_ordered, String price, String qty_left) {
        this.product_id = product_id;
        this.qty_ordered = qty_ordered;
        this.order_ID = order_ID;
        this.price = price;
        this.qty_left = qty_left;
    }

    
    public String getQty_left() {
        return qty_left;
    }

    public void setQty_left(String qty_left) {
        this.qty_left = qty_left;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }
        
    
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQty_ordered() {
        return qty_ordered;
    }

    public void setQty_ordered(String qty_ordered) {
        this.qty_ordered = qty_ordered;
    }
    
    
}
