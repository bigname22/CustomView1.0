package com.example.administrator.customview10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/23.
 */
public class CustomView2 extends TextView {
    Paint outpaint ;
    private Paint intpaint;

    public CustomView2(Context context) {
        this(context,null);
    }

    public CustomView2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        outpaint = new Paint();
        intpaint = new Paint();

        outpaint.setStrokeWidth(2);
        outpaint.setColor(Color.RED);

        intpaint.setStrokeWidth(4);
        intpaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getWidth(),getHeight(),outpaint);
       canvas.drawRect(20, 20, getWidth()-20, getHeight()-20, intpaint);
        super.onDraw(canvas);
    }
}
