package javafonts;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.control.Spinner;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

/**
 *
 * @author serik
 */
public class JavaFonts extends Application{
    private final int width=600,height=500;
    BorderPane root = new BorderPane();    
    Scene scene = new Scene(root,width,height);
    Label labelOut = new Label("Out Label");
    TextField tfEnter = new TextField();
    Button btnEnter = new Button("Enter");
    Button btnShow = new Button("Show");
    HBox btnBox = new HBox(btnEnter,btnShow);
    ColorPane colorsT = new ColorPane("Font color"); 
    ColorPane colorsB = new ColorPane("Background color");
    VBox colors = new VBox(colorsT,colorsB);
    CheckBox isAlways = new CheckBox("always on top");
    Spinner<Integer> sSize = new Spinner<Integer>(1,200,20);    
    GridPane gs = new GridPane();
    VBox settings = new VBox(gs,isAlways);
    public static String title;
    ArrayList<Present> pList = new ArrayList<Present>();
    double x,y;
    @Override public void start(Stage stage){
        title = this.getClass().getSimpleName();
        /*BorderPane.setAlignment(btnEnter,Pos.CENTER);        
        BorderPane.setAlignment(btnShow, Pos.CENTER);*/
        settings.setAlignment(Pos.CENTER);        
        btnBox.setAlignment(Pos.CENTER);
        btnBox.setSpacing(50);
        HBox.getHgrow(tfEnter);
        
        settings.setSpacing(10);
        isAlways.getStyleClass().add("cb");
        sSize.setMaxWidth(75);
        
        sSize.valueProperty().addListener(event->{
            labelOut.setStyle("-fx-font-size:"+sSize.getValue());
        });
        
        gs.add(new Label("Size"),0,0);
        gs.add(sSize,1,0);
        gs.setHgap(10);
        
        tfEnter.setPromptText("Enter some word");
        tfEnter.getStyleClass().add("tf");        
        btnEnter.getStyleClass().add("btn");
        btnShow.getStyleClass().add("btn");
        btnShow.setDisable(true);        
        labelOut.getStyleClass().add("labelO");
        root.setTop(tfEnter);
        root.setCenter(labelOut);
        root.setBottom(btnBox);
        root.setRight(settings);
        colors.setSpacing(20);
        colorsB.transparent.setSelected(true);
        root.setLeft(colors);
        scene.getStylesheets().add("design/style.css");
        scene.setOnKeyPressed(event->{
            if(event.getCode()==KeyCode.ESCAPE)stage.close();
        });
        btnEnter.setOnAction(event->{  
           btnShow.setDisable(false);
           labelOut.setTextFill(colorsT.getColor());
           labelOut.setText(tfEnter.getText());           
           labelOut.setWrapText(true); 
           
           
           colorsT.setPickerColor(colorsT.getColor());
           colorsB.setPickerColor(colorsB.getColor());
           String color = colorsT.getColor().toString();
           color = "#"+color.substring(2, 8);
           if(colorsT.getColor()==Color.TRANSPARENT)color="transparent";
           
           String back = colorsB.getColor().toString();           
           back = "#"+back.substring(2, 8);
           if(colorsB.getColor()==Color.TRANSPARENT)back="transparent";
           
           labelOut.setStyle(
                   "-fx-background-color:"+back+";\n"+
                   "-fx-text-fill:"+color+";\n"+
                   "-fx-font-size:"+sSize.getValue()+";\n"
                    );
           System.out.println(labelOut.getStyle());
        });
        btnShow.setOnAction(event->{
           String color = colorsT.getColor().toString();
           color = "#"+color.substring(2, 8);
           if(colorsT.getColor()==Color.TRANSPARENT)color="transparent";
           
           String back = colorsB.getColor().toString();           
           back = "#"+back.substring(2, 8);
           if(colorsB.getColor()==Color.TRANSPARENT)back="transparent";
           
           if(labelOut.getText().isEmpty()){
               Alert alert = new Alert(AlertType.ERROR);
               alert.setContentText("Not text");
               alert.showAndWait();
           }else{
               pList.add(new Present(labelOut.getText(),color,back,sSize.getValue(),isAlways.isSelected()));
           }
        });        
        stage.setOnCloseRequest(event->{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Вы уверены, что хотите выйти? \nМогут остаться не закрытые окна, закрыть их?");
            alert.setTitle(title);
            ButtonType btY = new ButtonType("Да");
            ButtonType btN = new ButtonType("Нет");    
            ButtonType btC = new ButtonType("Отмена");                
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(btY,btN,btC);            
            alert.showAndWait();
            if(alert.getResult().equals(btN))System.out.println("No");
            if(alert.getResult().equals(btC))event.consume();            
            if(alert.getResult().equals(btY)){
                for(Present o:pList)o.close();
            }
        });
        
        btnEnter.requestFocus();
        stage.getIcons().add(new Image("icons/icon.png"));
        stage.setScene(scene);
        stage.setMinWidth(width);
        stage.setMinHeight(height+100);
        stage.setTitle(title);
        //stage.initStyle(StageStyle.TRANSPARENT);
        //stage.setAlwaysOnTop(true);
        stage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
