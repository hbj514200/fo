package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.Calendar;
import java.util.HashMap;

public class guangbo extends BroadcastReceiver {

    Context mContext;
    SharedPreferences pre;
    SharedPreferences.Editor editor;
    private int nowday;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Firebase.setAndroidContext(mContext);
        Calendar calendar = Calendar.getInstance();
        nowday = calendar.get(Calendar.DAY_OF_MONTH);
        pre= mContext.getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        editor = pre.edit();
        if (pre.getInt("tuisong_day",-1) != nowday){
            duqu();
        }

    }

    private void duqu(){
        Firebase ref = new Firebase("https://nianfo-8afe9.firebaseio.com/tuisong");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,String> map = (HashMap<String, String>) dataSnapshot.getValue();
                String title = map.get("title");
                String content = map.get("content");
                String url = map.get("url");
                sendNotification(title, content, url);
                editor.putInt("tuisong_day", nowday);
                editor.commit();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void sendNotification(String title, String content, String url) {
        Intent intent = new Intent(mContext, webActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("url", url);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notificationBuilder.build());
    }


}

