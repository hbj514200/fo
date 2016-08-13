package com.qq.qzone.a133689237.fo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class jishuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jishu);
        getSupportActionBar().hide();

        tiaozhuan();
    }

    private void tiaozhuan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(jishuActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e){ }
            }
        }).start();
    }

}
