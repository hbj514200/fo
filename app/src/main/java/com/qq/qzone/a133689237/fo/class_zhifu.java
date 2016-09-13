package com.qq.qzone.a133689237.fo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;


public class class_zhifu {

        public static void toAliPayScan(Context context) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://www.PayPal.Me/hbj514200");
                intent.setData(content_url);
                context.startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(context, context.getResources().getString(R.string.ninmeiyou), Toast.LENGTH_SHORT).show();
            }
        }

}
