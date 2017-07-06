package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.bean.SmsMessageContent;

import java.util.ArrayList;

public class ShowSmsMessageActivity extends BaseActivity {


    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<SmsMessageContent> listMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sms_message);
        if(getIntent().getExtras()!=null){

        listMessage= (ArrayList<SmsMessageContent>) getIntent().getExtras().getSerializable("list");
//        Toast.makeText(ShowSmsMessageActivity.this,"执行了",Toast.LENGTH_LONG).show();
        }

        listView= (ListView) findViewById(R.id.listView);
        adapter=new ArrayAdapter(ShowSmsMessageActivity.this,android.R.layout.simple_list_item_1,listMessage);
        listView.setAdapter(adapter);

    }
}
