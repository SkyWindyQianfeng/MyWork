package com.railwayticket.myapp.mywork.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.adapter.CheckBigImgAdapter;

import java.util.ArrayList;
import java.util.List;

public class CheckBigMessageActivity extends BaseActivity {

    private GridView gridView;
    private Context context;
    private CheckBigImgAdapter adapter;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_big_message);
        context = this;
        getData();

        initView();

    }

    private void getData() {
        list = new ArrayList<>();
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");
        list.add("http://fc05.deviantart.net/fs31/f/2008/212/e/a/Hadziaaa_by_bejson.jpg");
        list.add("http://7.pic.pc6.com/thumb/up/2013-1/20131155220107689_600_0.jpg");
        list.add("http://p0.so.qhmsg.com/t01cd2e37cdc53675aa.jpg");
        list.add("http://th00.deviantart.net/fs37/150/f/2008/247/9/1/Metropolis_Marvel_in_Color_by_theFranchize.jpg");


    }


    private void initView() {
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(3);
        gridView.setHorizontalSpacing(12);
        gridView.setVerticalSpacing(12);
        adapter = new CheckBigImgAdapter(context);
        adapter.setList(list);
        gridView.setAdapter(adapter);
    }




}
