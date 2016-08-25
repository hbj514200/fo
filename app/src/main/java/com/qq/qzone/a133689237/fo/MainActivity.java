package com.qq.qzone.a133689237.fo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private Fragment listFragement = null;
    private FragmentManager fm = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        class_xueru.cedin(MainActivity.this);
        if(fm == null) fm = getSupportFragmentManager();
        if(listFragement == null)   listFragement = new listviewFragment();
        fm.beginTransaction().add(R.id.main_list_container, listFragement).commit();
    }

}
