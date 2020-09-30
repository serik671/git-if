package javacomeback;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
public class LineFX extends Line {
    private double x1,y1,x2,y2,Cx,Cy,speed;
    public LineFX(double x1,double y1,double x2,double y2,double Cx,double Cy, double width,double speed,double angle){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.Cx = Cx;
        this.Cy = Cy;
        this.speed = speed;
        setStrokeWidth(width);
        setStrokeLineCap(StrokeLineCap.ROUND);        
        setStartX(x1);
        setStartY(y1);
        setEndX(x2);
        setEndY(y2);
        
       Timeline timer = new Timeline(new KeyFrame(Duration.millis(speed),event->{
           getTransforms().add(new Rotate(angle,Cx,Cy));
       }));
       timer.setCycleCount(Animation.INDEFINITE);
       timer.play();             
        
        JavaComeBack.root.getChildren().add(this);
    }
    
    public void setGradient(Color c1, Color c2,double v1, double v2){
        LinearGradient gradient = new LinearGradient(0,0,v1,v2,true,CycleMethod.NO_CYCLE,new Stop(0,c1),new Stop(1,c2));
        setStroke(gradient);
    }
    
}
