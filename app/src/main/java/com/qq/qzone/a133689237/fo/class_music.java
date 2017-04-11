package com.qq.qzone.a133689237.fo;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class class_music {
    public static int mySongCount = 28; //序号

    public static List<String> getStr(Context context) {
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
        st.add(context.getString(R.string.foyue18));
        st.add(context.getString(R.string.foyue19));
        st.add(context.getString(R.string.foyue20));
        st.add(context.getString(R.string.foyue21));
        st.add(context.getString(R.string.foyue22));
        st.add(context.getString(R.string.foyue23));
        st.add(context.getString(R.string.foyue24));
        st.add(context.getString(R.string.foyue25));
        st.add(context.getString(R.string.foyue26));
        st.add(context.getString(R.string.foyue27));
        st.add(context.getString(R.string.foyue28));
        st.add(context.getString(R.string.foyue29));

        File file = new File(Environment.getExternalStorageDirectory().toString() + "/念佛机");
        if (file.exists()){
            for (File f : file.listFiles())
                if (f.getName().endsWith(".mp3"))
                    st.add((st.size() + 1) + ": " + f.getName().replaceAll(".mp3", ""));
        }
        else try { file.mkdir(); } catch (Exception ignored) { System.out.println("创建文件夹失败");}

        st.add("添加您自己的佛乐……");
        return st;
    }

    private static ArrayList<String> biaobao = new ArrayList<>();
    public static String getId(int postion) {
        if(!biaobao.isEmpty())          return biaobao.get(postion);
        biaobao.add(Integer.toString(R.raw.nanwu1));
        biaobao.add(Integer.toString(R.raw.dabei2));
        biaobao.add(Integer.toString(R.raw.damin3));
        biaobao.add(Integer.toString(R.raw.dabeishi4));
        biaobao.add(Integer.toString(R.raw.guanshiyin5));
        biaobao.add(Integer.toString(R.raw.liuzi6));
        biaobao.add(Integer.toString(R.raw.zhunti7));
        biaobao.add(Integer.toString(R.raw.changshou8));
        biaobao.add(Integer.toString(R.raw.yaoshi9));
        biaobao.add(Integer.toString(R.raw.lianchizan10));
        biaobao.add(Integer.toString(R.raw.wangshen11));
        biaobao.add(Integer.toString(R.raw.xifaputi12));
        biaobao.add(Integer.toString(R.raw.lenyan13));
        biaobao.add(Integer.toString(R.raw.wanfo14));
        biaobao.add(Integer.toString(R.raw.jintu15));
        biaobao.add(Integer.toString(R.raw.zanfohe16));
        biaobao.add(Integer.toString(R.raw.yangzhi17));
        biaobao.add(Integer.toString(R.raw.shijia18));
        biaobao.add(Integer.toString(R.raw.erni19));
        biaobao.add(Integer.toString(R.raw.wenshu20));
        biaobao.add(Integer.toString(R.raw.jingang21));
        biaobao.add(Integer.toString(R.raw.banruo22));
        biaobao.add(Integer.toString(R.raw.xintuo23));
        biaobao.add(Integer.toString(R.raw.xinjin24));
        biaobao.add(Integer.toString(R.raw.jinxin25));
        biaobao.add(Integer.toString(R.raw.caishen26));
        biaobao.add(Integer.toString(R.raw.lvdu27));
        biaobao.add(Integer.toString(R.raw.fodin28));
        biaobao.add(Integer.toString(R.raw.dizang29));

        File file = new File(Environment.getExternalStorageDirectory().toString() + "/念佛机");
        File[] files = file.listFiles();
        if (files!=null && files.length>0)
            for (File f : files)
                if (f.getName().endsWith(".mp3")) biaobao.add(f.getAbsolutePath());
        return biaobao.get(postion);
    }

}
