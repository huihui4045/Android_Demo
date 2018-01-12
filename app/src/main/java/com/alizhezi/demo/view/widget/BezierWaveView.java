package com.alizhezi.demo.view.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by gavin
 * Time 2018/1/12  11:40
 * Email:molu_clown@163.com
 *
 * 简单的贝塞尔曲线  波浪效果
 */

public class BezierWaveView extends View {

    private int width=0;

    private  int height=0;

    private int baseLine=0;

    private int waveHeight=100;

    private int waveWidth;

    private float offset=0f;

    private Paint mPaint;


    public BezierWaveView(Context context) {
        super(context);

        initView();
    }

    private void initView() {

        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path=new Path();


        int itemWidth=waveWidth/2;

        path.moveTo(-itemWidth*3,baseLine);

       for (int i=-3;i<2;i++){

           int startX=i*itemWidth;

           path.quadTo(startX+itemWidth/2+offset,
                   getWaveHeigh(i),
                   startX+itemWidth+offset,
                   baseLine);
       }


        path.lineTo(width,height);

        path.lineTo(0,height);

        path.close();


        canvas.drawPath(path,mPaint);


    }

    //奇数峰值是正的，偶数峰值是负数
    private int getWaveHeigh(int num){
        if(num % 2 == 0){
            return baseLine + waveHeight;
        }
        return baseLine - waveHeight;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        width=getMeasuredWidth();

        height=getMeasuredHeight();

        waveWidth=width;
        baseLine=height/2;

        updateXContral();


    }

    private void updateXContral(){


        final ValueAnimator animator=ValueAnimator.ofFloat(0,waveWidth);


        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

               float animatorValue= (float)animation.getAnimatedValue();


               offset=animatorValue;

               postInvalidate();
            }
        });



        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);

        animator.start();

    }
}
