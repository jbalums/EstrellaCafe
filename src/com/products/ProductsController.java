/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.products;

import assets.SearchBox;
import com.DBCon.DBClass;
import com.mysql.jdbc.StringUtils;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Mahal
 */
public class ProductsController implements Initializable {
    public ArrayList<Product> p = new ArrayList<Product>();
     public String S_ID="";
    @FXML
    private TextField pname, qty, prc, rsp, pname1, qty1, prc1, rsp1,pname2, pname_s, qty_l, stock_amt,rsp_s2;
    @FXML
    private Label s_notif,s_notif1,d_pname,stock_notify;
    @FXML
    private TableColumn c1,c2,c3,c4,c5,c12,c22,c32,c42;
    @FXML
    private TableView p_table,p_table2,p_table3,p_table4;
    @FXML
    private Button clear_btn, clear_btn1, clear_btn2;
    @FXML
    private Pane del_notif;
    
    @FXML
    private VBox vbx,vbx1,vbx2,vbx3;
    
    public DBClass db = new DBClass(); 
    
    private SearchBox sb = new SearchBox(),sb1 = new SearchBox(),sb2 = new SearchBox(),sb3 = new SearchBox();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        vbx.getStylesheets().add("/assets/SearchBox.css");
        vbx.getChildren().setAll(sb); 
        vbx1.getStylesheets().add("/assets/SearchBox.css");
        vbx1.getChildren().setAll(sb1); 
        vbx2.getStylesheets().add("/assets/SearchBox.css");
        vbx2.getChildren().setAll(sb2); 
        vbx3.getStylesheets().add("/assets/SearchBox.css");
        vbx3.getChildren().setAll(sb3); 
        sbl();
        tbl();
        try {
            loadData();
            p_table.setEditable(true);
            
            TableColumn idCol = new TableColumn("ID");
            TableColumn pncol = new TableColumn("Product Name");
            TableColumn qtyCol = new TableColumn("Quantity");
            TableColumn prcCol = new TableColumn("Price");
            TableColumn rspCol = new TableColumn("Re-Stock-POint");
            
            
            idCol.setMinWidth(20);            
            idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            c1.setCellValueFactory(new PropertyValueFactory<>("ID"));
            c12.setCellValueFactory(new PropertyValueFactory<>("ID"));
            pncol.setMinWidth(125);
            pncol.setCellValueFactory(new PropertyValueFactory<>("name"));
            c2.setCellValueFactory(new PropertyValueFactory<>("name"));
            c22.setCellValueFactory(new PropertyValueFactory<>("name"));
            
            qtyCol.setMinWidth(50);
            qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
            c3.setCellValueFactory(new PropertyValueFactory<>("qty"));
            c32.setCellValueFactory(new PropertyValueFactory<>("qty"));
            
            prcCol.setMinWidth(50);
            prcCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            c4.setCellValueFactory(new PropertyValueFactory<>("price"));
            
            rspCol.setMinWidth(55);
            rspCol.setCellValueFactory(new PropertyValueFactory<>("re_stock_point"));
            c5.setCellValueFactory(new PropertyValueFactory<>("re_stock_point"));
            c42.setCellValueFactory(new PropertyValueFactory<>("re_stock_point"));
           
            
          //  dateEnCol.setCellValueFactory(new PropertyValueFactory<Student, String>("dateEn"));
            
            //studentTable.setItems(data);
            p_table.getColumns().addAll(idCol,pncol, qtyCol, prcCol, rspCol);
           // p_table2.getColumns().addAll(idCol,pncol, qtyCol, prcCol, rspCol);
            idCol.setSortable(false);
            pncol.setSortable(false);
            p_table3.getColumns().addAll(idCol,pncol);
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    public boolean valid(TextField tf){
        return !tf.getText().isEmpty();
    }
    @FXML
    public void validate_pn(){
        if(pname.getText().isEmpty()){
            pname.setId("input_red");
        }else{
            pname.setId("input_field");
        }
    }
    @FXML
    public void validate_prc(){
        if(prc.getText().toString().isEmpty()){
            prc.setId("input_red");
        }else{
            if(Integer.parseInt(prc.getText()) > 0){
                prc.setId("input_red");
            }else{
                prc.setId("input_field");
            }
        }        
    }
    public static boolean isNum(TextField tf){
        if(tf.getText().toString().isEmpty()){
            tf.setId("input_red");
            return false;
        }else{
            if(tf.getText().matches("[0-9]")){                
                if(Integer.parseInt(tf.getText()) > 0){
                    tf.setId("input_red");
                    return false;
                }else{
                    tf.setId("input_field");
                    return true;
                }
            }else{
                tf.setId("input_red");
                return false;
            }
        }
    }
    @FXML
    public void validate_qty(){
        if(qty.getText().isEmpty() ){            
            qty.setId("input_red");
        }else{
            if(Integer.parseInt(qty.getText()) > 0){
                qty.setId("input_red");    
            }else{
                qty.setId("input_field");
            }
        }
    }
    @FXML
    public void addProducts() {
        if(valid(pname) && valid(prc) && valid(qty)){
            try{
                addProduct();
            }catch(Exception e){
                
            }
            
        }else{
            s_notif.setId("hide");
            validate_pn();
            validate_prc();
            validate_qty();
        }
    }
    @FXML
    public void tabChange() throws Exception{
//        loadDataS("");
        //System.out.println("ASDASD");
    }
    public void clearFields(){
        pname.clear();
        qty.clear();
        prc.clear();
        rsp.clear();
        pname1.clear();
        qty1.clear();
        prc1.clear();
        rsp1.clear();
        pname2.clear();
        stock_amt.clear();
        
       /* for(int i = 0 ; i < 500000 ; i++){            
            if(i == 499999)
                s_notif.setId("hide");
        }*/
    }
    public void addProduct(){
       s_notif.setId("hide");
       try{
            com.mysql.jdbc.Connection conn = db.getConnection();
            Statement stm = conn.createStatement();
            int resU = stm.executeUpdate("INSERT INTO products(name, qty, price, re_stock_point) VALUES ('"+pname.getText()+"', '"+qty.getText()+"', '"+prc.getText()+"', '"+rsp.getText()+"');");
            if(resU == 1){
                s_notif.setId("show");
                clearFields();
                loadData();
            }
            
       }catch(Exception e){
           
       }
    }
    public void loadData(String ID)throws Exception{
        
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * from products where ID = "+ID);
        while (rs.next()) {
           /* p.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));*/
            if(pname1.isVisible()){
                pname1.setText(rs.getString("name"));
                qty1.setText(rs.getString("qty"));
                prc1.setText(rs.getString("price"));
                rsp1.setText(rs.getString("re_stock_point"));
            }
            if(pname2.isVisible()){
                pname2.setText(rs.getString("name"));
                d_pname.setText(rs.getString("name"));
            }
            if(pname_s.isVisible()){
                pname_s.setText(rs.getString("name"));
                qty_l.setText(rs.getString("qty"));
                rsp_s2.setText(rs.getString("re_stock_point"));
                
                if(Integer.parseInt(qty_l.getText()) <= (Integer.parseInt(rsp_s2.getText())+1) ){
                    qty_l.setId("input_red");
                }else{
                    qty_l.setId("input_field");
                }
            }
        }
    }
    public void save()throws Exception{
      //  UPDATE `group4_db_estrella`.`products` SET `name` = 'Renzo1' WHERE `products`.`ID` = 11;
        if(!pname1.getText().isEmpty() && !qty1.getText().isEmpty() && !prc1.getText().isEmpty() && !rsp1.getText().isEmpty() ){
            s_notif1.setId("hide");
            ArrayList<Product> ar = new ArrayList<>();
            com.mysql.jdbc.Connection conn = db.getConnection();
            Statement stm = conn.createStatement();
            int rs = stm.executeUpdate("UPDATE products SET "
                    + "name='"+pname1.getText()+"', "
                    + "qty='"+qty1.getText()+"', "
                    + "price='"+prc1.getText()+"',"
                    + "re_stock_point='"+rsp1.getText()+"' WHERE ID ='"+S_ID+"';");
            if(rs > 0){
                s_notif1.setId("show");
            }
        }
        loadData();
    }
    public void addStock()throws Exception{
        if(!stock_amt.getText().isEmpty() && Integer.parseInt(stock_amt.getText()) > 0){
           
            stock_amt.setId("input_field");
            String amt = (Integer.parseInt(stock_amt.getText()) + Integer.parseInt(qty_l.getText())) +"";
            stock_notify.setId("hide");
            ArrayList<Product> ar = new ArrayList<>();
            com.mysql.jdbc.Connection conn = db.getConnection();
            Statement stm = conn.createStatement();
            int rs = stm.executeUpdate("UPDATE products SET "
                    + "qty='"+amt+"' "
                    +"WHERE ID ='"+S_ID+"';");
            if(rs > 0){
                stock_amt.clear();
                loadData(S_ID);
                stock_notify.setId("show");
            }
            loadData();
        }else{
            stock_amt.setId("input_red");
        }
    }
    public void delProduct()throws Exception{
       del_notif.setId("show");
    }
    public void delNo()throws Exception{
       del_notif.setId("hide");
       pname2.clear();
    }
    public void delYes()throws Exception{
        try{
            com.mysql.jdbc.Connection conn = db.getConnection();
            Statement stm = conn.createStatement();
            int resU = stm.executeUpdate("DELETE FROM products WHERE ID ='"+S_ID+"';");
            if(resU == 1){
                clearFields();
                del_notif.setId("hide");
                loadData();
            }
            
       }catch(Exception e){
           
       }
    }
    public void loadData() throws Exception{
        p.clear();
        ObservableList<Product> data2 = null;   
        p_table.setItems(data2);
        ArrayList<Product> ar = new ArrayList<>();
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * from products");
        while (rs.next()) {
            p.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
            ar.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
        }
        
        data2 = FXCollections.observableArrayList(ar);  
        p_table.setItems(data2);
        p_table2.setItems(data2);
        p_table3.setItems(data2);
        p_table4.setItems(data2);
    }
    public void loadDataS(String s) throws Exception{
        p.clear();
        ObservableList<Product> data2 = null, data3 = null, data4 = null, data5 = null;   
        p_table.setItems(data2);
        ArrayList<Product> ar= new ArrayList<>(),ar2= new ArrayList<>(),ar3= new ArrayList<>(),ar4= new ArrayList<>(),ar5 = new ArrayList<>();
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT ID, name, qty, FORMAT(price,2) as price, re_stock_point from products where  name LIKE '%"+s+"%'");
        while (rs.next()) {
            p.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
            ar.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
            ar2.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
            ar3.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
            ar4.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
        }
        try{
            data2 = FXCollections.observableArrayList(ar); 
            data3 = FXCollections.observableArrayList(ar2); 
            data4 = FXCollections.observableArrayList(ar3);
            data5 = FXCollections.observableArrayList(ar4);   
            p_table.setItems(data2);
            p_table2.setItems(data3);
            p_table3.setItems(data4);
            p_table4.setItems(data5);
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
    
    
    public void sbl(){
        sb.getChildrenUnmodifiable().get(0).setOnKeyReleased((KeyEvent event) -> {
            try {
                loadDataS(sb.getStxt());
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb1.getChildrenUnmodifiable().get(0).setOnKeyReleased((KeyEvent event) -> {
            try {
                loadDataS(sb1.getStxt());
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb2.getChildrenUnmodifiable().get(0).setOnKeyReleased((KeyEvent event) -> {
            try {
                loadDataS(sb2.getStxt());
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb3.getChildrenUnmodifiable().get(0).setOnKeyReleased((KeyEvent event) -> {
            try {
                loadDataS(sb3.getStxt());
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        sb.getChildrenUnmodifiable().get(0).setOnMouseExited((MouseEvent event) -> {
            try {
                loadDataS("");
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb1.getChildrenUnmodifiable().get(0).setOnMouseExited((MouseEvent event) -> {
            try {
                loadDataS("");
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb2.getChildrenUnmodifiable().get(0).setOnMouseExited((MouseEvent event) -> {
            try {
                loadDataS("");
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sb3.getChildrenUnmodifiable().get(0).setOnMouseExited((MouseEvent event) -> {
            try {
                loadDataS("");
            } catch (Exception ex) {
                Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
    }
    
    public void tbl(){
         p_table2.setOnMouseClicked((MouseEvent event) -> {
             try {
                 s_notif1.setId("hide");
                 S_ID  = p.get(p_table2.getSelectionModel().getFocusedIndex()).getID();
                 loadData(S_ID);
                 
             } catch (Exception ex) {
             }
         });
         
             p_table3.setOnMouseClicked((MouseEvent event) -> {
                 try {
                     del_notif.setId("hide");
                     S_ID  = p.get(p_table3.getSelectionModel().getFocusedIndex()).getID();
                     loadData(S_ID);
                 } catch (Exception ex) {
                 }
            });
             p_table4.setOnMouseClicked((MouseEvent event) -> {
                 try {
                     stock_notify.setId("hide");
                     S_ID  = p.get(p_table4.getSelectionModel().getFocusedIndex()).getID();
                     loadData(S_ID);
                 } catch (Exception ex) {
                 }
            });
    }
}
