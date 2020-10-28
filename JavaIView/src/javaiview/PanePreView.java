package javaiview;

import java.io.File;
import java.io.FileInputStream;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author serik
 */
public class PanePreView extends Pane {
    
    double width = 320, height = 100;    
    private Image image;
    private File file;
    ImageView filter;
    Label text;
    public PanePreView(Image image, String name, File file){
        this.image = image;
        this.file = file;
        try{
            filter = new ImageView(new Image(new FileInputStream("Filter.png")));
        }catch (Exception ex){}
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        text = new Label();
        text.setTranslateX(110);
        text.setText(EditText(name));
        text.setTextFill(Color.WHITE);
        setWidth(width);
        setHeight(height);
        getChildren().addAll(filter,imageView, text);
        getStyleClass().add("panePre");

        /*setOnMouseEntered(event-> text.setTextFill(Color.web("#020035")));
        setOnMouseExited(event-> text.setTextFill(Color.WHITE));*/
        
    }
    public void setImage(Image img){
        image = img;
    }
    public Image getImage(){
        return image;
    }
    public void setFile(File file){
        this.file = file;
    }
    public File getFile(){
        return file;
    }
    public FileInputStream getFileInputStream()throws Exception{
        FileInputStream file = new FileInputStream(this.file);        
        return file;
    }
    private String EditText(String name){
        String [] A = name.split("");
        String text = "";
        for (int i=0; i<A.length;i++){
            if(i%20==0){
                text+="\n";
            }
            text+=A[i];
        }
        return text;
    }
    public void setSelect(boolean f){
        getStyleClass().clear();
        if(f) getStyleClass().add("panePreSelect");
        else getStyleClass().add("panePre");
        
    }
}
