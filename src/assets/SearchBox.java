/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets;

import javafx.beans.value.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 *
 * @author Mahal
 */
public class SearchBox extends Region {
 
        private TextField textBox  = new TextField();
        private Button clearButton;
        private String stxt;
        public SearchBox() {
            setId("SearchBox");
            getStyleClass().add("search-box");
            setMinHeight(30);
           // String searchBoxCss = SearchBoxSample.class.getResource("SearchBox.css").toExternalForm();
            //VBox vbox = VBoxBuilder.create().build();
            //vbox.getStylesheets().add(searchBoxCss);
           // this.getStylesheets().add("SearchBox.css");
            setPrefSize(200, 30);
            setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
            textBox.setPromptText("Search");
            clearButton = new Button();
            clearButton.setVisible(false);
            getChildren().addAll(textBox, clearButton);
            clearButton.setOnAction(new EventHandler<ActionEvent>() {                
                @Override public void handle(ActionEvent actionEvent) {
                    textBox.setText("");
                    setStxt("");
                    textBox.requestFocus();
                }
            });
            
            textBox.textProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    clearButton.setVisible(textBox.getText().length() != 0);
                    setStxt(textBox.getText());
                    if(textBox.getText().length() == 0){
                        setStxt("");
                    }
                }
            });
        }
 
        @Override
        protected void layoutChildren() {
            textBox.resize(getWidth(), getHeight());
            clearButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
        }

    public String getStxt() {
        return stxt;
    }

    public void setStxt(String stxt) {
        this.stxt = stxt;
    }

    public TextField getTextBox() {
        return textBox;
    }

    public void setTextBox(TextField textBox) {
        this.textBox = textBox;
    }
    
    }