package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class guanyuzuozhe extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guanyuzuozhe);

        Button button = (Button) findViewById(R.id.guanyuzuozhe_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(guanyuzuozhe.this, juanzengActivity.class));
                finish();
            }
        });
    }

}
