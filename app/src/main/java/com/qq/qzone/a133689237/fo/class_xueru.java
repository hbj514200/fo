package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

public class class_xueru {

    public static void cedin(Context context){
        Calendar calendar = Calendar.getInstance();
        SharedPreferences pre = context.getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        if(pre.getInt("dayhao",-1) != calendar.get(Calendar.DAY_OF_MONTH)){
            editor.putInt("dayhao", calendar.get(Calendar.DAY_OF_MONTH));
            editor.putInt("tianshu", pre.getInt("tianshu",0)+1);
            editor.commit();
        }
    }

}
