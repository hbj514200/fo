package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class juanzengActivity extends Activity {

    private Button juanzengButton;
    private Button zhifubaobutton;
    SharedPreferences pre;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_juanzeng);
        final SharedPreferences pre = getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        editor = pre.edit();

        juanzengButton = (Button) findViewById(R.id.zhifubao_button);
        juanzengButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    if (pre.getInt("tianshu",0) > 1)
                        setContentView(R.layout.activity_juanzeng2);

                }
                return false;
            }

        });
    }

}

