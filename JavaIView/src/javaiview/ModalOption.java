package javaiview;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author serik
 */
public class ModalOption extends Stage{
    public ModalOption(){
        Pane root = new Pane();
        
        //Option Load Indicator
        Label lName = new Label("Настройки индикатора работы программы");
        lName.setFont(Font.font(15));
        CheckBox cbVisible = new CheckBox("Отображать индикатор работы программы");
        cbVisible.setTranslateX(20);
        cbVisible.setSelected(LeftPane.loading.isVisible());
        
        
        RadioButton rbRotate = new RadioButton("Вращение индикатора");
        rbRotate.setTranslateX(40);
        rbRotate.setSelected(LeftPane.loading.isStart());
        rbRotate.setOnAction(event->LeftPane.loading.setStart(rbRotate.isSelected()));
        
        RadioButton rbColor = new RadioButton("Переливание цветов на индикаторе");
        rbColor.setTranslateX(40);
        rbColor.setSelected(Controller.isLoadingGrad());
        rbColor.setOnAction(event-> Controller.setLoadingGrad(rbColor.isSelected()));
        if(!cbVisible.isSelected()){
            rbRotate.setDisable(true);
            rbColor.setDisable(true);
        }
        cbVisible.setOnAction(event->{
            LeftPane.loading.setVisible(cbVisible.isSelected());
            if(cbVisible.isSelected()){
                rbRotate.setDisable(false);                
                rbColor.setDisable(false);                               
            }else  {
                rbRotate.setDisable(true);                
                rbColor.setDisable(true);                
            }           
        });
        
        VBox vbLoad = new VBox(lName,cbVisible,rbRotate,rbColor);
        vbLoad.setSpacing(10);
        root.getChildren().add(vbLoad);
        
        Scene scene = new Scene(root,400,200);        
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        setTitle("Options");
        showAndWait();
    }
}
