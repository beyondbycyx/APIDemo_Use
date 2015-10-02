package com.example.hq.apidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.viewfilpper);
        flipper.setInAnimation(this,R.anim.in_l2r_flipper);
        flipper.setOutAnimation(this, R.anim.out_l2r_flipper);
        flipper.startFlipping();

    }

    public void onCompleteView(View view) {
        startActivity(new Intent(this, CompleteViewActivity.class));
        finish();
    }
}
