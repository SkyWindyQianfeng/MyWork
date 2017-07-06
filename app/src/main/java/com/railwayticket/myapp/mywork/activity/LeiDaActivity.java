package com.railwayticket.myapp.mywork.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.view.LeiDaView;

public class LeiDaActivity extends BaseActivity {

    private Context context;
    private LeiDaView leiDaView;
    private float values[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lei_da);
        leiDaView = (LeiDaView) findViewById(R.id.leida);
        values= new float[]{5,3,2,5,6,8};
        leiDaView.setValues(values);
    }

}
