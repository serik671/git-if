package javaapplicationmwv;
import javafx.animation.Animation.Status;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;



/**
 *
 * @author serik
 */
public class ModalWindow extends Stage{
    public ModalWindow(){        
        Pane root = new Pane();
        //List of colors
        ObservableList<String> colors = FXCollections.observableArrayList(
                "RED","BLACK","BLUE","YELLOW",
                "GREEN","PINK","BROWN","AQUA"
        );
        //Button
        Button btnClose = new Button("Close");
        btnClose.getStyleClass().add("btn");
        btnClose.setOnAction(event->close());
        
        //ChoiseBox
        ChoiceBox<String> cbColor = new ChoiceBox<String>(colors);  
        cbColor.setValue(JavaApplicationMWV.rec.getFill().toString());
        cbColor.getSelectionModel().selectedItemProperty().addListener((old_val,val,new_val)->{
            if(JavaApplicationMWV.ftRec.getStatus() != Status.PAUSED){            
            JavaApplicationMWV.ftRec.stop();
            JavaApplicationMWV.ftRec.setFromValue(Color.web(val));
            JavaApplicationMWV.ftRec.setToValue(Color.web(new_val));
            JavaApplicationMWV.ftRec.play();
            }else JavaApplicationMWV.rec.setFill(Color.web(new_val));
        });
        //Label
        Label lColor = new Label("Выбирете цвет:");
        //HBox
        HBox hbColor = new HBox(lColor,cbColor);
        hbColor.setTranslateY(50);
        hbColor.setTranslateX(10);
        hbColor.setAlignment(Pos.CENTER);
        hbColor.setSpacing(5);
        
        //RadioButton
        RadioButton rbRotate = new RadioButton("Вращение");
        rbRotate.setSelected(JavaApplicationMWV.rtRec.getStatus()==Status.RUNNING);
        rbRotate.setOnAction(event->{            
            if(rbRotate.isSelected()) JavaApplicationMWV.rtRec.play();
            else JavaApplicationMWV.rtRec.pause();
         });
        RadioButton rbColor = new RadioButton("Переливание");
        rbColor.setSelected(JavaApplicationMWV.ftRec.getStatus()==Status.RUNNING);
        rbColor.setOnAction(event->{            
            if(rbColor.isSelected()) JavaApplicationMWV.ftRec.play();
            else JavaApplicationMWV.ftRec.pause();
         });
        RadioButton rbScale = new RadioButton("Изменение размера");
        rbScale.setSelected(JavaApplicationMWV.stRec.getStatus()==Status.RUNNING);
        rbScale.setOnAction(event->{            
            if(rbScale.isSelected()) JavaApplicationMWV.stRec.play();
            else JavaApplicationMWV.stRec.pause();
         });
        //VBox
        VBox vbAnimation = new VBox(rbRotate,rbColor,rbScale);
        vbAnimation.setTranslateX(250);
        vbAnimation.setTranslateY(10);
        vbAnimation.setAlignment(Pos.CENTER_LEFT);
        vbAnimation.setSpacing(5);

        root.getChildren().addAll(btnClose,hbColor,vbAnimation);
        Scene scene = new Scene(root,500,100);
        scene.getStylesheets().add("javaapplicationmwv/design/style.css");
        setScene(scene);
        setX(710);
        setY(680);        
        //setAlwaysOnTop(true);
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        setTitle("Modal option of rectangle");
        getIcons().add(JavaApplicationMWV.icon);
        showAndWait();
    }
}
