/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaiview;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.scene.ImageCursor;

/**
 *
 * @author serik
 */
public class JavaIView extends Application{

    private static boolean isStart = false;
    public static final int width = 1920, height = 1080;
    public static String title = "JavaIView";
    public static Scene scene;
    public static Pane root;
    public static StarSpace ss;
    public static UpPane up;
    public static RightPane right;
    public static LeftPane left;
    public static Image img;
    public static ImageView image;
    private static PanePreView panePreViewSelect;
    public static ObservableList<Node> Nods;
    
    @Override public void start(Stage stage){        
        
        root =  new Pane();
        scene = new Scene(root,width,height);
        scene.getStylesheets().add("cssstyle/design.css");        
        Nods = FXCollections.observableArrayList();
        ss = new StarSpace(width, height, 400);
        
        up = new UpPane(stage,width,title);

        
        scene.setOnKeyPressed(event->{
            if((event.getCode() == KeyCode.ESCAPE) && isStart())Controller.ReturnRoot();
        });

        
        left = new LeftPane();
        left.getStyleClass().add("paneChooser");
        
        right = new RightPane();
        right.pane1.button1.setOnAction(event->{
            Controller.clearSelect();
            try{                
            File file = right.pane1.fc.showOpenDialog(stage);            
            right.loadImg(file.getPath());
            right.pane1.textfield1.setText(file.getPath());            
            }catch (Exception ex){System.err.println("Not file");}
        });
        right.list.getSelectionModel().selectedItemProperty().addListener(
                (old_val, val, new_val)->{                                       
                    if(getPreViewSelect()!=null && new_val!=null)getPreViewSelect().setSelect(false);
                    if(new_val!=null)setPreViewSelect(new_val);
                    if(getPreViewSelect()!=null){
                        getPreViewSelect().setSelect(true);
                        if(!left.isEnableButtons())left.setEnableButtons(true);
                        left.setLabelSize(getPreViewSelect());
                        left.setLabelWeight(getPreViewSelect());
                    }else left.LabelsClear();
                    root.getChildren().remove(image);
                    try{
                        image = new ImageView(new_val.getImage());
                        Controller.SetImageView(image);
                        root.getChildren().add(image); 
                    }catch (Exception ex){System.err.println("Not Image");}
                }
        );
        
        root.getChildren().addAll(up,right,left);
        try{        
        stage.getIcons().add(new Image(new FileInputStream("Icon.png")));
        }catch(FileNotFoundException e){
            System.err.println("Not image:\n"+e.getMessage());
        }  
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        setCursor();
        stage.setTitle(title);        
        if(stage.isAlwaysOnTop())stage.setTitle(stage.getTitle()+"(AlwaysOnTop)");
        stage.show();        
        }
    
    public static void main(String[] args) {        
        GetImage(args);
        Application.launch(args);        
    }
    
    public static void GetImage(String [] args){
        try{
            img = new Image(new FileInputStream(args[0]));
            image = new ImageView(img);
            Controller.SetImageView(image);
            root.getChildren().add(image);
        }catch (Exception ex){}
        System.out.println(args.length);
    }
    public static void setPreViewSelect(PanePreView preView){
        panePreViewSelect = preView;        
    }
    public static PanePreView getPreViewSelect(){
        return panePreViewSelect;
    }
    public static boolean isStart(){
        return isStart;
    }
    public static void setStart(boolean f){
        isStart = f;        
    }
    public static void setCursor(){
        try{
           scene.setCursor(new ImageCursor(new Image(new FileInputStream("Cursor.png"))));
        }catch (FileNotFoundException e){}
    }
}
