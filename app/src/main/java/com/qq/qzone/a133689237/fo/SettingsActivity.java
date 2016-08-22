package com.qq.qzone.a133689237.fo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {
    private ListView mListView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mListView = (ListView) findViewById(R.id.setting_listview);

        Map<String, String> keyValuePair0 = new HashMap<String, String>();
        keyValuePair0.put("name", "循环模式");
        keyValuePair0.put("shuomin", "选择播放模式：单曲循环、随机、顺序播放");
        Map<String, String> keyValuePair1 = new HashMap<String, String>();
        keyValuePair1.put("name", "重置天数");
        keyValuePair1.put("shuomin", "重新设定念佛天数");
        Map<String, String> keyValuePair2 = new HashMap<String, String>();
        keyValuePair2.put("name", "意见反馈");
        keyValuePair2.put("shuomin", "提供您宝贵的建议，帮助我做的更好");
        Map<String, String> keyValuePair3 = new HashMap<String, String>();
        keyValuePair3.put("name", "关于软件");
        keyValuePair3.put("shuomin", "查看关于app版本等信息");

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(keyValuePair0);
        list.add(keyValuePair1);
        list.add(keyValuePair2);
        list.add(keyValuePair3);
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
                        //startActivity(new Intent(SettingsActivity.this, ));
                        break;
                    case 1 :
                        startActivity(new Intent(SettingsActivity.this, chongzhiActivity.class));
                        break;
                    case 2 :
                        startActivity(new Intent(SettingsActivity.this, fankuiActivity.class));
                        break;
                    case 3 :
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


}
