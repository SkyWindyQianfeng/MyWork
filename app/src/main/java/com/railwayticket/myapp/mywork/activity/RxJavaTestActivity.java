package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.os.Bundle;

import com.pangpangzhu.pullrefreshlibrary.PullToRefreshGridView;
import com.railwayticket.myapp.mywork.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class RxJavaTestActivity extends BaseActivity {

    private PullToRefreshGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);
    }

    private Observer<String> observer=new Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    } ;

    private Observable<String> observable=Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
                 
        }
    });


}
