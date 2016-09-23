package com.example.administrator.customview10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/9/23.
 */
public class CustomView extends View {
    Paint paint;
    Paint shortLinePaint;
    Paint ciclePaint;
    Paint numberPaint;
    Paint secondsPaint;
    Paint minutePaint;
    Paint hourPaint;

    //在Activity中动态生成时调用
    public CustomView(Context context) {
        this(context, null);
    }

    //在XML文件中生成时调用，第二个参数为设置属性的集合
    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //最后一个参数为默认的风格，由系统调用（举例：button在不写东西的时候还有灰色底）
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        //设置画笔的边界锯子，true为
        paint.setAntiAlias(true);
        //设置画笔的颜色
        paint.setColor(Color.GREEN);
        //设置画笔的宽度
        paint.setStrokeWidth(8);
        //设置画笔的形状，STROKE为空心
        paint.setStyle(Paint.Style.STROKE);

        //短边的画笔
        shortLinePaint = new Paint();
        shortLinePaint.setColor(Color.GREEN);
        shortLinePaint.setAntiAlias(true);
        shortLinePaint.setStyle(Paint.Style.FILL);
        shortLinePaint.setStrokeWidth(8);

        ciclePaint = new Paint();
        ciclePaint.setColor(Color.RED);
        ciclePaint.setAntiAlias(true);
        ciclePaint.setStyle(Paint.Style.FILL);

        numberPaint = new Paint();
        numberPaint.setColor(Color.RED);
        numberPaint.setTextSize(30);
        numberPaint.setTextAlign(Paint.Align.CENTER);//文本已xy为中点描绘，默认是已x为左，y为基线描绘

        secondsPaint = new Paint();
        secondsPaint.setColor(Color.BLUE);
        secondsPaint.setStrokeWidth(3);

        minutePaint = new Paint();
        minutePaint.setStrokeWidth(6);
        minutePaint.setColor(Color.DKGRAY);

        hourPaint = new Paint();
        hourPaint.setStrokeWidth(9);
        hourPaint.setColor(Color.BLACK);
    }

    //super.onDraw(canvas);调用父类的绘图方法
    //canvas 画布 ，一般放一个bitmap对象进去，吧画的效果加载到bitmap上
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("bigname_log", "onDraw: *****************");
        int width = getWidth();
        int height = getHeight();
        int radius = width / 2 - 4;//半径
        canvas.drawCircle(width / 2, height / 2, radius, paint);
        canvas.drawCircle(width / 2, height / 2, 20, ciclePaint);
        canvas.drawLine(width / 2, 0, width / 2, 30, shortLinePaint);
        for (int i = 1; i <= 12; i++) {
            canvas.save();
            canvas.rotate(30 * i, width / 2, width / 2);
            canvas.drawLine(width / 2, 0, height / 2, 30, shortLinePaint);
            canvas.drawText(i + "", width / 2, 50, numberPaint);
            canvas.restore();
        }

        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);


        canvas.save();
        canvas.rotate(hour * 30 + minute / 2, width / 2, width / 2);
        canvas.drawLine(width / 2, width / 2 + 25, width / 2, width / 2 - 80, hourPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(minute * 6, width / 2, width / 2);
        canvas.drawLine(width / 2, width / 2 + 45, width / 2, width / 2 - 120, minutePaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(second * 6, width / 2, width / 2);
        canvas.drawLine(width / 2, width / 2 + 60, width / 2, width / 2 - 140, secondsPaint);
        canvas.restore();

        postInvalidateDelayed(1000);
    }
    /*
            * measureSpec是一个32位的int值，前两位代表测量的模式，后30为代表测量的大小，使用位计算的原因是提高和优化效率
            *
            * 模式分为如下三种：
            * EXACTYL 精确的，当为match_parent，或者指定值100dp...
            * AT_MOST 模糊的，当为wrap_content时，随着控件的子空间变化而变化，但不会超过父类
            * UNSPECIFIED 无规则的，这样的view像多大就多达大
            * */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(customWidth(widthMeasureSpec), customHeight(heightMeasureSpec));
    }

    private int customWidth(int widthMeasureSpec) {
        int width = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        Log.d("bigname_log", "customHeight: " + size);
        if (mode == MeasureSpec.EXACTLY) {
//            height = size; //精确的模式下，等于用户指定值
            width = size;
        } else {
            width = 200; //无规则模式下，等于200dp
            if (mode == MeasureSpec.AT_MOST) {
                width = Math.min(width, size); //最大值模式下，选取小的作为值
            }
        }
        return width;
    }

    private int customHeight(int heightMeasureSpec) {
        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("bigname_log", "customHeight: " + size);
        if (mode == MeasureSpec.EXACTLY) {
//            height = size; //精确的模式下，等于用户指定值
            height = size;
        } else {
            height = 200; //无规则模式下，等于200dp
            if (mode == MeasureSpec.AT_MOST) {
                height = Math.min(height, size); //最大值模式下，选取小的作为值
            }
        }
        return height;
    }

}
