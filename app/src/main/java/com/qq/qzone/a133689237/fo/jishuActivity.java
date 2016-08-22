package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class jishuActivity extends Activity {

    private ImageView image;
    private TextView tianshu;
    private TextView fenzhong;
    private SharedPreferences pre;
    private Handler myHandler = new Handler(){
        public void handleMessage(Message msg) {
            startActivity(new Intent(jishuActivity.this, MainActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_jishu);

        image = (ImageView) findViewById(R.id.jishu_tupian);
        tianshu = (TextView) findViewById(R.id.use_tianshu);
        fenzhong = (TextView) findViewById(R.id.use_fenzhong);
        pre = getSharedPreferences("mydata", Activity.MODE_PRIVATE);

        String st = "您已于此诵佛 " + pre.getInt("tianshu",0)+ " 天";
        tianshu.setText(st);
        fenzhong.setText("佛乐悠扬");

        suijiPicture();
        tiaozhuan();
    }

    private void tiaozhuan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    myHandler.sendMessage(new Message());
                } catch (Exception e){ }
            }
        }).start();
    }

    private void suijiPicture(){
        int[] suiji = {
                R.drawable.jishu_1,
                R.drawable.jishu_2,
                R.drawable.jishu_3,
                R.drawable.jishu_4,
                R.drawable.jishu_5,
                R.drawable.jishu_6,
                R.drawable.jishu_7,
                R.drawable.jishu_8
        };
        int index = (int) ( Math.random()*8 - 1 );
        if(index < 0)   index = 7;
        image.setImageResource(suiji[index]);
    }

}
