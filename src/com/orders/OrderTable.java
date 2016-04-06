/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orders;

import com.DBCon.DBClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mahal
 */
public class OrderTable {
    private String ID, qty_ordered, date_ordered, amount_paid, change, cashier;
   
    public DBClass db = new DBClass(); 
    public OrderTable(String ID, String qty_ordered, String date_ordered, String cashier) throws ClassNotFoundException, SQLException {
        this.ID = ID;
        this.qty_ordered = qty_ordered;
        this.date_ordered = date_ordered;
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT username from user where ID = "+cashier);
        while (rs.next()) {
            this.cashier = rs.getString("username");
        }
        
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }
    
    public OrderTable(String amount_paid, String change) {
        this.amount_paid = amount_paid;
        this.change = change;
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

        
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQty_ordered() {
        return qty_ordered;
    }

    public void setQty_ordered(String qty_ordered) {
        this.qty_ordered = qty_ordered;
    }

    public String getDate_ordered() {
        return date_ordered;
    }

    public void setDate_ordered(String date_ordered) {
        this.date_ordered = date_ordered;
    }
    
    
}
