package com.example.hq.apidemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DataPickerActivity extends AppCompatActivity {

    private static final int DIALOG_TIME = 1;
    private static final int DIALOG_DATE = 0;
    private int myear;
    private int month;
    private int day;
    private int hour;
    private int minuteOfHour;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_picker);

        tv = (TextView) findViewById(R.id.tv);
        //获取日历
        Calendar cal = Calendar.getInstance();

        myear = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR);
        minuteOfHour = cal.get(Calendar.MINUTE);
        //更新textview的数据
        updateTime();

    }

    private void updateTime() {

        tv.setText(new StringBuilder().append(myear+"-"+(month+1)+"-"+day).append(" "+pad(hour)+":"+pad(minuteOfHour)));
    }

    private String pad(int i) {

        return i>9? i+"":"0"+i;
    }

    //显示timepickerdialog
    public void onTime(View view) {

      showDialog(DIALOG_TIME);
    }
    //显示datepickerdialog
    public void onDate(View view) {
        showDialog(DIALOG_DATE);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DATE:
                //创建date的dialog
                DatePickerDialog.OnDateSetListener dateListeer = new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        myear = year;
                        month = monthOfYear;
                        day = dayOfMonth;
                        updateTime();
                    }
                };
               return  new DatePickerDialog(DataPickerActivity.this, dateListeer, myear, month, day);

            case DIALOG_TIME:
                //创建time的dialog
                TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour = hourOfDay;
                        minuteOfHour = minute;
                        updateTime();
                    }
                };
                return new TimePickerDialog(DataPickerActivity.this, timeListener, hour, minuteOfHour, false);

        }
       return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        //在这里设置窗口将要显示的时间和日期为当前的时间和日期
        switch (id) {
            case DIALOG_TIME:
                ((TimePickerDialog)dialog).updateTime(hour,minuteOfHour);
                break;
            case DIALOG_DATE:
                ((DatePickerDialog) dialog).updateDate(myear, month, day);
                break;
        }
    }
}
