package com.example.administrator.game2048;

import android.view.Gravity;
import android.widget.FrameLayout;
import android.content.Context;
import android.widget.TextView;
/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class Card extends FrameLayout {

    public Card(Context context){
        super(context);


        label=new TextView(getContext());
        label.setTextSize(32);
        //设置放卡片的容器放在中间
        label.setGravity(Gravity.CENTER);
        //添加卡片的背景
        label.setBackgroundColor(0x33ffffff);

        LayoutParams lp=new LayoutParams(-1,-1);
        //卡片和卡片之间的间隔
        lp.setMargins(10,10,0,0);
        addView(label,lp);
        setNum(0);

    }

    private int num=0;

    public int getNum(){
        return num;
    }

    public void setNum(int num){
        this.num=num;
        if(num<=0)
            label.setText("");
        else
            label.setText(num + "");
    }

    public boolean equals(Card o){
        return getNum()==o.getNum();
    }



    private TextView label;

}
