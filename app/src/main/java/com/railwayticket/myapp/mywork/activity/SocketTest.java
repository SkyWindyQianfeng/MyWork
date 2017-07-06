package com.railwayticket.myapp.mywork.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.railwayticket.myapp.mywork.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketTest extends BaseActivity {

    String line = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_test);
        openSocket();
    }


    public void openSocket() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket("192.168.3.139", 30001);
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                    Log.d("socket", "来自服务器的数据：" + sb.toString());
                    br.close();
                    s.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
