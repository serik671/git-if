package javanewyear;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author serik
 */
public class SnowFlake extends ImageView{
    private int x,y=0;
    private boolean f;
    public SnowFlake(){
        setImage(new Image(getClass().getResourceAsStream("images/snowflake1.png")));
        setFitHeight(60);
        setFitWidth(60);        
        setTranslateX(rndX());
        AnimationTimer animation = new AnimationTimer(){
            @Override public void handle(long now){
            
                setX(x);
                setY(y);
            }
        };
        animation.start();
        Timeline line = new Timeline(new KeyFrame(Duration.millis(1000/60),event->{
            if(f)x+=1;
            else x-=1;
            if(y>JavaNewYear.scene.getHeight()){
                rndX();
                y=0;
            }
            else y+=1;
        }));
        Timeline rand = new Timeline(new KeyFrame(Duration.millis(500),event->{
            Random rnd = new Random();
            f = rnd.nextBoolean();
        }));
        rand.setCycleCount(Animation.INDEFINITE);
        rand.play();
        line.setCycleCount(Animation.INDEFINITE);
        line.play();
        RotateTransition rotate = new RotateTransition(Duration.millis(3000),this);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();
    }        
    public int rndX(){
        Random rnd = new Random();
        x = rnd.nextInt((int)JavaNewYear.scene.getWidth()-60);
        return x;
    }
    public int rndY(){
        Random rnd = new Random();
        y = rnd.nextInt((int)JavaNewYear.scene.getHeight()-60);
        return y;
    }
    public void putY(int y){
        this.y = y;
    }
    public void putX(int x){
        this.x = x;
    }
}
