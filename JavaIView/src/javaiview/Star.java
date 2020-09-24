package javaiview;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

public class Star extends Circle {
    Random random = new Random();
    double width;
    double height;
    double x;
    double y;
    double sx,sy;
    double z;
    RotateTransition rt = new RotateTransition();    
    
    public Star(double width, double height){
        this.width = width;
        this.height = height;
        x = (2*random.nextInt((int)width)-width)/2;
        y = (2*random.nextInt((int)height)-height)/2; 
        z = random.nextInt((int)width);

        setRadius(10);        
        setTranslateX(x);
        setTranslateY(y);
        getStyleClass().add("star");
        setEffect(new DropShadow(20,Color.DARKGREEN));        
        
        rt.setByAngle(360);
        rt.setDuration(Duration.millis(800));
        rt.setNode(this);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
    }
    
    public void update(){
        if(z<0){
            z=width;            
            x = (2*random.nextInt((int)width)-width)/2;
            y = (2*random.nextInt((int)height)-height)/2; 
        }
        z-=30;
    }
    public void show(){
        sx = x/z*width;
        sy = y/z*height;
        setScaleX(1-z/width);  
        setScaleY(1-z/width);
        setTranslateX(sx);
        setTranslateY(sy);
    }
    public void setRotate(boolean a){
        if (a){
            rt.play();
        }else rt.stop();
    }
    
}
