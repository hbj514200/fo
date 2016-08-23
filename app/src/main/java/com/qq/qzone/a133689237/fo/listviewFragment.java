package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listviewFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private String[] strs = class_music.getStr();
    private MediaPlayer mMediaPlayer = null;
    private int old_position = -1;
    SharedPreferences pre = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        mListView = (ListView) view.findViewById(R.id.main_listview);
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.main_list_item, strs) );
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(getActivity(), class_music.getId(position));
            mMediaPlayer.start();
        }
        if (mMediaPlayer != null){
            if(position != old_position){
                mMediaPlayer.release();
                mMediaPlayer = MediaPlayer.create(getActivity(), class_music.getId(position));
                mMediaPlayer.start();
            }
            if(position == old_position){
                if(mMediaPlayer.isPlaying())    mMediaPlayer.pause();
                else                            mMediaPlayer.start();
            }

        }
        old_position = position;
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = pre.edit();
        editor.putInt("old_position", old_position).commit();
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pre = getActivity().getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        old_position = pre.getInt("old_position", -1);
    }
}
