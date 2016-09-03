package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class juanzengActivity extends Activity {

    private Button juanzengButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_juanzeng);

        juanzengButton = (Button) findViewById(R.id.zhifubao_button);
        juanzengButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_juanzeng2);
                juanzengButton = (Button) findViewById(R.id.zhifubao_button);
                juanzengButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        class_zhifu.toAliPayScan(juanzengActivity.this);
                    }
                });
            }
        });
    }

}
