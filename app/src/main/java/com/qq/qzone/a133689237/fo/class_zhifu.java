package com.qq.qzone.a133689237.fo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;


public class class_zhifu {

    public static void toAliPayScan(Context context) {
        try {
            Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007&qrcode=https:%2F%2Fd.alipay.com%2Fi%2Findex.htm%3Fb=RECEIVE_AC%26u%3DRyAG7gCace4ye6CD4glKlTb50KfchrdHeDrMGwf58pw%3D");
            context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            Toast.makeText(context, context.getResources().getString(R.string.ninmeiyouzhi), Toast.LENGTH_SHORT).show();
        }
    }

    public static void topaypal(Context context) {
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
