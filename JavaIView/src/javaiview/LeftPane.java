package javaiview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class LeftPane extends Pane {
    private Button btnHelp, btnInfo, btnViewMode, btnCopyImage, btnSleep, btnOption;    
    private Label lSize, lWeight;
    private CheckBox chBIsGrad, chBIsStart;
    private int height = JavaIView.height, width = 350;
    private int gran = 231;
    public static CircLoad loading = new CircLoad();    
    public LeftPane(){
        setMinWidth(width);
        setMaxWidth(width+5);        
        setMinHeight(height-31); 
        setTranslateY(31);
        loading.setTranslateX(180);
        loading.setTranslateY(height-gran);
        loading.setStart(true);
        Controller.setLoadingGrad(true);
        
        //Buttons info
        btnHelp = new Button("Помощь");
        btnHelp.setMinSize(width/2,35);
        btnInfo = new Button("Справка");
        btnInfo.setMinSize(width/2,35);
        HBox hbInfo = new HBox(btnInfo,btnHelp);        
        hbInfo.setSpacing(1);
        hbInfo.setTranslateY(height-2*gran);
        hbInfo.setTranslateX(1);
        hbInfo.setMinWidth(width);
        hbInfo.setAlignment(Pos.CENTER);
        CustomButtons(btnInfo,btnHelp);
        //Buttons Controls
        btnViewMode = new Button("Режим просмотра");
        btnViewMode.setDisable(true);
        btnViewMode.setMinSize(width-20, 50);
        btnCopyImage = new Button("Копировать изображение");
        btnCopyImage.setDisable(true);
        btnCopyImage.setMinSize(width-20,50);
        btnSleep = new Button("Спящий режим");
        btnOption = new Button("Настройки");
        btnSleep.setMinSize(width-20,50);
        btnOption.setMinSize(width-20,50);
        btnSleep.setOnAction(event-> Controller.ClearRoot());
        VBox vbControl = new VBox(btnViewMode,btnCopyImage,btnSleep,btnOption);
        vbControl.setTranslateY(100);
        vbControl.setTranslateX(10);
        vbControl.setAlignment(Pos.CENTER);
        vbControl.setSpacing(50);
        CustomButtons(btnViewMode,btnCopyImage,btnSleep,btnOption);
        //Line
        Line linGran = new Line(0,height-1.8*gran,width,height-1.8*gran);
        linGran.getStyleClass().add("lineLeft");
        Line linGran1 = new Line(0,height-2.05*gran,width,height-2.05*gran);
        linGran1.getStyleClass().add("lineLeft");
        //Labels
        lSize = new Label("Size:");
        lSize.setTranslateY(580);
        lSize.setStyle("-fx-text-fill:linear-gradient(to right,white,#0099ff); -fx-font-size:16");
        lWeight = new Label("Weight:");
        lWeight.setTranslateY(580);
        lWeight.setTranslateX(180);
        lWeight.setStyle("-fx-text-fill:linear-gradient(to right,white,#0099ff);-fx-font-size:16");
        
        
        getChildren().addAll(loading,hbInfo,linGran,linGran1,lSize,lWeight,vbControl);
    }
    
    private static void CustomButtons(Button ... btn){
        for(Button bt : btn){
            bt.getStyleClass().add("leftBtn");
        }        
    }
    public void setLabelSize(PanePreView preView){
        lSize.setText("Size: "+Math.round(preView.getImage().getWidth())+"x"+Math.round(preView.getImage().getHeight())+" px");
    }
    public void setLabelWeight(PanePreView preView){
        double weight=0;
        String format = "%.1f";
        try{
        weight = preView.getFileInputStream().available();
        }catch (Exception ex){
            System.err.print("Not weight");
        }
        if(weight<1000)lWeight.setText("Weight: "+Math.round(weight)+" byte");
        else if(weight<1000000)lWeight.setText("Weight: "+String.format(format,weight/1000)+" KB");
        else if(weight<1000000000)lWeight.setText("Weight: "+String.format(format,weight/1000000)+" MB");
        else lWeight.setText(""+String.format(format,weight/1000000000)+"GB");
    }
    public void LabelsClear(){
        lSize.setText("Size:");
        lWeight.setText("Weight:");
    }
    public void setEnableButtons(boolean f){
        btnViewMode.setDisable(!f);
        btnCopyImage.setDisable(!f);
    }
    public boolean isEnableButtons(){
        return !btnViewMode.isDisable();
    }
}
