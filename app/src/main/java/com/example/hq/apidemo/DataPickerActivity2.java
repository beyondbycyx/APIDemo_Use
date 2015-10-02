package com.example.hq.apidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DataPickerActivity2 extends AppCompatActivity {

    private TextView tv;
    private TimePicker time;
    private DatePicker date;
    private Integer hour;
    private Integer minuteOfDay;
    private int myear;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_picker2);
        tv = (TextView) findViewById(R.id.tv);
        time = (TimePicker) findViewById(R.id.time);
        date = (DatePicker) findViewById(R.id.date);
        //更新当前的数据
        hour = time.getCurrentHour();
        minuteOfDay = time.getCurrentMinute();

        myear = date.getYear();
        month = date.getMonth();
        day = date.getDayOfMonth();
        update();
        //设置监听
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minuteOfDay = minute;
                update();
            }
        });
        DatePicker.OnDateChangedListener dateListener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myear = year;
                month = monthOfYear;
                day = dayOfMonth;
                update();
            }
        };
        date.init(date.getYear(),date.getMonth(),date.getDayOfMonth(),dateListener);
   }

    private void update() {
        tv.setText(new StringBuilder().append(myear +"-"+(month+1)+"-"+ day).append(" " + pad(hour) + ":" + pad(minuteOfDay)));

    }
    private String pad(int i) {

        return i>9? i+"":"0"+i;
    }


}
