package com.qq.qzone.a133689237.fo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private Fragment listFragement = null;
    private FragmentManager fm = null;
    private TextView text;
    private LinearLayout adLayout = null;
    private Timer timer = new Timer();
    private int bannerflag = 0;
    InterstitialAd mInterstitialAd;
    Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            bannerflag = 1;
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
        adLayout =(LinearLayout)findViewById(R.id.adLayout);
        dinshiAD();
        admob();
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

    public void banner(){
        if (bannerflag==1){
            if (mInterstitialAd.isLoaded()){
                mInterstitialAd.show();
                bannerflag = 0;
            }
        }
    }

    private void dinshiAD() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(40000); } catch (Exception e) { }
                myhandler.sendMessage(new Message() );
                try { Thread.sleep(300000); } catch (Exception e) { }
                myhandler.sendMessage(new Message() );
                try { Thread.sleep(300000); } catch (Exception e) { }
                myhandler.sendMessage(new Message() );
                try { Thread.sleep(300000); } catch (Exception e) { }
                myhandler.sendMessage(new Message() );
                try { Thread.sleep(300000); } catch (Exception e) { }
                myhandler.sendMessage(new Message() );
            }
        }).start();

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void admob(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6630898560544189/3871700555");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        requestNewInterstitial();
    }

}
