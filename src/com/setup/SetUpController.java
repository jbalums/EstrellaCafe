/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.setup;

import com.DBCon.DBClass;
import com.mysql.jdbc.Connection;
import estrellacafe.EstrellaCafe;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ruedi
 */
public class SetUpController implements Initializable {
    public DBClass db = new DBClass();
    @FXML
    private Label username_label;
    @FXML
    private Label password_label;
    @FXML
    private Label password2_label;
    @FXML
    private TextField username;
    @FXML    
    private TextField password;
    @FXML    
    private TextField password2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    @FXML
    public void validate_username(){
        if(username.getText().length() > 6){
            username.setId("success");
            username_label.setId("label_err");
        }else{
            username.setId("error");
            username_label.setId("label_error");
        }
    }
    
    @FXML
    public void validate_password(){
        if(password.getText().length() > 6){
            password.setId("success");
            password_label.setId("label_err");
        }else{
            password.setId("error");
            password_label.setId("label_error");
        }
    }
    @FXML
    public void validate_password2(){
        if(password.getText().equals(password2.getText())){
            password2.setId("success");
            password2_label.setId("label_err");
        }else{
            password2.setId("error");
            password2_label.setId("label_error");
        }
    }
    @FXML
    private void close(ActionEvent event){
        Platform.exit();
    }
    @FXML
    private void submit(ActionEvent event) throws Exception{
        validate_username();
        validate_password();
        if((!username.getText().isEmpty())&&(!password.getText().isEmpty())&&(password.getText().equals(password2.getText()))){
            try {
                Connection conn = db.getConnection();
                Statement st = conn.createStatement();
                EstrellaCafe jf = new EstrellaCafe();
                
               ((Node)(event.getSource())).getScene().getWindow().hide();
                    
                    int resU = st.executeUpdate("INSERT INTO user (username, password, user_type) VALUES ('"+username.getText()+"', '"+password.getText()+"', 'admin');");
                    //int resU2 = st.executeUpdate("INSERT INTO user (username, password) VALUES ('"+username.getText()+"', '"+password.getText()+"');");
                    if(resU > 0   ){
                        jf.start(new Stage());
                    }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SetUpController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SetUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
