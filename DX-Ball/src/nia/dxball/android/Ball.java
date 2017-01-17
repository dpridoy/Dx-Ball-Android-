package nia.dxball.android;

import android.graphics.Canvas;

public class Ball {
    private float gameOver=0;
    private float x;
    private float y;
    private float r;
    private float dx;
    private float dy;
    
    public  Ball(float xx,float yy,float radius){    
        x=xx;
        y=yy;
        r=radius;
        dx=0;
        dy=0;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY() {
        return y;
    }


    public float getRadius() {
        return r;
    }


    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }


    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }
    
    public float getGameOver(){
        return gameOver;
    }
    
    public void move(){
        x=x+dx;
        y=y+dy;
    }

    public void ballBoundaryCheck(Canvas canvas) {

        if((this.y-this.r)>=canvas.getHeight()){
            MainCanvas.LIFE -= 1;
            MainCanvas.newLife = true;
        }
        
        if(MainCanvas.LIFE==0)
        	MainCanvas.GAMEOVER = true;
        
        if((this.x+this.r)>=canvas.getWidth() || (this.x-this.r)<=0){
            this.dx = -this.dx;
        }
        if( (this.y-this.r)<=0){
            this.dy = -this.dy;
        }
        
        
    }
    
}
