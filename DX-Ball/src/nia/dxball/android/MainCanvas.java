package nia.dxball.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

class MainCanvas extends View{

	int level;
    public static int LIFE;
    public static boolean newLife;
    public static boolean GAMEOVER;
    Paint paint;
    float brickX=0, brickY=0;
    int score=0;
    Canvas canvas;
    Ball ball;
    Bar bar;
    float barWidth;
    float ballSpeed;
    float barSpeed;
    boolean barMoveLeft;
    boolean leftPos,rightPos,first;
    public static int checkWidth=0;
    
    ArrayList<Bricks> bricks=new ArrayList<Bricks>();
 
    public MainCanvas(Context context) {
        super(context);
		paint=new Paint();
        level = 1;
        LIFE = 3;
        first = true;
        GAMEOVER = false;
        newLife = true;
        barMoveLeft = false;
        barSpeed = 4;
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	this.canvas = canvas;
    	int col;
        brickX = canvas.getWidth()/7;
        brickY = (canvas.getHeight()/15)*2;
        if(first) {
        	
            first=false;    
            if(level==1){
            for(int i=0; i<15; i++){
                
                if(brickX>=canvas.getWidth()-(canvas.getWidth()/7)*2) {
                    brickX = canvas.getWidth()/7;
                    brickY += canvas.getHeight()/15;
                }
                if(i%2==0)
                    col = Color.CYAN;
                else
                    col = Color.BLUE;
                bricks.add(new Bricks(brickX,brickY,brickX+canvas.getWidth()/7,brickY+canvas.getHeight()/15,col));
                brickX+=canvas.getWidth()/7;
            }
            }
            else if(level==2){
                for(int i=0; i<19; i++){
                    
                    if(brickX>=canvas.getWidth()-canvas.getWidth()/7) {
                        brickX = 0;
                        brickY += (canvas.getHeight()/15)*2;
                    }
                    if(i%2==0)
                        col = Color.GREEN;
                    else
                        col = Color.YELLOW;
                    bricks.add(new Bricks(brickX,brickY,brickX+canvas.getWidth()/7,brickY+canvas.getHeight()/15,col));
                    brickX+=(canvas.getWidth()/7)*2;
                }
             }
            
            else if(level==3){
                for(int i=0; i<15; i++){
                    
                    if(brickX>=canvas.getWidth()-(canvas.getWidth()/7)*2) {
                        brickX = canvas.getWidth()/7;
                        brickY += canvas.getHeight()/15;
                    }
                    if(i%2==0)
                        col = Color.GREEN;
                    else
                        col = Color.CYAN;
                    bricks.add(new Bricks(brickX,brickY,brickX+canvas.getWidth()/7,brickY+canvas.getHeight()/15,col));
                    brickX+=canvas.getWidth()/7;
                }
                
                brickX = 0;
                brickY = (canvas.getHeight()/15)*8;
                col = Color.RED;
                
                for(int i=0; i<14; i++){
                	if(brickX>=canvas.getWidth()-canvas.getWidth()/7) {
                        brickX = 0;
                        brickY += canvas.getHeight()/15;
                    }
                    bricks.add(new Bricks(brickX,brickY,brickX+canvas.getWidth()/7,brickY+canvas.getHeight()/15,col));
                    brickX+=canvas.getWidth()/7;
                }
             }
            
            else
            	GAMEOVER = true;
           
            //new bar
            barWidth = canvas.getWidth()/4;
            bar = new Bar(canvas.getWidth()/2-(barWidth/2), canvas.getHeight()-20, barWidth, 20);
            checkWidth = canvas.getWidth();	
   
        }
        
        if(newLife){
        	newLife = false;
            //new ball
            ballSpeed = 5;
            ball=new Ball(canvas.getWidth()/2,canvas.getHeight()-50,20);
            ball.setDx(ballSpeed);
            ball.setDy(-ballSpeed);
        }
        
        if(bricks.size()==0){
        	level += 1;
        	first = true;
        	newLife = true;
        }
        
        //background
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);   
        
        //Ball
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ball.getX(), ball.getY(), ball.getRadius(), paint);
        
        //text
        
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Score: "+score,20,40,paint);
        
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: "+LIFE,canvas.getWidth()-110,40,paint);

        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("LEVEL "+level,canvas.getWidth()/2-60,40,paint);
        
        //Bar
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(bar.getLeft(), bar.getTop(), bar.getRight(), bar.getBottom(), paint);
       
        //bricks
        for(int i=0;i<bricks.size();i++){
            canvas.drawRect(bricks.get(i).getLeft(),bricks.get(i).getTop(),bricks.get(i).getRight(),bricks.get(i).getBottom(),bricks.get(i).getPaint());
        }
        
        //gameover
        
        if(GAMEOVER){
        	paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);   
            
            paint.setColor(Color.RED);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("GAME OVER",canvas.getWidth()/2-110,canvas.getHeight()/2,paint);
            canvas.drawText("FINAL SCORE: "+score,canvas.getWidth()/2-150,canvas.getHeight()/2+60,paint);
            GAMEOVER = false;
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	((GameCanvas)getContext()).finish();
        }
        
        //collision
        
        this.ballBrickCollision(bricks,ball,canvas);
        this.ballBarCollision(bar,ball, canvas);
        ball.ballBoundaryCheck(canvas);
        barBoundaryCheck(canvas);
        //movement
        ball.move();
        bar.moveBar(barSpeed,barMoveLeft);
       
    }
    
    
    public void barBoundaryCheck(Canvas canvas) {
		if(bar.getRight()>=canvas.getWidth())
			barMoveLeft = true;
		if(bar.getLeft()<=0)
			barMoveLeft = false;
	}

	public void ballBarCollision(Bar bar,Ball ball,Canvas canvas){
        if(((ball.getY()+ball.getRadius())>=bar.getTop())&&((ball.getY()+ball.getRadius())<=bar.getBottom())&& ((ball.getX())>=bar.getLeft())&& ((ball.getX())<=bar.getRight())) {
            ball.setDy(-(ball.getDy()));

        }

    }
    public void ballBrickCollision(ArrayList<Bricks> br ,Ball ball,Canvas canvas){
    	
        for(int i=0;i<br.size();i++) {
            if (((ball.getY() - ball.getRadius()) <= br.get(i).getBottom()) && ((ball.getY() + ball.getRadius()) >= br.get(i).getTop()) && ((ball.getX()) >= br.get(i).getLeft()) && ((ball.getX()) <= br.get(i).getRight())) {      
            	br.remove(i);
                score+=1;
                ball.setDy(-(ball.getDy()));
            }
            else if(((ball.getY()) <= br.get(i).getBottom()) && ((ball.getY()) >= br.get(i).getTop()) && ((ball.getX() + ball.getRadius()) >= br.get(i).getLeft()) && ((ball.getX() - ball.getRadius()) <= br.get(i).getRight())) {
              br.remove(i);
              score+=1;
               ball.setDx(-(ball.getDx()));
            }
           
        }

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
    
    	float touchx = event.getX();
		float touchy = event.getY();
		if(touchx>=(bar.barRight)){
			/*rightSide = true;
			leftSide = false;*/
			barMoveLeft = false;
		}
		if(touchx<=bar.barLeft){
			/*rightSide = false;
			leftSide = true;*/
			barMoveLeft = true;
		}
		return true;

    }



}
