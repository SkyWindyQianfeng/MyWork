package com.railwayticket.myapp.mywork.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.utils.LoaderImage;
import com.railwayticket.myapp.mywork.widget.TouchImageView;

public class TouchImageViewActivity extends BaseActivity {

    private TouchImageView imageView;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_image_view);
        context=this;

        imageView= (TouchImageView) findViewById(R.id.image);
        imageView.setMaxZoom(4f);
//        LoaderImage.loaderImage("http://p4.so.qhmsg.com/t0141c0cf9b82f88889.jpg",imageView);
        imageView.setImageResource(R.drawable.honor);
    }

}
