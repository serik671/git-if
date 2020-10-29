package javaiview;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
    private static CircLoad circLoading = LeftPane.loading;
    private static Timeline loading = new Timeline(new KeyFrame(Duration.millis(500),e->loading()));
    private static TranslateTransition ttLeft ;
    private static TranslateTransition ttRight ;
    private static TranslateTransition ttUp ;
    private static FadeTransition ftImage;
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
        circLoading.c1.setStroke(Color.web("#0000cc"));
        circLoading.c2.setStroke(Color.web("#0099ff"));
        circLoading.c3.setStroke(Color.web("#99ccff"));
        switch (n){
            case 0 : break;
            case 1 : circLoading.c3.setStroke(Color.web("#020035"));break;
            case 2 : circLoading.c2.setStroke(Color.web("#020035"));circLoading.c3.setStroke(Color.web("#020035"));break;            
            default : {
                circLoading.c1.setStroke(Color.web("#020035"));
                circLoading.c2.setStroke(Color.web("#020035"));
                circLoading.c3.setStroke(Color.web("#020035"));
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
    public static void ClearRoot(){
        JavaIView.Nods.clear();
        JavaIView.Nods.addAll(JavaIView.root.getChildren());
        ttLeft = new TranslateTransition(Duration.millis(500), JavaIView.left);
        ttRight = new TranslateTransition(Duration.millis(500), JavaIView.right);
        ttUp = new TranslateTransition(Duration.millis(500), JavaIView.up);
        if(JavaIView.image != null){
            ftImage = new FadeTransition(Duration.millis(500),JavaIView.image);
            ftImage.setToValue(0);
            ftImage.play();
        }
        ttLeft.setToX(-JavaIView.left.getWidth());
        ttRight.setToX(JavaIView.width);
        ttUp.setToY(-31);
        ttUp.play();
        ttRight.play();
        ttLeft.play();
        ttLeft.setOnFinished(event->{ 
            if(!JavaIView.isStart()){
            JavaIView.setStart(true);
            JavaIView.root.getChildren().clear();            
            JavaIView.root.getChildren().add(JavaIView.ss);
            JavaIView.ss.setStart();
            }else {
                JavaIView.setStart(false);
                JavaIView.ss.setStop();
            }
        });
    }
    public static void ReturnRoot(){
        JavaIView.root.getChildren().clear();
        JavaIView.root.getChildren().addAll(JavaIView.Nods);
        if(ftImage != null){
            ftImage.setToValue(1);
            ftImage.play();
        }
        ttLeft.setToX(0);
        ttRight.setToX(JavaIView.width-JavaIView.right.getWidth());
        ttUp.setToY(0);
        ttUp.play();
        ttRight.play();
        ttLeft.play();
    }
}
