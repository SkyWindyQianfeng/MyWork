package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.railwayticket.myapp.mywork.R;

import java.util.ArrayList;
import java.util.List;

public class BaiduMapActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private MapView bmapView;
    private Spinner spinner;
    private List<String> spinnerList;
    private  BaiduMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        setData();

        bmapView= (MapView) findViewById(R.id.bmapView);
        spinner = (Spinner) findViewById(R.id.spinner);
       map = bmapView.getMap();

        spinner.setAdapter(new ArrayAdapter<String>(BaiduMapActivity.this,android.R.layout.simple_spinner_item,spinnerList));
        spinner.setOnItemSelectedListener(this);


    }


    private void setData(){
        spinnerList=new ArrayList<>();
        spinnerList.add("卫星地图");
        spinnerList.add("普通地图");
        spinnerList.add("实时交通地图");
        spinnerList.add("城市热力图");
    }

    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bmapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==1){
            map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            map.setTrafficEnabled(false);
            map.setBaiduHeatMapEnabled(false);
        }else if(i==0){
            map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            map.setTrafficEnabled(false);
            map.setBaiduHeatMapEnabled(false);
        }else if(i==2){
            map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            map.setTrafficEnabled(true);
            map.setBaiduHeatMapEnabled(false);
        }else if(i==3){
            map.setTrafficEnabled(false);
            map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            map.setBaiduHeatMapEnabled(true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
