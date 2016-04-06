/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orders;

import assets.SearchBox;
import com.DBCon.DBClass;
import com.itextpdf.text.DocumentException;
import com.products.Product;
import com.products.ProductsController;
import com.reports.OrderReports;
import com.reports.OrderTableReport;
import com.reports.Reciept;
import com.reports.SalesReport;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import javax.xml.bind.Marshaller.Listener;

/**
 * FXML Controller class
 *
 * @author Mahal
 */
public class OrdersController implements Initializable {
    @FXML
    Label s_notif, total_payable, change, total_payable1, money1, change1, ts2,tq2;
    @FXML
    TextField pname,qty, money;
    @FXML
    TableView p_table,odTable,odTable1, orderTable, orderReportTable;
    @FXML
    TableColumn c_pname,c_price,c_qty, clv,clv2, clv3, clv4, clv1,clv21, clv31, clv41, or1,or2,or3,or31, c1,c2,c3,c4,c5;
    @FXML
    Pane notify_add_order,gsr;
    @FXML
    DatePicker from, to;
    @FXML 
    Button gobtn;
    
    NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.US);
    
    
    private String IDNO;
    public DBClass db = new DBClass(); 
    public ArrayList<Trans> order_d = new ArrayList<Trans>();
    public ArrayList<Product> p = new ArrayList<Product>();
    public ArrayList<TransTable> ar2= new ArrayList<>();
    public ArrayList<OrderTable> orders = new ArrayList<OrderTable>(),orders2 = new ArrayList<OrderTable>();
    public ArrayList<OrderReport> OdR = new ArrayList<OrderReport>();
    public ArrayList<OrderTableReport> otr = new ArrayList<OrderTableReport>();
    SalesReport sr = new SalesReport();
    OrderReports odr = null;
    Reciept reciept ;//= new Reciept();
    public String S_ID="", S_ID2="";
    public float tp =0f;
    public int tp2 = 0,tqty = 0;
    public String username=""; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setOrderTable();
            setItemsTable();
            getLatestIDNO();
            setUpOrderReportTable();
        } catch (Exception ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        p_table.setOnMouseClicked((MouseEvent event) -> {
            
            try {
                //System.out.println(p_table.getSelectionModel().getFocusedIndex()+"");
                notify_add_order.setId("hide");
                //s_notif.setId("hide");
                S_ID  = p.get(p_table.getSelectionModel().getFocusedIndex()).getID();
                System.out.println(S_ID);
                loadData(S_ID);
                
            } catch (Exception ex) {
            }
        });
        money.focusedProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Float d = 0f;
                   
                                
                if(money.getText().isEmpty()){
                    money.setId("input_red");
                    //notify_add_order.setId("hide");
                }else{
                    d = Float.parseFloat(money.getText()) - tp;
                    if(d >= 0){
                                change.setText(d.toString());
                                money.setId("field");
                    }else{
                        money.setId("input_red");
                    }
                }
            }
        });
    }    
    @FXML    
    public void addItemsToOrder() throws ClassNotFoundException, SQLException{
        if(qty.getText().isEmpty()){
            qty.setId("input_red");
        }else{
            if(Double.parseDouble(qty.getText().toString()) > 0 && !pname.getText().isEmpty()){
                pname.setId("field");
                qty.setId("field");
                ArrayList<String> s3 = new ArrayList<String>();
                s3 = loadItemName(S_ID);
                order_d.add(new Trans(S_ID,qty.getText(), s3.get(1), s3.get(2)));
                //tp =0;
               // tqty =0;
                Float d = Float.parseFloat(order_d.get(order_d.size()-1).getQty_ordered())*Float.parseFloat(order_d.get(order_d.size()-1).getPrice());
                
      // for(int i = 0 ; i < order_d.size() ; i++){
                    tp += Double.parseDouble(d.toString());
                    tqty += Integer.parseInt(order_d.get(order_d.size()-1).getQty_ordered());
                    
                //}
                ar2.add(new TransTable(s3.get(0),numberFormatter.format(Float.parseFloat(order_d.get(order_d.size()-1).getPrice())),order_d.get(order_d.size()-1).getQty_ordered(),numberFormatter.format(d)));
                total_payable.setText(tp+"");
                
            ObservableList<TransTable> data2 = null;   
            odTable.setItems(data2);
            data2 = FXCollections.observableArrayList(ar2);   
            odTable.setItems(data2);
            clv.setSortable(false);
            clv.setCellValueFactory(new PropertyValueFactory<>("pname"));
            clv2.setSortable(false);
            clv2.setCellValueFactory(new PropertyValueFactory<>("price"));
            clv3.setSortable(false);
            clv3.setCellValueFactory(new PropertyValueFactory<>("qty"));
            clv4.setSortable(false);
            clv4.setCellValueFactory(new PropertyValueFactory<>("payable"));
            }else{
                qty.setId("input_red");
                pname.setId("input_red");
            }
        }
        qty.clear();
        pname.clear();
    }
    @FXML 
    public void submitOrder()throws Exception{
        addTransTable();
    }
    @FXML
    public void change()throws Exception{
        loadProductData();
    }
    @FXML 
    public void Okay()throws Exception{
        notify_add_order.setId("hide");
        change.setText("0.00");
    }
    public void addTransTable()throws Exception{
        if(!money.getText().isEmpty()){
            money.setId("field");
           if(Integer.parseInt(money.getText()) >= tp){
               System.out.print("good");
                String lastid ="";
                com.mysql.jdbc.Connection conn = db.getConnection();
                Statement stm = conn.createStatement();
                int resU = stm.executeUpdate("INSERT INTO orders(amount_paid, orders.change, status, userID) VALUES ('"+money.getText()+"','"+change.getText()+"','1','"+IDNO+"')");
                if(resU > 0){
                    ResultSet rs2 = stm.executeQuery("select ID as last_id from orders order by ID desc limit 1");                 
                    while (rs2.next()) {
                        lastid = rs2.getString("last_id");
                    }
                   // sr.sales(order_d);
                    
                    for(int i = 0 ; i < order_d.size() ; i++){
                        int resI = stm.executeUpdate("INSERT INTO trans(product_id, qty_ordered, order_ID) VALUES ('"+order_d.get(i).getProduct_id()+"','"+order_d.get(i).getQty_ordered()+"','"+lastid+"')");   
                        Double q = Double.parseDouble(order_d.get(i).getQty_left()) - Double.parseDouble(order_d.get(i).getQty_ordered());
                        int resI2 = stm.executeUpdate("UPDATE products SET qty = '"+q.toString()+"' WHERE ID ="+order_d.get(i).getProduct_id());
                    }
                    reciept = new Reciept(order_d, lastid);
                    //reciept.ge
                    loadProductData();
                }
                
                notify_add_order.setId("show");
                order_d.clear();
                tp = 0;
                ArrayList<TransTable> ar= new ArrayList<>();
                ObservableList<TransTable> data2 = null;   
                odTable.setItems(data2);
                data2 = FXCollections.observableArrayList(ar);   
                odTable.setItems(data2);
                ar2.clear();
                money.clear();
                total_payable.setText("0.00");
                loadOrderData();
               
            }else{
                money.setId("input_red");
           }
           
        }else{
            money.setId("input_red");
        }
        
       
        

    }
    
    public void setItemsTable()throws Exception{
        loadProductData();
        try {
            c_pname.setSortable(false);
            c_pname.setCellValueFactory(new PropertyValueFactory<>("name"));
            c_price.setSortable(false);
            c_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            c_qty.setSortable(false);
            c_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void loadData(String ID)throws Exception{
        
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * from products where ID = "+ID);
        while (rs.next()) {
            if(pname.isVisible()){
                pname.setText(rs.getString("name"));
                //qty.setText(rs.getString("qty"));
            }
            
        }
    }
    public void loadProductData() throws Exception{
        p.clear();
        ObservableList<Product> data2 = null;   
        p_table.setItems(data2);
        ArrayList<Product> ar= new ArrayList<>();
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT ID, name, price, qty, re_stock_point from products");
        while (rs.next()) {
            p.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"), 
                               rs.getString("re_stock_point")));
            ar.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), numberFormatter.format(Float.parseFloat(rs.getString("price"))),//String.format("%10.2f", Float.parseFloat(rs.getString("price"))), 
                               rs.getString("re_stock_point")));
        }
        try{
            data2 = FXCollections.observableArrayList(ar);   
            p_table.setItems(data2);
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    public void setOrderTable()throws Exception{
        loadOrderData();
        try {
            or1.setSortable(false);
            or1.setCellValueFactory(new PropertyValueFactory<>("ID"));
            or2.setSortable(false);
            or2.setCellValueFactory(new PropertyValueFactory<>("qty_ordered"));
            or3.setSortable(false);
            or3.setCellValueFactory(new PropertyValueFactory<>("date_ordered"));
            or31.setSortable(false);
            or31.setCellValueFactory(new PropertyValueFactory<>("cashier"));
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
         orderTable.setOnMouseClicked((MouseEvent event) -> {
             try {
               // notify_add_order.setId("hide");
                 ///s_notif.setId("hide");
                 S_ID2  = orders.get(orderTable.getSelectionModel().getFocusedIndex()).getID();
                 //System.out.println(S_ID);
                loadOrderData(S_ID2);
                 
                ArrayList<String> s = new  ArrayList<String>();
                s =  loadOrder(S_ID2);
                money1.setText(s.get(0));
                change1.setText(s.get(1));
                clv1.setSortable(false);
                clv1.setCellValueFactory(new PropertyValueFactory<>("name"));
                clv21.setSortable(false);
                clv21.setCellValueFactory(new PropertyValueFactory<>("price"));
                clv31.setSortable(false);
                clv31.setCellValueFactory(new PropertyValueFactory<>("qty"));
                clv41.setSortable(false);
                clv41.setCellValueFactory(new PropertyValueFactory<>("payable"));
             } catch (Exception ex) {
             }
         });
    }
    public void loadOrderData() throws ClassNotFoundException, SQLException{
        orders.clear();
        ObservableList<OrderTable> data2 = null;   
        orderTable.setItems(data2);
        ArrayList<OrderTable> ar= new ArrayList<>();
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT a.ID as ID, sum(b.qty_ordered) as qty, DATE_FORMAT(a.date_ordered,'%M %d, %Y') as date_ordered, userID from orders a join trans b on b.order_ID = a.ID group by b.order_ID");
        while (rs.next()) {
            orders.add(new OrderTable(rs.getString("ID"), rs.getString("qty"), rs.getString("date_ordered"), rs.getString("userID")));
            ar.add(new OrderTable(rs.getString("ID"), rs.getString("qty"), rs.getString("date_ordered"), rs.getString("userID")));
           // orders2.add(new OrderTable(rs.getString("amount_paid"),rs.getString("achange")));
        }
        try{
            data2 = FXCollections.observableArrayList(ar);   
            orderTable.setItems(data2);
        }catch(Exception e){
            e.printStackTrace();
        }
           
    }
   public ArrayList<String> loadOrder(String ID) throws ClassNotFoundException, SQLException{
       com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * from orders where ID = "+ID);
        ArrayList<String> s = new ArrayList<>();
        while (rs.next()) {
            s.add(rs.getString("amount_paid"));
            s.add(rs.getString("change"));     
        }
        
        return s;
   }
    public void loadOrderData(String ID)throws Exception{
        p.clear();
        ObservableList<Product> data2 = null;   
        odTable1.setItems(data2);
        ArrayList<Product> ar= new ArrayList<>();
        tp = 0;
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT a.ID as ID, b.name as name, a.qty_ordered as qty, b.price as price, b.re_stock_point as re_stock_point, (b.price*a.qty_ordered) as payable from trans a join products b on b.ID = a.product_id where a.order_ID = "+ID);
        while (rs.next()) {
            ar.add(new Product(rs.getString("ID"),rs.getString("name"), 
                               rs.getString("qty"), rs.getString("price"),//numberFormatter.format(Float.parseFloat(rs.getString("price"))),//String.format("%10.2f", Float.parseFloat(rs.getString("price"))), 
                               rs.getDouble("payable")));
            tp += rs.getDouble("payable");
        }        
        total_payable1.setText(tp+"");
        
        try{
            data2 = FXCollections.observableArrayList(ar);   
            odTable1.setItems(data2);
        }catch(Exception e){
            e.printStackTrace();
        }
        
            
    }
    public ArrayList<String> loadItemName(String ID) throws ClassNotFoundException, SQLException{
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT name,  price, qty from products where ID = "+ID);
        ArrayList<String> s = new ArrayList<>();
        while (rs.next()) {
            s.add(rs.getString("name"));
            s.add(rs.getString("price"));    
            s.add(rs.getString("qty"));      
        }
        return s;
    }

    public void loadSalesReport()throws Exception{
        //SELECT a.ID as ID, b.name as name, a.qty_ordered as qty, b.price as price, b.re_stock_point as re_stock_point, (b.price*a.qty_ordered) as payable, c.date_ordered as date_ordered from trans a join products b on b.ID = a.product_id join orders c on a.order_ID = c.ID where c.date_ordered between "2016-03-16" AND "2016-03-18" ORDER BY `a`.`ID` ASC
        //OdR
        OdR.clear();
        ObservableList<OrderReport> data2 = null;   
        orderReportTable.setItems(data2);
        ArrayList<OrderReport> ar= new ArrayList<>();
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT a.ID as ID, b.name as name, a.qty_ordered as qty, b.price as price, b.re_stock_point as re_stock_point, (b.price*a.qty_ordered) as payable, c.date_ordered as date_ordered from trans a join products b on b.ID = a.product_id join orders c on a.order_ID = c.ID where c.date_ordered between '"+from.getValue()+"' AND '"+to.getValue()+"' ORDER BY a.ID ASC");
        while (rs.next()) {
            OdR.add(new OrderReport(rs.getString("ID"),
                    rs.getString("name"), 
                    rs.getString("qty"), 
                    rs.getString("price"), 
                    rs.getString("date_ordered")));
            ar.add(new OrderReport(rs.getString("ID"),
                    rs.getString("name"), 
                    rs.getString("qty"), 
                    rs.getString("price"), 
                    rs.getString("date_ordered")));
        }
        try{
            data2 = FXCollections.observableArrayList(ar);   
            orderReportTable.setItems(data2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setOrderReportTable()throws Exception{
       /* loadSalesReport();
        try {
            c_pname.setSortable(false);
            c_pname.setCellValueFactory(new PropertyValueFactory<>("name"));
            c_price.setSortable(false);
            c_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            c_qty.setSortable(false);
            c_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    public void setIDNO(String ID) throws Exception{
        this.IDNO = ID;
        
       // username.setText(ID);
        //loadData();
    }
    public void getLatestIDNO() throws ClassNotFoundException, SQLException{
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT userID from sessions order by ID DESC limit 1");
        while (rs.next()) {
            this.IDNO = rs.getString("userID");
        }
    }
    
    @FXML 
    public void genRep() throws DocumentException{
        //sr.GenerateReport("JOEL");
        odr = new OrderReports(otr, from.getValue().toString(), to.getValue().toString());
        gsr.setId("show");
    }
    @FXML
    public void hidegsr(){        
        gsr.setId("hide");
    }
    @FXML
    public void salesReport()throws Exception{
        
        gsr.setId("hide");
        otr.clear();
        ObservableList<OrderTableReport> data2 = null;   
        orderReportTable.setItems(data2);
        
        com.mysql.jdbc.Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = null;
        if(from.getValue().toString().isEmpty() || to.getValue().toString().isEmpty()){
            
             rs = stm.executeQuery("SELECT a.ID as ID, b.name as name, a.qty_ordered as qty, b.price as price, b.re_stock_point as re_stock_point, (b.price*a.qty_ordered) as payable, a.order_ID as order_ID, DATE_FORMAT(c.date_ordered,'%M %d, %Y') as date_ordered from trans a join products b on b.ID = a.product_id join orders c on a.order_ID = c.ID  ORDER BY a.ID ASC");
        }else{
             rs = stm.executeQuery("SELECT a.ID as ID, b.name as name, a.qty_ordered as qty, b.price as price, b.re_stock_point as re_stock_point, (b.price*a.qty_ordered) as payable, a.order_ID as order_ID, DATE_FORMAT(c.date_ordered,'%M %d, %Y') as date_ordered from trans a join products b on b.ID = a.product_id join orders c on a.order_ID = c.ID where c.date_ordered >= '"+from.getValue().toString()+" 00:00:00' AND c.date_ordered <= '"+to.getValue().toString()+" 23:59:59' ORDER BY a.ID ASC");        
        }
        while (rs.next()) {
           // this.IDNO = rs.getString("userID");
            otr.add(new OrderTableReport(rs.getString("ID"), rs.getString("date_ordered"), rs.getString("name"), rs.getString("qty"), rs.getString("payable")));
           
        }
        Float f2  = 0f, tqy2 = 0f;
        
        for(int i = 0 ; i < otr.size() ; i++){
            f2 += Float.parseFloat(otr.get(i).getPayable());
            tqy2 += Float.parseFloat(otr.get(i).getQty_ordered());
        }
        ts2.setText(f2.toString());
        tq2.setText(tqy2.toString());
        try{
            data2 = FXCollections.observableArrayList(otr);   
            orderReportTable.setItems(data2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setUpOrderReportTable(){
        
        try {
            c1.setSortable(false);
            c1.setCellValueFactory(new PropertyValueFactory<>("ID"));
            c2.setSortable(false);
            c2.setCellValueFactory(new PropertyValueFactory<>("date_ordered"));
            c3.setSortable(false);
            c3.setCellValueFactory(new PropertyValueFactory<>("product_order"));
            c4.setSortable(false);
            c4.setCellValueFactory(new PropertyValueFactory<>("qty_ordered"));
            c5.setSortable(false);
            c5.setCellValueFactory(new PropertyValueFactory<>("payable"));
        } catch (Exception ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
