package com.railwayticket.myapp.mywork.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;

import com.railwayticket.myapp.mywork.R;

public class MyMediaPlayerActivity extends BaseActivity {



    private SurfaceView surfaceView;
    private Context context;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_media_player);
        context=this;
        initView();
    }

    private void initView() {
        surfaceView= (SurfaceView) findViewById(R.id.surface_view);
    }
}
