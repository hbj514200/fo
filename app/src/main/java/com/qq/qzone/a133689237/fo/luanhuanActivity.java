package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

public class luanhuanActivity extends Activity {

    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_luanhuan);

        layout = (RelativeLayout) findViewById(R.id.luanhuan_layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try { Thread.sleep(150); } catch (Exception e) { }
                            finish();
                        }
                    }).start();
                }
                return true;
            }
        });
    }

}
