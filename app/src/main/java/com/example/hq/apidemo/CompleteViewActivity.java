package com.example.hq.apidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class CompleteViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_view);
        AutoCompleteTextView edit1 = (AutoCompleteTextView) findViewById(R.id.edit_query1);
        AutoCompleteTextView edit2 = (AutoCompleteTextView) findViewById(R.id.edit_query2);
        AutoCompleteTextView edit3 = (AutoCompleteTextView) findViewById(R.id.edit_query3);

        String[] strs={"abc","bcd","cde","hugo","hugo1","hugo2","hugo3","hugo4","hugo5","hugo6"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.item_autotext,strs);

        edit1.setAdapter(adapter);
        edit1.setThreshold(1);
        edit1.setCompletionHint("right!");
        edit2.setAdapter(adapter);
        edit3.setAdapter(adapter);

    }


    public void chronometer(View view) {
        startActivity(new Intent(this,ChronometerDemo.class));
        finish();
    }
}
