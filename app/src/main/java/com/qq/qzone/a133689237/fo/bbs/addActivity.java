package com.qq.qzone.a133689237.fo.bbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.qq.qzone.a133689237.fo.R;

public class addActivity extends AppCompatActivity implements View.OnClickListener {
    private static boolean chongfu = false;
    private EditText nameInput;
    private EditText contentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameInput = (EditText) findViewById(R.id.add_nichen);
        contentInput = (EditText) findViewById(R.id.add_content);
        Button button = (Button) findViewById(R.id.fankui_button);
        button.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        String name = nameInput.getText().toString();
        String content = contentInput.getText().toString();
        if(content.length()<1 || content.length()<5 || chongfu)   return;
        Time t = new Time();    t.setToNow();

        Firebase mFireRef = new Firebase("https://nianfo-8afe9.firebaseio.com/bbs");
        mFireRef.push().setValue(new bbsData(name, content, Integer.toString(t.monthDay)));
        Toast.makeText(addActivity.this, "分享成功，功德无量", Toast.LENGTH_LONG).show();
        chongfu = true;
        finish();
    }

}
