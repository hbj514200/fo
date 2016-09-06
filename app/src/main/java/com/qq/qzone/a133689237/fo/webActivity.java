package com.qq.qzone.a133689237.fo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.spot.SpotManager;

public class webActivity extends AppCompatActivity implements View.OnTouchListener {

    private WebView webView;
    private int touchflag = 0 ;
    private ProgressBar pb;
    Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1)  banner();
            if (msg.what == 2)  chapin();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webview);
        pb = (ProgressBar) findViewById(R.id.web_pb);
        webView.setOnTouchListener(this);

        String url = getIntent().getStringExtra("url");
        load(url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try { Thread.sleep(5000); } catch (Exception e) { }
                    Message message1 = new Message();    message1.what = 1;
                    myhandler.sendMessage(message1);
                try { Thread.sleep(35000); } catch (Exception e) { }
                    Message message2 = new Message();    message2.what = 2;
                    myhandler.sendMessage(message2);
            }
        }).start();

    }

    private void load(String url){
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if(newProgress==100){
                    pb.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_UP && touchflag==0){
            touchflag++;
            getSupportActionBar().hide();
            return true;
        }
        return false;
    }

    private void banner(){
        if (Math.random()*10<7){
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.BOTTOM;
            View adView = BannerManager.getInstance(webActivity.this).getBanner(webActivity.this);
            this.addContentView(adView, layoutParams);
        }
    }

    private void chapin(){
        if (Math.random()*10<6){
            SpotManager.getInstance(webActivity.this).setSpotOrientation(SpotManager.ORIENTATION_PORTRAIT);
            SpotManager.getInstance(webActivity.this).setAnimationType(SpotManager.ANIM_ADVANCE);
            SpotManager.getInstance(webActivity.this).showSpotAds(webActivity.this);
        }
    }

}
