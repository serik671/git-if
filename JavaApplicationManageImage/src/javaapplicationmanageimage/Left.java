package javaapplicationmanageimage;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


public class Left extends VBox {    
    public Button bcopy,bsave;
    private final int bwidth = 250;
    public Left(){
        bcopy = new Button("Копировать изображение");
        bcopy.setOnAction(event->Copy());
        bcopy.setMinWidth(bwidth);
        bcopy.setDisable(true);
        
        bsave = new Button("Скачать изображение");
        bsave.setMinWidth(bwidth);
        bsave.setDisable(true);
        
        Controller.CastomBtn(bcopy,bsave);        
        getChildren().addAll(bcopy,bsave);
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);
    }
    public void Copy(){
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putImage(JavaApplicationManageImage.center.getImage());
        clipboard.setContent(content);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Изображение скопировано в буфер обмена!");
        alert.setTitle(JavaApplicationManageImage.title);
        alert.show();
    }
    public void setBtnDisable(boolean f){
        if(f)BtnDis(bsave,bcopy);
        else BtnEn(bsave,bcopy);
    }
    private void BtnDis(Button ... b){
        for(Button o : b)o.setDisable(true);
    }
    private void BtnEn(Button ... b){
        for(Button o : b)o.setDisable(false);
    }
}
