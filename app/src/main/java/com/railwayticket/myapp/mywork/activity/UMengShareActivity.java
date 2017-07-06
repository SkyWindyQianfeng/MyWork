package com.railwayticket.myapp.mywork.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.railwayticket.myapp.mywork.R;
import com.tencent.connect.dataprovider.DataType;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

public class UMengShareActivity extends BaseActivity implements View.OnClickListener {

    private TextView btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umeng_share);
        btnShare= (TextView) findViewById(R.id.share);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.share){
            new ShareAction(UMengShareActivity.this).withText("hello")
                    .setDisplayList(SHARE_MEDIA.DINGTALK,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.ALIPAY,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN_CIRCLE)
                    .setCallback(listener).setShareboardclickCallback(new ShareBoardlistener() {
                @Override
                public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                    if(share_media.equals(SHARE_MEDIA.QQ)){
                        new ShareAction(UMengShareActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                .withText("hello").withMedia(new UMWeb("http://www.baidu.com"))
                                .setCallback(listener)
                                .share();
                    }
                    if(share_media.equals(SHARE_MEDIA.QZONE)){
                        new ShareAction(UMengShareActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                .withText("hello").withMedia(new UMWeb("http://www.baidu.com"))
                                .setCallback(listener)
                                .share();
                    }if(share_media.equals(SHARE_MEDIA.WEIXIN)){
                        new ShareAction(UMengShareActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                .withText("hello").withMedia(new UMWeb("http://www.baidu.com"))
                                .setCallback(listener)
                                .share();
                    }if(share_media.equals(SHARE_MEDIA.WEIXIN_CIRCLE)){
                        new ShareAction(UMengShareActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                .withText("hello").withMedia(new UMWeb("http://www.baidu.com"))
                                .setCallback(listener)
                                .share();
                    }
                }
            }).open();
        }
    }

    private UMShareListener listener=new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(UMengShareActivity.this,""+share_media,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(UMengShareActivity.this,""+throwable.toString(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
