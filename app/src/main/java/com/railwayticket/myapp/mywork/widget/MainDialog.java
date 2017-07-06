package com.railwayticket.myapp.mywork.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.railwayticket.myapp.mywork.R;

/**
 * Created by tianyuanyuan on 2016/8/24.
 */
public class MainDialog extends Dialog {

    private TextView tv_introduction;
    private Context context;
    private String introduction;

    public MainDialog(Context context,String introduction) {
        super(context);
        this.context=context;
        this.introduction=introduction;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("功能介绍");
        setContentView(R.layout.dialog_main);
        tv_introduction= (TextView) findViewById(R.id.tv_introduction);
        tv_introduction.setText(introduction);

    }
}
