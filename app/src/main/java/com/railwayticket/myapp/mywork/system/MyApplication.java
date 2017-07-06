package com.railwayticket.myapp.mywork.system;

import android.app.Application;
import android.util.Log;

import com.railwayticket.myapp.mywork.utils.LoaderImage;
import com.umeng.message.IUmengCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by tianyuanyuan on 2016/6/1.
 */
public class MyApplication extends Application {

    private static MyApplication instans = null;

    public static MyApplication getInstance() {
        return instans;
    }
    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");//微信
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");//qq空间
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");//丁丁
        PlatformConfig.setAlipay("2015111700822536");
    }
    public MyApplication() {
        super();
        instans = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        LoaderImage.getInstance(this);
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.i("myToken",deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        mPushAgent.enable(new IUmengCallback() {
            @Override
            public void onSuccess() {
                Log.i("push","success");
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i("push",s+"-->"+s1);
            }
        });
    }
}
