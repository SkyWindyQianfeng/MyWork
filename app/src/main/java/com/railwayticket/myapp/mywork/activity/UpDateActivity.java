package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.railwayticket.myapp.mywork.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class UpDateActivity extends BaseActivity {

    private Context context;
    private Button update;
    private int versionCode;
    private HttpUtils utils;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
            }

        }
    };

    private static int start = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);
        context = this;
        update = (Button) findViewById(R.id.btn_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    versionCode = context.getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
//                InputStream is = getInputStream("http://p2.so.qhmsg.com/bdr/_240_/t01880712f0b22e9432.jpg");
//                try {
//                    inputStreamToFile(is);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                File file = new File(Environment.getExternalStorageDirectory(), "upu.apk");
                HttpUtils http = new HttpUtils();
                http.download("http://www.pangpangpig.com/APK/DownLoad/PangPangPig100.apk", file.getAbsolutePath().toString(), true, true, new RequestCallBack<File>() {
                    @Override
                    public void onSuccess(ResponseInfo<File> responseInfo) {
                        Toast.makeText(context, "图片下载成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(context, "连接失败" + e.toString(), Toast.LENGTH_LONG).show();
                        Log.d("lll", "" + e.toString());
                    }
                });
            }
        });

        pd = new ProgressDialog(UpDateActivity.this);
        pd.setTitle("文件下载中");
        pd.setMessage("请稍等");
        pd.setProgressStyle(HorizontalScrollView.SCROLL_AXIS_HORIZONTAL);
        pd.setCancelable(true);
        pd.show();
        new Thread(new Runnable() {
            @Override
            public void run() {

                InputStream is = null;
                OutputStream os = null;

                try {
                    os = new FileOutputStream(file);
                    URL url = new URL("http://www.pangpangpig.com/APK/DownLoad/PangPangPig100.apk");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    is = conn.getInputStream();
                    pd.setMax(conn.getContentLength());
                    byte[] buffer = new byte[80 * 1024];
                    int len = 0;
                    int progressLen = 0;
                    while ((len = is.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                        progressLen += len;
                        pd.setProgress(progressLen);
                    }
                    update();
                    Log.d("KKKK", "success！！！");
                    pd.dismiss();
                    os.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        try {
//            new FileDownloadThread(new URL("http://www.pangpangpig.com/APK/DownLoad/PangPangPig100.apk"),file,start,Integer.MAX_VALUE);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }


    public void update(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "pppig.apk"))
                , "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private File file = new File(Environment.getExternalStorageDirectory(), "pppig.apk");
    private ProgressDialog pd;

    private InputStream getInputStream(String path) {
        InputStream inputStream = null;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5 * 1000);
            inputStream = connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }


    private void inputStreamToFile(InputStream inputStream) throws IOException {
        File file = creatFile("upDateQQ.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[4 * 1024];
        while ((inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, buffer.length);
        }

        Toast.makeText(this, "下载完成", Toast.LENGTH_SHORT).show();
        fos.flush();
        fos.close();
        inputStream.close();

    }

    private File creatFile(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        if (file.exists()) {

        } else {
            file.mkdir();
        }
        return file;
    }


    class FileDownloadThread extends Thread {
        private static final int BUFFER_SIZE = 1024 * 80;
        private URL url;
        private File file;
        private int startPosition;
        private int endPosition;
        private int curPosition;
        //用于标识当前线程是否下载完成
        private boolean finished = false;
        private int downloadSize = 0;

        public FileDownloadThread(URL url, File file, int startPosition, int endPosition) {
            this.url = url;
            this.file = file;
            this.startPosition = startPosition;
            this.curPosition = startPosition;
            this.endPosition = endPosition;
        }

        @Override
        public void run() {
            BufferedInputStream bis = null;
            RandomAccessFile fos = null;
            byte[] buf = new byte[BUFFER_SIZE];
            URLConnection con = null;
            try {
                con = url.openConnection();
                con.setAllowUserInteraction(true);
                //设置当前线程下载的起点，终点
                con.setRequestProperty("Range", "bytes=" + startPosition + "-" + endPosition);
                //使用java中的RandomAccessFile 对文件进行随机读写操作
                fos = new RandomAccessFile(file, "rw");
                //设置开始写文件的位置
                fos.seek(startPosition);
                bis = new BufferedInputStream(con.getInputStream());
                endPosition = con.getContentLength();
                pd.setMax(con.getContentLength());
                //开始循环以流的形式读写文件
                while (curPosition < endPosition) {
                    int len = bis.read(buf, 0, BUFFER_SIZE);
                    if (len == -1) {
                        break;
                    }
                    fos.write(buf, 0, len);
                    curPosition = curPosition + len;
                    start = curPosition;
                    pd.setProgress(start);
                    if (curPosition > endPosition) {
                        downloadSize += len - (curPosition - endPosition) + 1;
                        pd.dismiss();
                    } else {
                        downloadSize += len;
                    }
                }
                //下载完成设为true
                this.finished = true;
                bis.close();
                fos.close();
            } catch (IOException e) {
                Log.d(getName() + " Error:", e.getMessage());
            }
        }

        public boolean isFinished() {
            return finished;
        }

        public int getDownloadSize() {
            return downloadSize;
        }
    }


}


