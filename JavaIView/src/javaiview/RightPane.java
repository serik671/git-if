package javaiview;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 *
 * @author serik
 */
public class RightPane extends Pane{
    private int x=1600-40,y=30,
                width = JavaIView.width-x,
                height = JavaIView.height-y;
    private VBox vb;
    public ListView<PanePreView> list;
    public PaneChooser pane1;
    private File selectFile = new File("");
    private String pattern = ".*(jpg|png|gif|jpeg)";
    public RightPane(){        
        list = new <PanePreView>ListView();
        pane1 = new PaneChooser(width,100);
        vb = new VBox(pane1,list);
        list.setTranslateY(10);
        list.setMinSize(width,height-150);
        list.getStyleClass().add("list");        
        setWidth(width);
        setHeight(height);
        setTranslateX(x);
        setTranslateY(y+1);
        pane1.button1.setOnAction(event->{
            
        });
        pane1.textfield1.setOnAction(event->{
            Controller.clearSelect();
            loadImg(pane1.textfield1.getText());
        });
        getChildren().add(vb);        
    }
    
    public void loadImg(String str){
        File file = new File(str);
        Image image;        
        ImageView img;
        JavaIView.left.setEnableButtons(false);
        if(file.isDirectory()){
            
            ObservableList<PanePreView> list = FXCollections.observableArrayList();
            
            for(File o : file.listFiles()){
                if (o.getName().matches(pattern)){
                image = new Image(o.toURI().toString());   
                PanePreView preView = new PanePreView(image,o.getName(),o);
                list.add(preView);
                if(getSelectFile()!=null)
                if(getSelectFile().getName().equals(o.getName())){
                    list.remove(preView);
                    list.add(0,preView);  
                    JavaIView.setPreViewSelect(preView);
                    JavaIView.getPreViewSelect().setSelect(true);
                    JavaIView.left.setLabelSize(preView);
                    JavaIView.left.setLabelWeight(preView);
                }
                System.out.println(o.getName());                
                }                
            }
            this.list.setItems(list);  
        }else if (file.isFile()){
            if (file.getName().matches(pattern)){
                image = new Image(file.toURI().toString());                
                img = new ImageView(image);                
                Controller.SetImageView(img);
                JavaIView.image = img;                
                String [] path = file.getPath().split("/");
                String fpath = "/";
                for(int i=0; i<path.length-1; i++){
                    fpath += path[i]+"/";
                }
                setSelectFile(file);
                loadImg(fpath);                
                JavaIView.root.getChildren().add(JavaIView.image);
                JavaIView.left.setEnableButtons(true);
                System.out.println(file.getName()+" | "+fpath);
            }
        }else{
            pane1.textfield1.setText("NOT FILE");
            list.getItems().clear();
        }        
    }
    
    public void setSelectFile(File file){
        selectFile = file;
    }
    public File getSelectFile(){
        return selectFile;
    }
    
}
