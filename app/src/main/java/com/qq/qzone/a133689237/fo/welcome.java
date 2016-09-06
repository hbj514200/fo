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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        mText = (ImageView) findViewById(R.id.welcome_text);

        startAnimation();
        tiaozhuan();
        Firebase.setAndroidContext(this);
        AdManager.getInstance(welcome.this).init("3ec6d67a552212e1", "5a9a483820d01818", true, true);
    }

    private void tiaozhuan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2400);
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
        .setDuration(1200);
        ObjectAnimator TealpAnimator = ObjectAnimator.ofFloat(mText, "alpha", 0.3f, 1f)
        .setDuration(1100);

        TeoveAnimator.start();
        TealpAnimator.start();
        }

}
