package com.example.administrator.customview10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private CustomView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = ((CustomView) findViewById(R.id.view1));
        //这里打印0，0是因为还没有去执行onDraw()方法绘制,oncreat()方法是进行运行前的准备工作
        Log.d("bigname_log", "onCreate: "+ view.getWidth()+","+ view.getHeight());
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("bigname_log", "onResume: "+view.getWidth()+","+view.getHeight());
    }
}
