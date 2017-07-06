package com.railwayticket.myapp.mywork.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.railwayticket.myapp.mywork.R;

public class HttpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        RequestParams params = new RequestParams();
//        params.addHeader("name", "value");

        params.addBodyParameter("account", "18310168993");
        params.addBodyParameter("password", "123456");
        params.addBodyParameter("userSource", "2");
        params.addBodyParameter("verificationCode", "222222");

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.POST, "http://192.168.1.201:8080/userApi/reg", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("MMMM", "SSSS" + responseInfo.result.toString());

            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });

    }

}
