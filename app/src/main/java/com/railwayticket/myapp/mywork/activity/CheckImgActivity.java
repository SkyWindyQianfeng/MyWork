package com.railwayticket.myapp.mywork.activity;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.fragment.CheckImgAdapterFragment;
import java.util.ArrayList;
import java.util.List;

public class CheckImgActivity extends BaseActivity {

    private Context context;
    private ViewPager viewPager;
    private List<String> listUrl;
    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_img);
        context = CheckImgActivity.this;
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        getUrl();
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listUrl);
        viewPager.setAdapter(myFragmentPagerAdapter);
    }

    private void getUrl() {
//        MediaRecorder mediaRecorder=new MediaRecorder();
        listUrl=new ArrayList<>();
        listUrl.add("http://g.hiphotos.baidu.com/image/pic/item/0823dd54564e92589f2fe1019882d158cdbf4ec1.jpg");
        listUrl.add("http://h.hiphotos.baidu.com/image/pic/item/8b13632762d0f70346c0f2330cfa513d2797c5eb.jpg");
        listUrl.add("http://a.hiphotos.baidu.com/image/pic/item/e824b899a9014c08a6864f4a0e7b02087af4f4a4.jpg");
        listUrl.add("http://a.hiphotos.baidu.com/image/pic/item/b8014a90f603738d19266bcdb71bb051f919ecf5.jpg");
        listUrl.add("http://b.hiphotos.baidu.com/image/pic/item/a044ad345982b2b7c9245e2b35adcbef77099ba1.jpg");
        listUrl.add("http://a.hiphotos.baidu.com/image/pic/item/00e93901213fb80e53c3063832d12f2eb83894ca.jpg");

    }


    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<String> list;
        public MyFragmentPagerAdapter(FragmentManager fm,List<String> list) {
            super(fm);
            this.list=list;
        }

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            return CheckImgAdapterFragment.create(context, list.get(position));
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
