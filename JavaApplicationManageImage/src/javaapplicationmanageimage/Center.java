package javaapplicationmanageimage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Center extends ImageView{
    private String url;
    private double weight;
    private double width, height;
    public Center(){}
    public void setURL(String url){
        this.url = url;
    }
    public String getURL(){        
        return url;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    public double getWeight(){
        return weight;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public double getWidth(){
        return width;
    }
        public void setHeight(double height){
        this.height = height;
    }
    public double getHeight(){
        return height;
    }
}
