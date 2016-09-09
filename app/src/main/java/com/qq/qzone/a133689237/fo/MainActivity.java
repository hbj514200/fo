package com.qq.qzone.a133689237.fo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.spot.SpotManager;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Fragment listFragement = null;
    private FragmentManager fm = null;
    private TextView text;
    private LinearLayout adLayout = null;
    private Timer timer = new Timer();
    private int bannerflag = 0;
    Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1)  banner();
            if (msg.what == 2){
                SpotManager.getInstance(MainActivity.this).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
                SpotManager.getInstance(MainActivity.this).setAnimationType(SpotManager.ANIM_ADVANCE);
                SpotManager.getInstance(MainActivity.this).showSpotAds(MainActivity.this);
            }
            super.handleMessage(msg);
        }
    };

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
        double tem = Math.random()*10;
        if ( tem<=2.5 ){
            adLayout =(LinearLayout)findViewById(R.id.adLayout);
            dinshiAD();
        }
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

    private void banner(){
        bannerflag++;
            if (text.getVisibility() == View.VISIBLE && bannerflag%10==0) {
                text.setVisibility(View.GONE);
                adLayout.setVisibility(View.VISIBLE);
                View adView = BannerManager.getInstance(MainActivity.this).getBanner(MainActivity.this);
                adLayout.removeAllViews();
                adLayout.addView(adView);
            } else {
                adLayout.setVisibility(View.GONE);
                text.setVisibility(View.VISIBLE);
                suijitext();
            }
        if (Math.random()*10<=3 || bannerflag == 1){
            SpotManager.getInstance(MainActivity.this).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
            SpotManager.getInstance(MainActivity.this).setAnimationType(SpotManager.ANIM_ADVANCE);
            SpotManager.getInstance(MainActivity.this).showSpotAds(MainActivity.this);
        }

    }

    private void dinshiAD() {
        TimerTask task = new TimerTask() {
            public void run() {
                Message message1 = new Message();   message1.what = 1;
                myhandler.sendMessage(message1);
                if (bannerflag %10 == 1){
                    Message message2 = new Message();   message2.what = 1;
                    myhandler.sendMessage(message2);
                }
            }
        };
        timer.schedule(task, 60000, 30000);
    }

}
