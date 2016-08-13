package com.qq.qzone.a133689237.fo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class welcome extends AppCompatActivity {

    private ImageView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        mText = (ImageView) findViewById(R.id.welcome_text);

        tiaozhuan();
        startAnimation();
    }

    private void tiaozhuan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(welcome.this, jishuActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){ }
            }
        }).start();
    }

    private void startAnimation() {

        ObjectAnimator TeoveAnimator = ObjectAnimator
        .ofFloat(mText, "translationY", mText.getBottom()+50, mText.getBottom())
        .setDuration(1200);
        ObjectAnimator TealpAnimator = ObjectAnimator.ofFloat(mText, "alpha", 0.1f, 1f)
        .setDuration(1200);

        TeoveAnimator.start();
        TealpAnimator.start();
        }

}
