package com.qq.qzone.a133689237.fo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private Fragment listFragement = null;
    private FragmentManager fm = null;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        class_xueru.cedin(MainActivity.this);
        if(fm == null) fm = getSupportFragmentManager();
        if(listFragement == null)   listFragement = new listviewFragment();
        fm.beginTransaction().add(R.id.main_list_container, listFragement).commit();

        text = (TextView) findViewById(R.id.main_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        suijitext();
    }

    private void suijitext(){
        String[] suiji = {
                getResources().getString(R.string.maindi_1),
                getResources().getString(R.string.maindi_2),
                getResources().getString(R.string.maindi_3),
                getResources().getString(R.string.maindi_4),
                getResources().getString(R.string.maindi_5),
                getResources().getString(R.string.maindi_6),
                getResources().getString(R.string.maindi_7),
                getResources().getString(R.string.maindi_8),
                getResources().getString(R.string.maindi_9)
        };
        int index = (int) ( Math.random()*suiji.length - 1 );
        if(index < 0)   index = 1;
        text.setText(suiji[index]);
    }

}
