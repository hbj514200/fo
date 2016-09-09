package com.qq.qzone.a133689237.fo;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class class_music {

    public static List<String> getStr(Context context){

        List<String> st = new ArrayList<String>();
        st.add(context.getString(R.string.foyue1));
        st.add(context.getString(R.string.foyue2));
        st.add(context.getString(R.string.foyue3));
        st.add(context.getString(R.string.foyue4));
        st.add(context.getString(R.string.foyue5));
        st.add(context.getString(R.string.foyue6));
        st.add(context.getString(R.string.foyue7));
        st.add(context.getString(R.string.foyue8));
        st.add(context.getString(R.string.foyue9));
        st.add(context.getString(R.string.foyue10));
        st.add(context.getString(R.string.foyue11));
        st.add(context.getString(R.string.foyue12));
        st.add(context.getString(R.string.foyue13));
        st.add(context.getString(R.string.foyue14));
        st.add(context.getString(R.string.foyue15));
        st.add(context.getString(R.string.foyue16));
        st.add(context.getString(R.string.foyue17));
        return st;
    }

    public static int getId(int postion){
        int[] biaobao = new int[]{
          R.raw.nanwu1,
          R.raw.dabei2,
          R.raw.damin3,
          R.raw.dabeishi4,
          R.raw.guanshiyin5,
          R.raw.liuzi6,
          R.raw.zhunti7,
          R.raw.changshou8,
          R.raw.yaoshi9,
          R.raw.lianchizan10,
          R.raw.wangshen11,
          R.raw.xifaputi12,
          R.raw.lenyan13,
          R.raw.wanfo14,
          R.raw.jintu15,
          R.raw.zanfohe16,
          R.raw.yangzhi17
        };
        return biaobao[postion];
    }

}
