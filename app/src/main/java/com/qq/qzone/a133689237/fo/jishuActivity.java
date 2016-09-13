package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.google.firebase.analytics.FirebaseAnalytics;

public class jishuActivity extends Activity {

    private ImageView image;
    private TextView tianshu;
    private SharedPreferences pre;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_jishu);

        image = (ImageView) findViewById(R.id.jishu_tupian);
        tianshu = (TextView) findViewById(R.id.use_tianshu);
        pre = getSharedPreferences("mydata", Activity.MODE_PRIVATE);

        suijiPicture();
        String st = getResources().getString(R.string.ninyi) + " " + pre.getInt("tianshu",0)+ " "+getResources().getString(R.string.tian);
        tianshu.setText(st);

        tiaozhuan();
        Firebase.setAndroidContext(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    private void tiaozhuan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {   Thread.sleep(2500);  } catch (Exception e){   }
                startActivity(new Intent(jishuActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }).start();
    }

    private void suijiPicture(){
        int[] suiji = {
                R.drawable.jishu_1,
                R.drawable.jishu_2,
                R.drawable.jishu_3,
                R.drawable.jishu_4,
        };
        int index = 0;
        if (Math.random()*10<3)     index = 0;
        if (Math.random()*10<3)     index = 1;
        if (Math.random()*10<3)     index = 2;
        if (Math.random()*10<3)     index = 3;
        image.setImageResource(suiji[index]);
    }

}
