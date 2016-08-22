package com.qq.qzone.a133689237.fo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import com.firebase.client.Firebase;

public class welcome extends Activity {

    private ImageView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        Firebase.setAndroidContext(this);

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
                    startActivity(new Intent(welcome.this, jishuActivity.class));
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
