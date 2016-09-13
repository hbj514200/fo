package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class juanzengActivity extends Activity {

    private Button juanzengButton;
    private Button zhifubaobutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_juanzeng);

        juanzengButton = (Button) findViewById(R.id.zhifubao_button);
        juanzengButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                    setContentView(R.layout.activity_juanzeng2);
                    juanzengButton = (Button) findViewById(R.id.zhifubao_button);
                    zhifubaobutton = (Button) findViewById(R.id.paypal_button);

                    juanzengButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            class_zhifu.topaypal(juanzengActivity.this);
                        }
                    });

                    zhifubaobutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            class_zhifu.toAliPayScan(juanzengActivity.this);
                        }
                    });


                }
                return false;
            }

        });
    }

}

