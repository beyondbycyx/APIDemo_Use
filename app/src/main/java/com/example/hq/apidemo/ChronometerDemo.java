package com.example.hq.apidemo;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

/*
* 计时器：可以设置它显示的格式，和启动，停止，重新开始和开始的时间
* */
public class ChronometerDemo extends AppCompatActivity {

    private Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer_demo);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                CharSequence text = chronometer.getText();
                Toast.makeText(ChronometerDemo.this,text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onstart(View view) {
        chronometer.start();
    }

    public void onstop(View view) {
        chronometer.stop();
    }

    //这里设置的时间是手机从开机启动+睡眠时间的时间值，效果是显示时是00：00 开始的
    public void onreset(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    public void setFormat(View view) {
        chronometer.setFormat("我换个是格式啦 %s");
    }


    public void clearFormat(View view) {
        chronometer.setFormat(null);
    }
}
