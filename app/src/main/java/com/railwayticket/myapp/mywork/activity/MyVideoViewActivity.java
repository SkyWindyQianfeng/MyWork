package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.baidu.cyberplayer.core.BMediaController;
import com.baidu.cyberplayer.core.BVideoView;
import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.utils.DateUtil;

import java.io.IOException;

public class MyVideoViewActivity extends BaseActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {


    private VideoView videoView;
    private Context context;
    private LinearLayout controller;
    private FrameLayout videoRl;
    private TextView startTime;
    private TextView endTime;
    private SeekBar seekBar;
    private ImageView play;


    private int what = 2;

    private boolean controllerIsShow = false;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                startTime.setText(DateUtil.longToHour(videoView.getCurrentPosition()));
                seekBar.setProgress(videoView.getCurrentPosition());
                if (videoView.isPlaying()) {
                    sendEmptyMessageDelayed(1, 1000);
                }
            } else if (msg.what == what) {
                if (controllerIsShow) {
                    what = 2;
                    controller.setVisibility(View.GONE);
                    controllerIsShow = false;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_video_view);
        context = this;
        initView();
        initListener();
    }


    private void initListener() {
        videoRl.setOnClickListener(this);
        play.setOnClickListener(this);
        endTime.setOnClickListener(this);
    }

    private void initView() {
        videoView = (VideoView) findViewById(R.id.video_view);
        videoRl = (FrameLayout) findViewById(R.id.rl_video);
        controller = (LinearLayout) findViewById(R.id.video_controller);
        startTime = (TextView) findViewById(R.id.tv_start_time);
        endTime = (TextView) findViewById(R.id.tv_end_time);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        videoView.setVideoPath("http://img.jan360d.com/media/jan.mp4");
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                startTime.setText("00:00:00");
                endTime.setText(DateUtil.longToHour(videoView.getDuration()));
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });
        play = (ImageView) findViewById(R.id.iv_play);
        controller.setVisibility(View.VISIBLE);
    }


    private void stop() {
        videoView.pause();
    }

    private void start() {
        videoView.start();
        seekBar.setMax(videoView.getDuration());
        handler.sendEmptyMessage(1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_video:

                if (!videoView.isPlaying()) {
                    start();
                }
                controller.setVisibility(View.VISIBLE);
                if (!controllerIsShow) {
                    what++;
                    handler.sendEmptyMessageDelayed(what, 6000);
                    controllerIsShow = true;
                } else {
                    controller.setVisibility(View.GONE);
                    controllerIsShow = false;
                    Message msg = new Message();
                    msg.what = 2;
                    msg.arg1 = 2;
                    handler.sendMessage(msg);
                }

                break;
            case R.id.iv_play:
                if (videoView.isPlaying()) {
                    stop();
                } else {
                    start();
                }
                break;
            case R.id.tv_end_time:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                FrameLayout.LayoutParams layoutParams =
                        new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.gravity = Gravity.CENTER;
                videoView.setLayoutParams(layoutParams);
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int target = seekBar.getProgress();
        videoView.seekTo(target);
    }
}
