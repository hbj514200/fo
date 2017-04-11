package com.qq.qzone.a133689237.fo.bbs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.qq.qzone.a133689237.fo.R;
import java.util.LinkedList;
import java.util.List;

public class bbsActivity extends AppCompatActivity {
    private static List<bbsData> str = new LinkedList<>();
    private myadapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.bbs_add_news);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bbsActivity.this, addActivity.class));
            }
        });

        if(str.isEmpty())    bbsUntil.getData(bbsActivity.this);
        ListView listView = (ListView) findViewById(R.id.bbs_listview);
        adapter = new myadapter(bbsActivity.this, R.layout.bbs_listview_item, str);
        listView.setAdapter(adapter);
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

    public class myadapter extends ArrayAdapter<bbsData> {

        public myadapter(Context context, int textId, List<bbsData> object) {
            super(context, textId, object);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.bbs_listview_item, null);
            TextView name = (TextView) view.findViewById(R.id.bbs_item_name);
            TextView content = (TextView) view.findViewById(R.id.bbs_item_content);
            TextView date= (TextView) view.findViewById(R.id.bbs_item_date);
            name.setText(str.get(position).name);
            content.setText(str.get(position).content);
            date.setText(str.get(position).date);
            return view;
        }
    }

    public void callBack(bbsData data){
        data.name = data.name.replaceAll("\\n", "");
        data.content = data.content.replaceAll("\n", "");
        ((LinkedList<bbsData>) str).addFirst(data);
        adapter.notifyDataSetChanged();
    }

}

