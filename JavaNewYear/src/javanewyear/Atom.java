/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javanewyear;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

/**
 *
 * @author serik
 */
public class Atom extends StackPane{
    Circle c1, c2, c3;
    Ellipse t1,t2,t3;    
    PathTransition pt1;
    PathTransition pt2; 
    PathTransition pt3; 
    
    public Atom(){
        t1 = new Ellipse(100,300);  
        t1.setRotate(45);
        t2 = new Ellipse(100,300);
        t2.setRotate(100);
        t3 = new Ellipse(100,300);        
        t3.setRotate(155);
        c1 = new Circle(40);
        c1.setFill(Color.RED);
        c2 = new Circle(40);
        c2.setFill(Color.BLUE);
        c3 = new Circle(40);
        c3.setFill(Color.GREEN);
        pt1 = new PathTransition(Duration.millis(2000),t1,c1);
        pt2 = new PathTransition(Duration.millis(3000),t2,c2);
        pt3 = new PathTransition(Duration.millis(4000),t3,c3);
        customCircle(c1,c2,c3);        
        customEllipse(t1,t2,t3);
        customPath(pt1,pt2,pt3);
        getChildren().addAll(t1,t2,t3,c1,c2,c3);
        AnimationTimer animation = new AnimationTimer(){
            @Override public void handle(long now){
                for(SnowFlake o:JavaNewYear.list){
                    if(o.getBoundsInParent().intersects(c1.getBoundsInParent())){
                        o.putY(0);
                        o.rndX();
                    }
                }
            }
        };
        animation.start();
    }
    
    private void customCircle(Circle ... c){
        for(Circle o:c){
            DropShadow shadow = new DropShadow();
            if(o.getFill()==Color.RED) shadow.setRadius(0);
            else shadow.setRadius(50);
            shadow.setColor((Color)o.getFill());
            o.setEffect(shadow);            
        }
    }
    private void customEllipse(Ellipse ... e){
        for(Ellipse o:e){
            o.setFill(Color.TRANSPARENT);
            //o.setStroke(Color.BLACK);
        }
    }
    private void customPath(PathTransition ... pt){
        for(PathTransition o:pt){
            o.setCycleCount(Animation.INDEFINITE);
            o.setInterpolator(Interpolator.LINEAR);
            o.play();
        }        
    }    
}
