package com.osama.frontend;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by osama on 6/27/16.
 */
public class Animations {
    public void animateNodebyFade(Node a){
        FadeTransition x=new FadeTransition(new Duration(1500),a);
        x.setFromValue(100);
        x.setToValue(0);
        x.setCycleCount(1);
        x.play();


    }
    public void animateNodeByScale(Node a){
        ScaleTransition y=new ScaleTransition(new Duration(1500),a);
        y.setToX(5);
        y.setToY(5);
        y.setCycleCount(1);
        y.play();
    }
    public void cancelTransationEffectsScale(Node a){
        ScaleTransition x=new ScaleTransition(new Duration(500),a);
        x.setToY(1);
        x.setToX(1);
        x.setCycleCount(1);
        x.play();
    }
    public void cancelTransationEffectsFade(Node a){
        FadeTransition  x=new FadeTransition(new Duration(800),a);
        x.setFromValue(0);
        x.setToValue(1000);
        x.setCycleCount(1);
        x.play();

    }
    public void changeMatchcount(Node a){
        ScaleTransition x=new ScaleTransition(new Duration(1000),a);
        x.setFromX(5);
        x.setFromY(5);
        x.setToX(1);
        x.setToY(1);
        x.setCycleCount(1);
        x.play();
    }

}
