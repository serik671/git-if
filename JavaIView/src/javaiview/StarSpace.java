/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaiview;


import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

/**
 *
 * @author serik
 */
public class StarSpace extends Pane{
    Star [] S;
    AnimationTimer timer;
    
    public StarSpace(double width, double height, int count){
        setTranslateX(width/2);
        setTranslateY(height/2);        
        S = new Star[count];
        for(int i=0; i<S.length; i++){
            Star star = new Star(width,height);
            S[i] = star;            
            getChildren().add(star);            
            star.show();
            star.setRotate(true);
        }
        
        timer = new AnimationTimer(){
            @Override public void handle(long now){
                for(Star o : S){
                    o.update();
                    o.show();
                }
            }
        };   
        timer.start();
    }
    
    public void setStart(){
        timer.start();
    }
    public void setStop(){
        timer.stop();
    }
    
}
