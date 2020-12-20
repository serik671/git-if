package javafonts;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



/**
 *
 * @author serik
 */
public class ColorPane extends Pane{
    private final Label title = new Label();
    public TextField red = new TextField();
    public TextField green = new TextField();
    public TextField blue = new TextField();
    public CheckBox transparent = new CheckBox();
    public ColorPicker colors = new ColorPicker();
    public int max = 255;
    private boolean isTransparent = false;
    public ColorPane(String title){
        this.title.setText(title);
        //this.title.setTranslateY(20);        
        tfCustom(red,green,blue);
        
        Label r = new Label("R: ");
        Label b = new Label("B: ");
        Label g = new Label("G: ");
        
        transparent.setText("Transparent");
        transparent.getStyleClass().add("cb");
        transparent.selectedProperty().addListener(event->{
            if(transparent.isSelected()){
                red.setDisable(true);
                green.setDisable(true);
                blue.setDisable(true);
                colors.setDisable(true);
                isTransparent = true;
            }else{
                red.setDisable(false);
                green.setDisable(false);
                blue.setDisable(false);                
                colors.setDisable(false);
                isTransparent = false;
            }
        });
        
        colors.setValue(Color.BLACK);
        colors.setMaxSize(100, 40);
        colors.getStyleClass().add("colorPicker");
        colors.setOnAction(event->{
            double rd = max*colors.getValue().getRed();
            double gn = max*colors.getValue().getGreen();
            double be = max*colors.getValue().getBlue();            
            red.setText(String.format("%.0f",rd));
            green.setText(String.format("%.0f",gn));
            blue.setText(String.format("%.0f",be));
        });
        
        GridPane grid = new GridPane();        
        grid.add(this.title,0,0,2,1); //put(0,0) but spawn 2 column and 1 row
        grid.add(colors,0,1,2,1);
        grid.add(r,0,2);
        grid.add(g,0,3);
        grid.add(b,0,4);
        grid.add(red,1,2);
        grid.add(green,1,3);
        grid.add(blue,1,4);
        grid.add(transparent,0,5,2,1);
        grid.setGridLinesVisible(false);
        
        
        getChildren().add(grid);
    }
    private void tfCustom(TextField ... tf){
        int maxlength=2;        
        for(TextField o:tf){
            o.setMaxWidth(45);
            o.getStyleClass().add("tfColor");
            o.setOnKeyPressed(event->{
                if(o.getText().length()>maxlength){                    
                    String s = o.getText().substring(0, maxlength);
                    o.setText(s);          
                    o.positionCaret(maxlength);          
                }           
            });
            o.setOnKeyReleased(event->{
                try{  
                    int a=0;
                    if(o.getText().length()>0)
                    a = Integer.parseInt(o.getText()); 
                    if(o.getText().length()==3&&a>max)throw new Exception("Your value >"+max);
                }catch(Exception e){
                    Alert alert = new Alert(AlertType.ERROR); //Не работает при StageStyle.UTILITY
                    alert.setContentText("Please enter number from 0 to 255"+"\n"+e.getMessage());
                    alert.setTitle(JavaFonts.title);
                    alert.showAndWait();
                    o.clear();
                } 
            });
        }
    }
    public Color getColor(){
        if(isTransparent)return Color.TRANSPARENT;
        if(red.getText().isEmpty())red.setText("0");
        if(green.getText().isEmpty())green.setText("0");
        if(blue.getText().isEmpty())blue.setText("0");
        return Color.rgb(Integer.valueOf(red.getText()),Integer.valueOf(green.getText()),Integer.valueOf(blue.getText()));
    }
    public void setPickerColor(Color color){
        colors.setValue(color);
    }
}
