package com.qq.qzone.a133689237.fo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.List;

public class listviewFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{
    private ListView mListView;
    private List<String> strs;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private int old_position = -1;
    private Boolean shunxv_media=true;
    private ImageView zuoButton;
    private ImageView youButton;
    private ImageView foxiang;
    private FloatingActionButton fabButton;
    SharedPreferences pre = null;
    private myadapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        adapter = new myadapter(getActivity(), R.layout.main_list_item, strs);
        foxiang = (ImageView) view.findViewById(R.id.main_foxiang);
        fabButton = (FloatingActionButton) view.findViewById(R.id.main_fab_setting);
        mListView = (ListView) view.findViewById(R.id.main_listview);
        mListView.setAdapter(adapter);
        zuoButton = (ImageView) view.findViewById(R.id.main_zuo);
        youButton = (ImageView) view.findViewById(R.id.main_you);
        mListView.setOnItemClickListener(this);
        zuoButton.setOnClickListener(this);
        youButton.setOnClickListener(this);
        foxiang.setOnClickListener(this);
        fabButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tem = old_position;
        old_position = -1;
        adapter.notifyDataSetChanged();
        old_position = tem;
        CheckedTextView textView = (CheckedTextView) view.findViewById(R.id.text_checked);
        textView.setChecked(true);
        huan(position);
        ((MainActivity)getActivity()).banner();
    }

    private void huan(int position){
        if(position > 22 || position <0)   {   huan(0); return;  }
        if(mMediaPlayer == null){
                mMediaPlayer = MediaPlayer.create(getActivity(), class_music.getId(position));
                mMediaPlayer.start();
        }
        else {
            if(position != old_position){
                    if (mMediaPlayer.isPlaying())   mMediaPlayer.stop();
                    if (mMediaPlayer != null)       mMediaPlayer.release();
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer = MediaPlayer.create(getActivity(), class_music.getId(position));
                    mMediaPlayer.start();
            }
            if(position == old_position){
                if(mMediaPlayer.isPlaying())    mMediaPlayer.pause();
                else                            mMediaPlayer.start();
            }
        }
        old_position = position;
        bofangmoshi();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pre = getActivity().getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        strs = class_music.getStr(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_zuo :
                if (mMediaPlayer != null && old_position >= 1){
                    huan(old_position - 1);
                    adapter.notifyDataSetChanged();
                    ((MainActivity)getActivity()).banner();
                }
                break;
            case R.id.main_you :
                if (mMediaPlayer != null && old_position < 23-1){
                    huan(old_position + 1);
                    adapter.notifyDataSetChanged();
                    ((MainActivity)getActivity()).banner();
                }
                break;
            case R.id.main_foxiang :
                startActivity(new Intent(getActivity(), juanzengActivity.class));
                ((MainActivity)getActivity()).banner();
                break;
            default:
                break;
    }
}

    private void bofangmoshi(){
        switch (pre.getInt("bofangmoshi",0)){
            case 0 :
                if (mMediaPlayer != null){
                    mMediaPlayer.setLooping(true);
                    shunxv_media = false;
                }
                break;
            case 1 :
                shunxv_media = true;
                if(mMediaPlayer != null)
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if ( shunxv_media ){
                            mMediaPlayer.setLooping(false);
                            huan(old_position+1);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bofangmoshi();
    }

    public class myadapter extends ArrayAdapter<String> {
        int resouId;

        public myadapter(Context context, int textId, List<String> object) {
            super(context, textId, object);
            resouId = textId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getContext()).inflate(resouId, null);
            CheckedTextView checktext = (CheckedTextView) view.findViewById(R.id.text_checked);
            checktext.setText(getItem(position));
            if (position == old_position) checktext.setChecked(true);
            return view;
        }
    }

}
