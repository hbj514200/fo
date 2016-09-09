package com.qq.qzone.a133689237.fo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import com.firebase.client.Firebase;
import net.youmi.android.AdManager;

public class welcome extends Activity {

    private ImageView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mText = (ImageView) findViewById(R.id.welcome_text);

        tiaozhuan();
        startAnimation();
    }

    private void tiaozhuan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                    startActivity(new Intent(welcome.this, jishuActivity.class));
                    finish();
                } catch (Exception e){
                    startActivity(new Intent(welcome.this, jishuActivity.class));
                    finish();
                }
            }
        }).start();
    }

    private void startAnimation() {

        ObjectAnimator TeoveAnimator = ObjectAnimator
        .ofFloat(mText, "translationY", mText.getBottom()+50, mText.getBottom())
        .setDuration(1700);
        ObjectAnimator TealpAnimator = ObjectAnimator.ofFloat(mText, "alpha", 0.3f, 1f)
        .setDuration(1700);

        TeoveAnimator.start();
        TealpAnimator.start();
        }

}
