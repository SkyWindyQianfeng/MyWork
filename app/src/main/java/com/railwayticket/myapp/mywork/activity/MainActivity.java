package com.railwayticket.myapp.mywork.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.railwayticket.myapp.mywork.widget.MainDialog;
import com.railwayticket.myapp.mywork.zxing.qr_codescan.ZxingMainActivity;
import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private List list;
    private ArrayAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        PushAgent.getInstance(context).onAppStart();
        getDate();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        Log.d("mw", "mm" + width);
        Log.d("mw", "mm" + height);


    }

    private void getDate() {
        list = new ArrayList();
        list.add("通讯录相关操作");
        list.add("照片处理");
        list.add("版本更新");
        list.add("网络请求");
        list.add("自定义控件");
        list.add("百度播放器");
        list.add("缩放Img");
        list.add("刮刮卡");
        list.add("雷达图");
        list.add("二维码");
        list.add("Socket测试");
        list.add("GreenDaoTest");
        list.add("百度地图");
        list.add("RxJava异步加载图片");
        list.add("友盟分享");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(MainActivity.this, GetPhoneNum.class);
                break;
            case 1:
                intent.setClass(MainActivity.this, TakePhotoActivity.class);
                break;
            case 2:
                intent.setClass(MainActivity.this, UpDateActivity.class);
                break;
            case 3:
                intent.setClass(this, HttpActivity.class);
                break;
            case 4:
                intent.setClass(this, DIYViewActivity.class);
                break;
            case 5:
                intent.setClass(this, MyVideoViewActivity.class);
                break;
            case 6:
                intent.setClass(this, TouchImageViewActivity.class);
                break;
            case 7:
                intent.setClass(this, GuaguaCardActivity.class);
                break;
            case 8:
                intent.setClass(this, LeiDaActivity.class);
                break;
            case 9:
                intent.setClass(this, ZxingMainActivity.class);
                break;
            case 10:
                intent.setClass(this, SocketTest.class);
                break;
            case 11:
                intent.setClass(this, GreenDaoTest.class);
                break;
            case 12:
                intent.setClass(this, BaiduMapActivity.class);
                break;
            case 13:
                try {
                    intent.setClass(this, Class.forName("RxJavaTestActivity"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 14:
                intent.setClass(this, UMengShareActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        MainDialog dialog = null;
        switch (position) {
            case 0:
                dialog = new MainDialog(MainActivity.this, "1、读取通讯录列表\n2、选择联系人打电话\n3、选择联系人发送短信的两种方式");
                dialog.show();
                break;
            case 1:
                dialog = new MainDialog(MainActivity.this, "1、拍照获取照片\n2、从相册中选择照片\n3、将获取到的照片压缩\n4、截取照片的一部分");
                dialog.show();
                break;
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lau","onDestroyMain");
    }
}
