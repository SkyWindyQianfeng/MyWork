package com.railwayticket.myapp.mywork.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.view.GuaguaCardView;

public class GuaguaCardActivity extends BaseActivity {


    private Context context;
    private GuaguaCardView guaguaCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guagua_card);
        context=this;

        guaguaCardView= (GuaguaCardView) findViewById(R.id.guaguaka);
        guaguaCardView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        guaguaCardView.setShowButton(new GuaguaCardView.ShowButton() {
            @Override
            public void show(int percent) {
                if(percent>50){
                    Toast.makeText(GuaguaCardActivity.this,"显示按钮",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
