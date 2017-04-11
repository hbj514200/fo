package com.qq.qzone.a133689237.fo.bbs;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import java.util.HashMap;

public class bbsUntil {

    public static boolean qinqiu;  //防止短时间多次请求，列表重复

    public static void getData(final bbsActivity activity){
        if(qinqiu)      return;

        Firebase mFireRef = new Firebase("https://nianfo-8afe9.firebaseio.com/bbs");
        Query recentPostsQuery = mFireRef.limitToLast(50);

        recentPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String,String> map = (HashMap<String, String>) dataSnapshot.getValue();
                activity.callBack(new bbsData(map.get("name"), map.get("content"), map.get("date")+"日"));
                qinqiu = true;
            }
            @Override   public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
            @Override   public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override   public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override   public void onCancelled(FirebaseError firebaseError) {}
        });

    }

}
