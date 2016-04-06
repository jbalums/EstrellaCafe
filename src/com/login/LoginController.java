/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import assets.SearchBox;
import com.DBCon.DBClass;
import com.main.MainController;
import estrellacafe.EstrellaCafe;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;


public class LoginController implements Initializable {

 private static double initX;
 private static double initY;
 private double xOffset = 0;
 
                int id=0;
    public DBClass db = new DBClass(); 
    private double yOffset = 0;
    @FXML
    private Stage stage;
    @FXML
    private AnchorPane parentPane;
    @FXML
    private ImageView logo;
    @FXML
    private TextField username, password;
    @FXML
    private Label label_u,label_p, label;
    @FXML
    private Button close_btn,login_btn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BufferedImage bufferedImage = null;
         try {
             bufferedImage = ImageIO.read(new File("C:\\Users\\Mahal\\Documents\\NetBeansProjects\\EstrellaCafe\\assets\\logo2.jpg"));
         } catch (IOException ex) {
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
        WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
        this.logo.setImage(image);
        
    }    
    @FXML
    public void usernameChange(){
              label.setId("hide");
        if(username.getText().isEmpty()){
            username.setId("field_red");
            label_u.setId("show");
        }else{
            username.setId("field_green");  
            label_u.setId("hide");          
        }
    }
    
    @FXML
    public void passwordChange(){
              label.setId("hide");
        if(password.getText().isEmpty()){
            password.setId("field_red");
            label_p.setId("show");
        }else{
            password.setId("field_green");  
            label_p.setId("hide");          
        }
    }
    
    @FXML
    public void login(ActionEvent event) throws SQLException, ClassNotFoundException, Exception{
        if(!username.getText().isEmpty() && !password.getText().isEmpty()){
              label.setId("hide");
             Connection conn = db.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT ID from user where username = '"+
                                username.getText()+"' AND password = '"+password.getText()+"'"); 
               // int size=0;
                while (rs.next()) {
                    id = rs.getInt("ID");
                    
                }
                int idno = stm.executeUpdate("INSERT INTO sessions (userID) values('"+id+"')");
                    
                if(id > 0){
                   //EstrellaCafe ec = new EstrellaCafe();
                  // ec.setUID(id+"");
                   start(new Stage(), id);
                   
                   ((Node)(event.getSource())).getScene().getWindow().hide();                          
                }else{
                    username.setId("field_red");
                    password.setId("field_red");
                    label.setId("show");
                }
        }else{
            usernameChange();
            passwordChange();
        }
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
    @FXML
    public void login_hover(){
        login_btn.setId("login");
    }
    @FXML
    public void login_hover_out(){        
        login_btn.setId("login_out");
    }
    
    public void start(Stage stage, int i) throws Exception {        
        //Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/main/Main2.fxml"));     
       
        Parent root = (Parent)fxmlLoader.load();          
        MainController controller = fxmlLoader.<MainController>getController();
        controller.setIDNO(i+"");
        
        //root = FXMLLoader.load(getClass().getResource(src));
        stage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Scene scene = new Scene(root);     
        
        //System.out.println(controller.getIDNO()+"");
        stage.setScene(scene);
        stage.show();
    }
    
    
}
