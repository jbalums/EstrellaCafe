/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrellacafe;

import com.DBCon.DBClass;
import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Mahal
 */
public class EstrellaCafe extends Application {
    
    public DBClass db = new DBClass();
    public String ID;
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = null;
       Connection conn = db.getConnection();
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery("SELECT * from user");
        int size=0;
        while (res.next()) {
            size++;
        }
        if(size > 0){
             root = FXMLLoader.load(getClass().getResource("/com/login/login.fxml"));
        }else{
             root = FXMLLoader.load(getClass().getResource("/com/setup/acc_setup.fxml"));
        }
        
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
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void start(Stage stage, String src) throws Exception {        
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource(src));
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
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void setUID(String ID){
        this.ID = ID;
    }
    public String getUID(){
        return ID;
    }
}
