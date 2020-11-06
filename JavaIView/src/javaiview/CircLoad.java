package javaiview;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.animation.RotateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class CircLoad extends Pane {
    Circle c1;
    RotateTransition rt1;
    DropShadow ds1;
    Circle c2;
    RotateTransition rt2;
    DropShadow ds2;
    Circle c3;
    RotateTransition rt3;
    DropShadow ds3;
    /*Circle c4;
    Circle c5;*/
    int strokeWidth = 10;
    private void CastomCircle(Circle ... c){
        for(Circle o : c){
            o.getStyleClass().add("circLoad");
            o.setStrokeWidth(strokeWidth);            
        }
        getChildren().addAll(c);
    }
    private void Start(RotateTransition ... rt){
        for(RotateTransition o : rt){
            o.play();
        }
    }
    private void Stop(RotateTransition ... rt){
        for(RotateTransition o : rt){
            o.stop();
        }
    }
    public CircLoad(){
        //DropShadow
        ds1 = new DropShadow();
        ds1.setColor(Color.web("#0000cc"));
        ds1.setRadius(25);
        ds2 = new DropShadow();
        ds2.setColor(Color.web("#0099ff"));
        ds2.setRadius(25);
        ds3 = new DropShadow();
        ds3.setColor(Color.web("#99ccff"));
        ds3.setRadius(25);
        //Circle
        c1 = new Circle(150);
        c1.setStroke(Color.web("#0000cc"));
        c1.setEffect(ds1);
        c1.setStyle("-fx-stroke-dash-array:80");
        c2 = new Circle(100);
        c2.setStroke(Color.web("#0099ff"));
        c2.setEffect(ds2);
        c2.setTranslateX(strokeWidth/2-4);
        c2.setTranslateY(strokeWidth/2-4);
        c2.setStyle("-fx-stroke-dash-array:45");
        c3 = new Circle(50);
        c3.setStroke(Color.web("#99ccff"));
        c3.setEffect(ds3);
        c3.setTranslateX(strokeWidth-7);
        c3.setTranslateY(strokeWidth-7);
        c3.setStyle("-fx-stroke-dash-array:50");
        CastomCircle(c1,c2,c3);
        //Animation
        rt1 = new RotateTransition(Duration.millis(4500),c1);
        rt1.setInterpolator(Interpolator.LINEAR);
        rt1.setByAngle(360);
        rt1.setCycleCount(Animation.INDEFINITE);
        rt2 = new RotateTransition(Duration.millis(2050),c2);
        rt2.setInterpolator(Interpolator.LINEAR);
        rt2.setByAngle(-360);
        rt2.setCycleCount(Animation.INDEFINITE);
        rt3 = new RotateTransition(Duration.millis(2300),c3);
        rt3.setInterpolator(Interpolator.LINEAR);
        rt3.setByAngle(360);
        rt3.setCycleCount(Animation.INDEFINITE);        
    }
    
    public void setStart(boolean is){
        if(is)
            Start(rt1,rt2,rt3);
        else Stop(rt1,rt2,rt3); 
    }
    public boolean isStart(){        
        return rt1.getStatus()==Status.RUNNING||
               rt2.getStatus()==Status.RUNNING||
               rt3.getStatus()==Status.RUNNING;
    }
}
