package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class chongzhiActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText = (EditText) findViewById(R.id.chongzhi_edittext);
        button = (Button) findViewById(R.id.chongzhi_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")){
                    queren_dialog();
                }
                else
                    warning_dialog();
            }
        });
    }

    private void chuli(){
        SharedPreferences mySharedPreferences= getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt("tianshu", Integer.valueOf(editText.getText().toString()).intValue());
        editor.commit();
    }

    private void queren_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(chongzhiActivity.this);
        builder.setTitle(getResources().getString(R.string.zhuyi));
        builder.setMessage(getResources().getString(R.string.zhezhen));
        builder.setCancelable(true);
        builder.setNegativeButton(getResources().getString(R.string.quxiao), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.hao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chuli();
                ok_dialog();
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void warning_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(chongzhiActivity.this);
        builder.setTitle(getResources().getString(R.string.tishi));
        builder.setMessage(getResources().getString(R.string.qinan));
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.hao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void ok_dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(chongzhiActivity.this);
        builder.setTitle(getResources().getString(R.string.tishi));
        builder.setMessage(getResources().getString(R.string.chongzhi));
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
