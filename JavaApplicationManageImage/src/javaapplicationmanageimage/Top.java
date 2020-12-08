/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationmanageimage;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author Пользователь
 */
public class Top extends HBox{
    public TextField tfURL = new TextField();
    public Label label1 = new Label("Введите URL:");
    public Top(){
        //Label        
        label1.getStyleClass().add("label");        
        
        //Text field        
        tfURL.setPromptText("URL");        
        //Set Hgrow for TextField
        HBox.setHgrow(tfURL, Priority.ALWAYS);
        
        tfURL.getStyleClass().add("tf");
        
        getChildren().addAll(label1,tfURL);
        setSpacing(5);
        setAlignment(Pos.CENTER);
    }
    public void setTextFieldText(String str){
        tfURL.clear();
        tfURL.setText(str);
    }
}
