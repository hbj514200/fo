package com.qq.qzone.a133689237.fo;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class dia_main_caidan extends DialogFragment implements View.OnTouchListener, AdapterView.OnItemClickListener {
    private Button mButton;
    public ListView mListView;
    private MediaPlayer player;
    private MainActivity activity;

    public void setData(MediaPlayer player, MainActivity activity) {
        this.player = player;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_choose_daji, container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mListView = (ListView) view.findViewById(R.id.dialog_list_view);
        mButton = (Button) view.findViewById(R.id.dialog_daji_button);
        String[] strs = new String[]{getString(R.string.zantinbofang), getString(R.string.jixvbofang), getString(R.string.dinshiguanbi), getString(R.string.tuichuruanjian)};
        mListView.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.dialog_listview_item, strs));
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setOnItemClickListener(this);
        mButton.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mButton.setBackgroundColor(Color.parseColor("#303f9f"));
        dismiss();
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch(i+1){
            case 1 :
                if(player.isPlaying())  player.pause();
                break;
            case 2 :
                if(!player.isPlaying()) player.start();
                break;
            case 3 :
                showSingleChoiceDialog();
                break;
            case 4 :
                System.exit(0);
                break;
        }
    }

    int yourChoice;
    private void showSingleChoiceDialog() {
        final String[] items = {"10分钟", "20分钟", "30分钟", "1小时", "2小时", "3小时", "5小时"};
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(getActivity());
        singleChoiceDialog.setTitle(getString(R.string.xuanzeguanbi));
        singleChoiceDialog.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setNegativeButton(getString(R.string.quxiao), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), getString(R.string.meixuanze), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        singleChoiceDialog.setPositiveButton(getString(R.string.quedin), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice == -1)   return;
                        int time = 300*60;
                        switch (yourChoice+1){
                            case 1 :    time = 10*60;    break;
                            case 2 :    time = 20*60;    break;
                            case 3 :    time = 30*60;    break;
                            case 4 :    time = 60*60;    break;
                            case 5 :    time = 120*60;   break;
                            case 6 :    time = 180*60;   break;
                            case 7 :    time = 300*60;   break;
                        }
                        Toast.makeText(getActivity(), items[yourChoice]+getString(R.string.houzidong), Toast.LENGTH_SHORT).show();
                        final int finalTime = time;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {   Thread.sleep(finalTime*1000);    } catch (Exception ignored) {}
                                activity.guanbi();
                            }
                        }).start();
                    }
                });
        singleChoiceDialog.show();
    }

}