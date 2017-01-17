package nia.dxball.android;

import android.graphics.Canvas;

public class Bar {
    float barTop,barLeft,barWidth,barHeight,barRight,barBottom;
    Canvas canvas = new Canvas();
    int x,y;


    Bar(float left, float top, float width, float height){
        barLeft = left;
        barTop = top;
        barWidth = width;
        barHeight = height;
        barRight = barLeft+barWidth;
        barBottom = barTop+barHeight;
    }


    public float getLeft() {
        return barLeft;
    }


    public float getTop() {
        return barTop;
    }
    
    public float getRight() {
		return barRight;
	}
    
    public float getBottom() {
		return barBottom;
	}
    
    public float getBarWidth() {
		return barWidth;
	}
    
    public float getBarHeight() {
		return barHeight;
	}
    
    public void moveBar(float speed, boolean moveLeft){
        /*if(leftPos==true){
            if(MainCanvas.checkWidth>=barLeft+barWidth) {
                barLeft += 5;
                barRight += 5;
            }
            leftPos = false;
        }
        else if(rightPos==true){
            if(0<=barLeft) {
                //if()
                barLeft -= 5;
                barRight -= 5;
            }
            rightPos = false;
        }
        else{
        	if(MainCanvas.checkWidth<=barLeft+barWidth){
        		barLeft -= 5;
                barRight -= 5;
        	}
        	if(0>=barLeft){
        		barLeft += 5;
                barRight += 5;
        	}
        }*/
    	if(moveLeft)
    		speed = -speed;
    	barLeft += speed;
		barRight += speed;
    	

   }


}
