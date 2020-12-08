package javaapplicationmanageimage;

import java.net.URL;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller {
    public static void CastomBtn(Button ... b){
        for(Button o : b){
            o.getStyleClass().add("button");            
        }
    }
    public static void LoadImage(String path, Stage stage){        
        new Thread(JavaApplicationManageImage.load).start();       
        if(path.isEmpty()){
            JavaApplicationManageImage.center.setURL(null);
            JavaApplicationManageImage.center.setImage(null);
            JavaApplicationManageImage.left.setBtnDisable(true);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("¬ведите URL в текстовое поле");
            alert.setTitle(JavaApplicationManageImage.title);
            alert.show();
        }else
        try{
        URL url = new URL(path);
        JavaApplicationManageImage.center.setWeight(url.openStream().available());
        Image image = new Image(url.openStream());
        
        if(stage.getWidth()<image.getWidth()+300)stage.setMinWidth(image.getWidth()+JavaApplicationManageImage.left.bcopy.getWidth()+10);
        else stage.setMinWidth(image.getWidth()+JavaApplicationManageImage.left.bcopy.getWidth());
        if(stage.getHeight()<image.getHeight()+100)stage.setMinHeight(image.getHeight()+JavaApplicationManageImage.top.tfURL.getHeight()+35);
        else stage.setMinHeight(image.getHeight()+JavaApplicationManageImage.top.tfURL.getHeight()+25);
        
        JavaApplicationManageImage.center.setWidth(image.getWidth());
        JavaApplicationManageImage.center.setHeight(image.getHeight());
        JavaApplicationManageImage.center.setImage(image);        
        JavaApplicationManageImage.center.setURL(path);
        JavaApplicationManageImage.left.setBtnDisable(false);
        }catch(Exception e){
            if(e.getMessage().equals(JavaApplicationManageImage.top.tfURL.getText()))
            JavaApplicationManageImage.top.setTextFieldText("Not image: "+e.getMessage());
            else JavaApplicationManageImage.top.setTextFieldText(e.getMessage());
            JavaApplicationManageImage.left.setBtnDisable(true);
            JavaApplicationManageImage.center.setImage(null);
            JavaApplicationManageImage.center.setURL(null);
        }
        JavaApplicationManageImage.load.stop();
        JavaApplicationManageImage.top.label1.setText("¬ведите URL: ");
        JavaApplicationManageImage.scene.setCursor(Cursor.DEFAULT);
    }
}
