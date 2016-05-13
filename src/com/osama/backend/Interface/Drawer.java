package com.osama.backend.Interface;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Contract;

/**
 * Created by osama on 5/11/16.
 */
public class Drawer {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private static final int w = 30;
    private static final int h = 30;
    private Image image;


    public Canvas drawGame() {
        canvas = new Canvas(200, 200);
        graphicsContext = canvas.getGraphicsContext2D();
        drawLines(graphicsContext);
        return canvas;

    }

    private void drawLines(GraphicsContext gc) {
        gc.setLineWidth(5);
        gc.setStroke(Color.CYAN);
        gc.beginPath();
        gc.strokeLine(0, 40, 190, 30);
        gc.strokeLine(40, 0, 30, 190);
        gc.strokeLine(0, 130, 190, 120);
        gc.strokeLine(130, 0, 120, 190);
        gc.stroke();
    }

    public void drawImage(int box, int player) {
        if (player == 1) {
            image = new Image("images/cross.png");
        } else if (player == 2) {
            image = new Image("images/zero.png");
        }
        switch (box) {
            case 0: {
                graphicsContext.drawImage(image, 5, 5, w, h);
                break;
            }
            case 1: {
                graphicsContext.drawImage(image, 70, 2, w, h);
                break;
            }
            case 2: {
                graphicsContext.drawImage(image, 150, 0, w, h);
                break;
            }
            case 3: {
                graphicsContext.drawImage(image, 0, 70, w, h);
                break;
            }
            case 4: {
                graphicsContext.drawImage(image, 70, 70, w, h);
                break;
            }
            case 5: {
                graphicsContext.drawImage(image, 150, 70, w, h);
                break;
            }
            case 6: {
                graphicsContext.drawImage(image, 0, 150, w, h);
                break;
            }


            case 7: {
                graphicsContext.drawImage(image, 70, 150, w, h);
                break;
            }

            case 8: {
                graphicsContext.drawImage(image, 150, 150, w, h);
                break;
            }
        }
    }
    public void drawWinLine(int[] winBoxes){
        System.out.println("Victory");
        winBoxes=sort(winBoxes); //sort the numbers
        graphicsContext.setStroke(Color.RED);

        if(winBoxes[0]==0 && winBoxes[2]==8){
                graphicsContext.strokeLine(10,10,170,170);
            }
        else if(winBoxes[0]==0 && winBoxes[2]==2){
                graphicsContext.strokeLine(10,10,170,10);
            }
        else if(winBoxes[0]==3 && winBoxes[2]==5){
                graphicsContext.strokeLine(10,60,170,60);
            }
        else if(winBoxes[0]==6 && winBoxes[2]==8){
                graphicsContext.strokeLine(10,165,170,165);

        }
        else if(winBoxes[0]==0 && winBoxes[2]==6){
                graphicsContext.strokeLine(10,10,10,170);
            }
        else if(winBoxes[0]==1 && winBoxes[2]==7){
                graphicsContext.strokeLine(60,10,60,170);
            }
        else if(winBoxes[0]==2 && winBoxes[2]==8){
                graphicsContext.strokeLine(165,10,165,170);
            }
        else if(winBoxes[0]==2 && winBoxes[2]==6){
                graphicsContext.strokeLine(165,10,10,170);
            }


    }

    @Contract(pure = true)
    private int[] sort(int[] win){
        int min=win[0];
        int temp;
        for(int i=1;i<win.length;i++){
            if(min>win[i]){
                temp=win[i];
                win[i]=min;
                min=temp;
            }
        }
        return win;
    }

}
