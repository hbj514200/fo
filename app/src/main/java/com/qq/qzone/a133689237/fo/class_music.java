package com.qq.qzone.a133689237.fo;

import android.content.Context;

public class class_music {

    public static String[] getStr(Context context){
        String[] str = new String[]{
                context.getString(R.string.foyue1),
                context.getString(R.string.foyue2),
                context.getString(R.string.foyue3),
                context.getString(R.string.foyue4),
                context.getString(R.string.foyue5),
                context.getString(R.string.foyue6),
                context.getString(R.string.foyue7),
                context.getString(R.string.foyue8),
                context.getString(R.string.foyue9),
                context.getString(R.string.foyue10),
                context.getString(R.string.foyue11)
        };
        return str;
    }

    public static int getId(int postion){
        int[] biaobao = new int[]{
          R.raw.dihou_1
        };
        return biaobao[postion];
    }

}
