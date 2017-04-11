package com.qq.qzone.a133689237.fo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;

public class fankuiActivity extends AppCompatActivity {

    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fankui);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contentEditText = (EditText) findViewById(R.id.fankui_content);
        Button button = (Button) findViewById(R.id.fankui_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!contentEditText.getText().toString().equals(""))
                tijiao();
            }
        });
    }

    private void tijiao(){
        String content_text = contentEditText.getText().toString();
        if(content_text.equals("")){
            warning_dialog();
        } else {
            Firebase mFireRef = new Firebase("https://nianfo-8afe9.firebaseio.com/feedback");
            mFireRef.push().setValue(contentEditText.getText().toString());
            thank_dialog();
        }

    }

    private void warning_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(fankuiActivity.this);
        builder.setTitle(getResources().getString(R.string.tishi));
        builder.setMessage(getResources().getString(R.string.qinanyaoqiu));
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.hao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void thank_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(fankuiActivity.this);
        builder.setTitle(getResources().getString(R.string.ganxie));
        builder.setMessage(getResources().getString(R.string.feichang));
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.hao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });
        builder.create().show();
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

}
