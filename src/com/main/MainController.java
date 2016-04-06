/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import com.DBCon.DBClass;
import com.login.LoginController;
import com.orders.OrdersController;
import com.products.Product;
import estrellacafe.EstrellaCafe;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Mahal
 */
public class MainController implements Initializable {
    
    private String IDNO;
    @FXML
    private ImageView logo,logo2;
    @FXML
    private Button close_btn,go, product_btn, order_btn,logoutbtn;
    @FXML
    private AnchorPane form_pane;
    @FXML
    private Label username;
    @FXML
    private String u="";
    private static BorderPane root = new BorderPane();
    public static BorderPane getRoot() {
        return root;
    }
    
    public DBClass db = new DBClass(); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        logos();
        hovers();
    }    
    @FXML
    public void go() throws Exception{
        form_pane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/com/home/home.fxml")));
    }
    @FXML
    public void products()throws Exception{
        try{
            form_pane.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/com/products/products.fxml")));   
        }catch(Exception e){
        }
    }
    @FXML
    public void orders()throws Exception{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/orders/orders.fxml"));     
             
       //OrdersController controller = fxmlLoader.<OrdersController>getController();
        //controller.setIDNO(1+"");
        form_pane.getChildren().setAll((Node) fxmlLoader.load());
    }
    @FXML
    public void exit(){
        Platform.exit();
    }
    @FXML
    public void exit_hover(){
        close_btn.setId("exit");
    }
    @FXML
    public void exit_hover_out(){
        close_btn.setId("exit_out");
    }
    
    public void logos(){
        BufferedImage bufferedImage = null;
         try {
             bufferedImage = ImageIO.read(new File("C:\\Users\\Mahal\\Documents\\NetBeansProjects\\EstrellaCafe\\assets\\logo2.jpg"));
         } catch (IOException ex) {
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        this.logo.setImage(image);
        BufferedImage bufferedImage2 = null;
         try {
             bufferedImage2 = ImageIO.read(new File("C:\\Users\\Mahal\\Documents\\NetBeansProjects\\EstrellaCafe\\assets\\logo.jpg"));
         } catch (IOException ex) {
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
        WritableImage image2 = SwingFXUtils.toFXImage(bufferedImage2, null);
        this.logo2.setImage(image2);
    }
    public void hovers(){
        go.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                go.setId("menu_btn_hover");
            }
        });
        go.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                go.setId("menu_btn");
            }
        });
        order_btn.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                order_btn.setId("menu_btn_hover");
            }
        });
        order_btn.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                order_btn.setId("menu_btn");
            }
        });
        product_btn.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                product_btn.setId("menu_btn_hover");
            }
        });
        product_btn.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                product_btn.setId("menu_btn");
            }
        });       
        logoutbtn.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                logoutbtn.setId("menu_btn_hover");
            }
        });
        logoutbtn.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                logoutbtn.setId("menu_btn");
            }
        });
    }
    public void loadData() throws Exception{
        try{
            com.mysql.jdbc.Connection conn = db.getConnection();
            Statement stm = conn.createStatement();
            EstrellaCafe ec = null ;
            ResultSet rs = stm.executeQuery("SELECT * from user WHERE ID='"+username.getText()+"'");
            IDNO = username.getText();
            while (rs.next()) {
                username.setText("USER: "+rs.getString("username"));
                u=rs.getString("username");
                //System.out.println(rs.getString("username"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setIDNO(String ID) throws Exception{
        this.IDNO = ID;
        
        username.setText(ID);
        loadData();
    }
    public String getIDNO(){
        return this.IDNO;
    }
}
