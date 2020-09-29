package javaiview;

import javafx.scene.image.ImageView;

/**
 *
 * @author serik
 */
public class Controller {
    private static double width = JavaIView.width, height = JavaIView.height;
    private static double x = width/2, y = height/2, k;
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
    public static void LoadImagetoPath(String path){
        
    }
}
