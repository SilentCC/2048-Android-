package com.example.administrator.game2048;

import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.content.Context;
import android.util.*;
import android.graphics.*;
import java.util.*;


/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class GameView extends GridLayout {

    //传入相关属性的构造方法
    public GameView(Context context,AttributeSet attrs){
        super(context,attrs);

        initGameView();
    }

    public GameView(Context context){
        super(context);
        initGameView();
    }

    public GameView(Context context,AttributeSet attrs,int defStyle){

        super(context,attrs,defStyle);
        initGameView();

    }
    //初始化界面
    private void initGameView(){
        //指定四列
        setColumnCount(4);
        //设置颜色

        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new View.OnTouchListener(){

            private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX=event.getX()-startX;
                        offsetY=event.getY()-startY;

                        if(Math.abs(offsetX)>Math.abs(offsetY)){

                            if(offsetX<-5){
                             //   System.out.println("left");
                                swipeLeft();

                            }
                            else if(offsetX>5){
                             //  System.out.println("right");
                                swipeRight();
                            }}
                        else {
                            if (offsetY < -5) {
                              //  System.out.println("up");
                                swipeUp();
                            } else if (offsetY > 5) {
                                //System.out.println("down");
                                swipeDown();
                            }
                        }
                        break;

                }

                return true;
            }
        });

    }

    @Override
    //获取手机的宽度和高度，从而计算卡片的长和宽
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth=(Math.min(w,h)-10)/4;

        addCards(cardWidth,cardWidth);
        startGame();
    }
    //添加卡片函数
    private void addCards(int cardWidth,int cardHeight){

        Card c;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                c=new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cardMap[x][y]=c;
            }
        }

    }
    //开启游戏
    private void startGame(){

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                cardMap[i][j].setNum(0);
            }

        }
        addRandomNum();
        addRandomNum();
        addRandomNum();
        addRandomNum();
        addRandomNum();
        addRandomNum();
        addRandomNum();
        addRandomNum();


    }

    private void addRandomNum(){
        emptyPoints.clear();
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                if(cardMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }

            }
        }
        //获取Point;
        Point p= emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardMap[p.x][p.y].setNum(Math.random()>0.1?2:4);

    }

    private void swipeLeft(){
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++ ){
                for(int x1=x+1;x1<4;x1++){
                    if(cardMap[x1][y].getNum()>0){

                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x--;
                            break;
                        }
                        else if(cardMap[x][y].equals(cardMap[x1][y])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x1][y].setNum(0);
                            break;

                        }
                    }
                }
            }
        }
        addRandomNum();

    }

    private void swipeRight(){
        for(int y=0;y<4;y++){
            for(int x=3;x>=0;x-- ){
                for(int x1=x-1;x1>=0;x1--){
                    if(cardMap[x1][y].getNum()>0){

                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x++;
                            break;
                        }
                        else if(cardMap[x][y].equals(cardMap[x1][y])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x1][y].setNum(0);
                            break;

                        }
                    }
                }
            }
        }
        addRandomNum();

    }

    private void swipeUp(){

        for(int x=0;x<4;x++){
            for(int y=0;y<4;y++ ){
                for(int y1=y+1;y1<4;y1++){
                    if(cardMap[x][y1].getNum()>0){

                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y--;
                            break;
                        }
                        else if(cardMap[x][y].equals(cardMap[x][y1])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x][y1].setNum(0);
                            break;

                        }
                    }
                }
            }
        }
        addRandomNum();

    }

    private void swipeDown(){

        for(int x=0;x<4;x++){
            for(int y=3;y>=0;y-- ){
                for(int y1=y-1;y1>=0;y1--){
                    if(cardMap[x][y1].getNum()>0){

                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y++;
                            break;
                        }
                        else if(cardMap[x][y].equals(cardMap[x][y1])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x][y1].setNum(0);
                            break;

                        }
                    }
                }
            }
        }
        addRandomNum();

    }

    //定义二维数组
    private Card[][] cardMap =new Card[4][4];

    private List<Point> emptyPoints = new ArrayList<Point>();



}
