package com.railwayticket.myapp.mywork.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.railwayticket.myapp.mywork.R;


public class BroadCaseDemoActivity extends BaseActivity {


    public class myBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_broad_case_demo);
    }
}
