package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {
    private ListView mListView;
    ArrayAdapter adapter;

    private Button juanButton;
    private SharedPreferences pre;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pre = getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        editor = pre.edit();
        juanButton = (Button) findViewById(R.id.juanzeng_button);
        mListView = (ListView) findViewById(R.id.setting_listview);
        juanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, juanzengActivity.class));
            }
        });

        Map<String, String> keyValuePair0 = new HashMap<String, String>();
        keyValuePair0.put("name", getResources().getString(R.string.bofangmoshi));
        keyValuePair0.put("shuomin", getResources().getString(R.string.shuomin1));
        Map<String, String> keyValuePair1 = new HashMap<String, String>();
        keyValuePair1.put("name", getResources().getString(R.string.chongzhitianshu));
        keyValuePair1.put("shuomin", getResources().getString(R.string.shuomin2));
        Map<String, String> keyValuePair2 = new HashMap<String, String>();
        keyValuePair2.put("name", getResources().getString(R.string.yijianfankui));
        keyValuePair2.put("shuomin", getResources().getString(R.string.ganxieninti));
        Map<String, String> keyValuePair3 = new HashMap<String, String>();
        keyValuePair3.put("name", getResources().getString(R.string.tuijianqumu));
        keyValuePair3.put("shuomin", getResources().getString(R.string.tuijianqumu));
        Map<String, String> keyValuePair4 = new HashMap<String, String>();
        keyValuePair4.put("name", getResources().getString(R.string.guanyuruanjian));
        keyValuePair4.put("shuomin", getResources().getString(R.string.shuomin4));

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(keyValuePair0);
        list.add(keyValuePair1);
        list.add(keyValuePair2);
        list.add(keyValuePair3);
        list.add(keyValuePair4);
        ListAdapter adapter = new SimpleAdapter(this, list,
                R.layout.setting_list_item, new String[] { "name",
                "shuomin" }, new int[] { R.id.text1,
                R.id.text2 });
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        bofangmoshi();
                        break;
                    case 1 :
                        startActivity(new Intent(SettingsActivity.this, chongzhiActivity.class));
                        break;
                    case 2 :
                        startActivity(new Intent(SettingsActivity.this, fankuiActivity.class));
                        break;
                    case 3 :
                        startActivity(new Intent(SettingsActivity.this, tuijianqumu_Activity.class));
                        break;
                    case 4 :
                        startActivity(new Intent(SettingsActivity.this, guanyuActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
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

    private void bofangmoshi(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle(getResources().getString(R.string.qinxuanze));
        final String[] sex = {getResources().getString(R.string.danquxunhuan), getResources().getString(R.string.shunxvbofang)};
        builder.setSingleChoiceItems(sex, pre.getInt("bofangmoshi",0), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                editor.putInt("bofangmoshi", which);
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.quedin), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                editor.commit();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.quxiao), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
