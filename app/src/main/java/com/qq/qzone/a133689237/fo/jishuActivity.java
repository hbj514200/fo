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

public class jishuActivity extends Activity {

    private ImageView image;
    private TextView tianshu;
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
        pre = getSharedPreferences("mydata", Activity.MODE_PRIVATE);

        String st = getResources().getString(R.string.ninyi) + " " + pre.getInt("tianshu",0)+ " "+getResources().getString(R.string.tian);
        tianshu.setText(st);

        suijiPicture();
        tiaozhuan();
    }

    private void tiaozhuan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2600);
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
        };
        int index = (int) ( Math.random()*suiji.length - 1 );
        if(index < 0)   index = suiji.length -1 ;
        image.setImageResource(suiji[index]);
    }

}
