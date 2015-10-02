package com.example.hq.apidemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 *
 * View animation four ways
 * */
public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        //1. ViewPropertyAnimator	animate()
        //iv.animate().translationX(50).setStartDelay(1000).start();
        //iv.animate().translationXBy(50).setStartDelay(1000).start();
        //2.Animation .xml
        AnimationSet animation = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.anim_set);
        //iv.setAnimation(animation);
        //3.Animator .xml
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_set);
        animator.setTarget(iv);
        animator.start();

    }


    public void onSpinner(View view) {
        startActivity(new Intent(MainActivity.this,SpinnerActivity.class));
        finish();
    }

    public void dataPicker(View view) {
        startActivity(new Intent(MainActivity.this,DataPickerActivity.class));
    }

    public void datePicker2(View view) {
        startActivity(new Intent(MainActivity.this,DataPickerActivity2.class));
    }
}
