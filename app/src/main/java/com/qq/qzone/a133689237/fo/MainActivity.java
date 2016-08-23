package com.qq.qzone.a133689237.fo;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        class_xueru.cedin(MainActivity.this);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.main_list_container, new listviewFragment()).commit();

    }

}
