package javaiview;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author serik
 */
public class Controller {
    private static double width = JavaIView.width, height = JavaIView.height;
    private static double x = width/2, y = height/2, k;
    private static int n=0;
    private static Timeline loading = new Timeline(new KeyFrame(Duration.millis(500),e->loading()));
    public static void SetImageView(ImageView img){        
        if(img.getImage().getWidth()>900){
            k = img.getImage().getWidth()/img.getImage().getHeight();
            img.setFitWidth(900);
            img.setFitHeight(900/k);
            img.setTranslateX(x-img.getFitWidth()/2);
        }else img.setTranslateX(x-img.getImage().getWidth()/2);
        if(img.getImage().getHeight()>600){
            k = img.getImage().getHeight()/img.getImage().getWidth();
            img.setFitWidth(600/k);
            img.setTranslateX(x-img.getFitWidth()/2);
            img.setFitHeight(600);
            img.setTranslateY(y-img.getFitHeight()/2);
        }else img.setTranslateY(y-img.getImage().getHeight()/2);
    }
    public static void clearSelect(){
        if(JavaIView.getPreViewSelect() != null){
            JavaIView.getPreViewSelect().setSelect(false);
            JavaIView.setPreViewSelect(null);
        }
        JavaIView.right.setSelectFile(null);
        JavaIView.root.getChildren().remove(JavaIView.image);
    }
    private static void loading(){
        JavaIView.loading.c1.setStroke(Color.web("#0000cc"));
        JavaIView.loading.c2.setStroke(Color.web("#0099ff"));
        JavaIView.loading.c3.setStroke(Color.web("#99ccff"));
        switch (n){
            case 0 : break;
            case 1 : JavaIView.loading.c3.setStroke(Color.web("#020035"));break;
            case 2 : JavaIView.loading.c2.setStroke(Color.web("#020035"));JavaIView.loading.c3.setStroke(Color.web("#020035"));break;            
            default : {
                JavaIView.loading.c1.setStroke(Color.web("#020035"));
                JavaIView.loading.c2.setStroke(Color.web("#020035"));
                JavaIView.loading.c3.setStroke(Color.web("#020035"));
            }
        }
        if(n<3)
        n++;
        else n=0;
    } 
    public static void setLoadingGrad(boolean bool){
        if(bool){
        loading.setCycleCount(Animation.INDEFINITE);        
        loading.play();
        }
        else loading.stop();
    }
}
