package javaapplicationmanageimage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.ImageCursor;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class JavaApplicationManageImage extends Application{
    
    public static Pane root = new Pane();
    public static BorderPane bpmain;
    public static Top top = new Top();
    public static Left left = new Left();
    public static Center center = new Center();
    public static Loader load = new Loader();
    public static Scene scene;
    int width=700, height=500;
    public static String title = "Manage Image from Net";
    
    @Override public void start(Stage stage){
        bpmain = new BorderPane();    
        scene = new Scene(bpmain,width,height);        
        scene.getStylesheets().add("javadesign/style.css");

        //top.tfURL.setOnAction(event->Controller.LoadImage(top.tfURL.getText(),stage));
        top.tfURL.setOnKeyPressed(event->{            
            if(event.getCode()==KeyCode.ENTER){
                scene.setCursor(Cursor.WAIT);
                top.label1.setText("Загрузка...: ");
            }   
        });
        top.tfURL.setOnKeyReleased(event->{
            
            if(event.getCode()==KeyCode.ENTER)                
                Controller.LoadImage(top.tfURL.getText(), stage);            
            });
        

        left.bsave.setOnAction(event->{
            String path = "", fileName, fileType;          
            FileChooser choiser = new FileChooser();            
            try{
            URL url = new URL(center.getURL());
            fileName = url.getFile();
            fileName = fileName.replace("/","");           
            String [] mas = fileName.split("\\.");
            fileType = mas[mas.length-1];
            System.out.println(mas.length+" "+fileName);
            choiser.getExtensionFilters().addAll(
                    new ExtensionFilter(fileType,"*."+fileType),
                    new ExtensionFilter("All Image",new String[]{"*.png","*.jpg","*.gif","*.jpeg"})
            );
            ReadableByteChannel channel = Channels.newChannel(url.openStream());            
            choiser.setInitialFileName(fileName);
            File file = choiser.showSaveDialog(stage);            
            if(file.getPath()!=null){            
            path =file.getPath();
            System.out.println(path);
            FileOutputStream out = new FileOutputStream(path);            
            out.getChannel().transferFrom(channel, 0,Long.MAX_VALUE);
            out.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Файл сохранён!");            
            alert.setTitle(title);
            alert.show();
            }
            channel.close();            
            }catch(Exception e){}
        });
        
        //BorderPane
        bpmain.setMinSize(0, 0);
        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(left,Pos.CENTER);
        BorderPane.setAlignment(center,Pos.CENTER);
        
        
        bpmain.setTop(top);
        bpmain.setLeft(left);
        bpmain.setCenter(center);
        scene.setOnMouseClicked(event->{
            System.out.printf("Width: %s\tHeight: %s\n",stage.getWidth(),stage.getHeight());
        });        
        stage.setMinHeight(163);
        stage.setMinWidth(633);
        stage.setTitle(title);
        stage.setScene(scene);        
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }    
}
