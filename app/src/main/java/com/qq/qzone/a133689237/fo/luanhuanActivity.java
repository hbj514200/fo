package com.qq.qzone.a133689237.fo;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class luanhuanActivity extends Activity {

    private RelativeLayout layout;
    private ImageView image;
    private int index = 1;
    private Timer timer = new Timer();
    private ObjectAnimator imalp = null;
    private Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1 :
                    image.setBackgroundResource( getIndex() );
                    imalp.start();
                    break;
                case 2 :
                    layout.setBackgroundResource( getIndex() );
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_luanhuan);

        layout = (RelativeLayout) findViewById(R.id.luanhuan_layout);
        image = (ImageView) findViewById(R.id.luanhuan_tupian);
        imalp = ObjectAnimator.ofFloat(image, "alpha", 0.1f, 1f).setDuration(2000);

        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            }
        });

        dinshi();
    }

    private int getIndex(){
        int[] suiji = {
                R.drawable.jishu_1,     R.drawable.jishu_1,
                R.drawable.jishu_2,     R.drawable.jishu_2,
                R.drawable.jishu_3,     R.drawable.jishu_3,
                R.drawable.jishu_4,     R.drawable.jishu_4,
                R.drawable.jishu_5,     R.drawable.jishu_5,
                R.drawable.jishu_6,     R.drawable.jishu_6,
                R.drawable.jishu_7,     R.drawable.jishu_7,
                R.drawable.jishu_8,     R.drawable.jishu_8
        };
        index++;    index%=16;
        return suiji[index];
    }

    private void dinshi(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();    message.what = 1;
                myHandler.sendMessage(message);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try { Thread.sleep(3000); } catch (Exception e) { }
                        Message message = new Message();    message.what = 2;
                        myHandler.sendMessage(message);
                    }
                }).start();
            }
        }, 10000, 10000);
    }

}
