package com.qq.qzone.a133689237.fo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.qq.qzone.a133689237.fo.bbs.bbsActivity;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class listviewFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{
    private List<String> strs;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private int old_position = -1;
    private Boolean shunxv_media=true;
    SharedPreferences pre = null;
    private myadapter adapter;
    private ImageView foxiang2;
    private Handler myhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            foxiang2.setVisibility(View.VISIBLE);               //佛像动态呼吸发光
            set.start();
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        adapter = new myadapter(getActivity(), R.layout.main_list_item, strs);
        ImageView foxiang = (ImageView) view.findViewById(R.id.main_foxiang);
        foxiang2 = (ImageView) view.findViewById(R.id.main_foxiang2);
        FloatingActionButton fabButton = (FloatingActionButton) view.findViewById(R.id.main_fab_setting);
        ListView listView = (ListView) view.findViewById(R.id.main_listview);
        listView.setAdapter(adapter);
        ImageView zuoButton = (ImageView) view.findViewById(R.id.main_zuo);
        ImageView youButton = (ImageView) view.findViewById(R.id.main_you);
        listView.setOnItemClickListener(this);
        zuoButton.setOnClickListener(this);
        youButton.setOnClickListener(this);
        foxiang.setOnClickListener(this);
        FloatingActionButton caidan = (FloatingActionButton) view.findViewById(R.id.main_fab_setting2);
        FloatingActionButton bbs = (FloatingActionButton) view.findViewById(R.id.main_fab_people);
        caidan.setOnClickListener(this);
        bbs.setOnClickListener(this);
        fabButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
        });
        foTimer.schedule(new TimerTask() {
            public void run(){
                foxiangAnimator();
            }
        }, 6000, 3000);           //佛像Timer佛像闪耀动画

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tem = old_position;
        old_position = -1;
        adapter.notifyDataSetChanged();
        old_position = tem;
        huan(position);
        ((MainActivity)getActivity()).banner();
    }

    private void huan(int position){
        if(position==class_music.getStr(getActivity()).size()-1 && old_position!=class_music.getStr(getActivity()).size()-2){
            new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.tianjiafoyue)).setMessage(getString(R.string.tianjianContent)).setPositiveButton("确定", null).show();
            return;
        }
        if(position >= class_music.getStr(getActivity()).size()-1 || position <0)   {   huan(0); return;  }
        if (mMediaPlayer == null) {
            if (position <= class_music.mySongCount)     mMediaPlayer = MediaPlayer.create(getActivity(), Integer.valueOf(class_music.getId(position)));
            else try {
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setDataSource(class_music.getId(position));
                mMediaPlayer.prepare();
            } catch (IOException e) { Log.i("播放异常", "失败"); }
            mMediaPlayer.start();
        }
        else {
            if (position != old_position) {
                if (mMediaPlayer.isPlaying()) mMediaPlayer.stop();
                if (mMediaPlayer != null)     mMediaPlayer.release();
                if (position <= class_music.mySongCount)     mMediaPlayer = MediaPlayer.create(getActivity(), Integer.valueOf(class_music.getId(position)));
                else try {
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource(class_music.getId(position));
                    mMediaPlayer.prepare();
                } catch (IOException e) {   Toast.makeText(getActivity(), "磁盘文件播放失败", Toast.LENGTH_SHORT).show(); }
                mMediaPlayer.start();
            }
            if (position == old_position){
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
                if (mMediaPlayer != null && old_position < class_music.getStr(getActivity()).size()-1){
                    huan(old_position + 1);
                    adapter.notifyDataSetChanged();
                    ((MainActivity)getActivity()).banner();
                }
                break;
            case R.id.main_foxiang :
                startActivity(new Intent(getActivity(), juanzengActivity.class));
                break;
            case R.id.main_fab_setting2 :
                dia_main_caidan dialog = new dia_main_caidan();
                dialog.setData(mMediaPlayer, (MainActivity)getActivity());
                dialog.show(getActivity().getFragmentManager(), "CHOOSE_DAJI");
                break;
            case R.id.main_fab_people :
                startActivity(new Intent(getActivity(), bbsActivity.class));
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
            if (position == old_position){
                checktext.setChecked(true);
                checktext.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            return view;
        }

    }

    private Timer foTimer = new Timer();
    private AnimatorSet set = null;
    private void foxiangAnimator(){
        if(set==null){
            ObjectAnimator foAnimator = ObjectAnimator.ofFloat(foxiang2, "alpha", 0f, 1f).setDuration(1700);
            ObjectAnimator foAnimator2 = ObjectAnimator.ofFloat(foxiang2, "alpha", 1f, 0f).setDuration(1700);
            set = new AnimatorSet();
            set.play(foAnimator).before(foAnimator2);
        }
        myhandler.sendMessage(new Message());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();      //防止出现activity关了， MediaPlayer却还在播放的尴尬。
        mMediaPlayer.stop();
    }
}
