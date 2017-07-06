package com.railwayticket.myapp.mywork.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.utils.PhotoUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static anet.channel.util.Utils.context;

/**
 * 参考http://www.360doc.com/content/12/0706/16/7471983_222642047.shtml
 */
public class TakePhotoActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private ImageView iv_photo;
    private Button btn_takePhoto;
    private Button btn_photo;
    private final int FROM_ALBUM = 10;//从相册选取
    private final int FROM_CAMERA = 11;//拍照获取
    private static Bitmap bitmap;
    private int imageSizeBig = 1;
    private int imageSizeSmall = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        btn_photo = (Button) findViewById(R.id.btn_photo);
        btn_takePhoto = (Button) findViewById(R.id.btn_takePhoto);
        iv_photo.setOnClickListener(this);
        btn_photo.setOnClickListener(this);
        btn_takePhoto.setOnClickListener(this);
        iv_photo.setOnTouchListener(this);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (TakePhotoActivity.this.getResources().getDisplayMetrics().density *100), View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (TakePhotoActivity.this.getResources().getDisplayMetrics().density *100), View.MeasureSpec.EXACTLY);
        iv_photo.measure(widthMeasureSpec,heightMeasureSpec);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_photo:
                if (isClick) {
                    Intent intent = new Intent();
                    intent.setClass(TakePhotoActivity.this, CheckImgActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.btn_photo:
                getPhotoFromAlbum();
                break;
            case R.id.btn_takePhoto:
                getPhotoFromCamera();
                break;
        }
    }

    /**
     * 拍照选取照片
     */
    private void getPhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "liuxueyu.jpg")));
        startActivityForResult(intent, FROM_CAMERA);
    }

    /**
     * 从相册中选取照片
     */
    private void getPhotoFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, FROM_ALBUM);
    }


    /**
     * 图片剪切功能
     *
     * @param uri
     */
    private static final String IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg";//temp file
    Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);//The Uri to store the big bitmap

    private void shellPic(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 800);
        intent.putExtra("outputY", 800);
        intent.putExtra("return-data", false);

        intent.putExtra("scale", true);

//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.putExtra("noFaceDetection", true); // no face detection

        startActivityForResult(intent, 5);


    }


    @Override
    protected void onResume() {
        super.onResume();
        iv_photo.setImageBitmap(bitmap);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FROM_CAMERA && resultCode == RESULT_OK) {
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "liuxueyu.jpg"));
            shellPic(uri);
            /**
             * 图片压缩
             */
//            bitmap = PhotoUtils.getSmallBitmap(uri.getPath(), 768, 1280);
//            iv_photo.setImageBitmap(bitmap);
//            File file = new File(Environment.getExternalStorageDirectory(), "liuxueyusmall.jpg");
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 60
//                        , fos);
//                fos.flush();
//                fos.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            /*Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            iv_photo.setImageBitmap(bitmap);*/

        }
        if (requestCode == FROM_ALBUM && resultCode == RESULT_OK) {
            /**
             * 在相册中选择图片，获取到选择图片的uri
             * 然后使用该uri进入系统的图片裁剪功能shellPic（uri）
             */
            if (data != null) {
                Uri uri = data.getData();
                shellPic(uri);


                /**
                 *
                 *
                 * 图片的压缩
                 */
//                try {
//                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
////
//                    iv_photo.setImageBitmap(bitmap);
//                    File file = new File(Environment.getExternalStorageDirectory(), "liuxueyualbum.jpg");
//
//                    FileOutputStream fos = new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100
//                            , fos);
//                    bitmap = PhotoUtils.getSmallBitmap(file.getPath(), 768, 1280);
//
//                    File file1 = new File(Environment.getExternalStorageDirectory(), "liuxueyualbum2.jpg");
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60
//                            , new FileOutputStream(file1));
//
//                    RequestParams params=new RequestParams();
//
//                    HttpUtils utils=new HttpUtils();
//
//                    fos.flush();
//                    fos.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//

                /**
                 * 若不需要剪切的话，就不需要实现shellPic（uri）方法
                 * 直接使用contentResolver通过uri来获取到图片展示就可以了
                 *
                 * ContentResolver contentResolver=getContentResolver();
                 try {
                 bitmap= BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                 iv_photo.setImageBitmap(bitmap);
                 } catch (FileNotFoundException e) {
                 e.printStackTrace();
                 }
                 Log.d("DDDD", data.getData().toString());*/
            }
        }
        if (requestCode == 5 && resultCode == RESULT_OK) {
            /**
             *获取到剪切后的图片，展示在ImageView控件上
             */
            Bundle bundle = data.getExtras();
            if (imageUri != null) {
                bitmap = decodeUriAsBitmap(imageUri);
                iv_photo.setImageBitmap(bitmap);
            }
        }
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {

        Bitmap bitmap = null;

        try {

            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));

        } catch (FileNotFoundException e) {

            e.printStackTrace();

            return null;

        }

        return bitmap;

    }


    int screenWidth;
    int screenHeight;
    int lastX;
    int lastY;

    int beginX;
    int beginY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isClick = false;
        int action = event.getAction();
        Log.i("@@@@@@", "Touch:" + action);
        //Toast.makeText(DraftTest.this, "λ�ã�"+x+","+y, Toast.LENGTH_SHORT).show();
        switch (action) {

            case MotionEvent.ACTION_DOWN:

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                beginX = (int) event.getRawX();
                beginY = (int) event.getRawY();


                Log.d("aaa", "X==" + beginX + "Y==" + beginY);
                break;
            /**
             * layout(l,t,r,b)
             * l  Left position, relative to parent
             t  Top position, relative to parent
             r  Right position, relative to parent
             b  Bottom position, relative to parent
             * */
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;
                if (left < 0) {
                    left = 0;
                    right = left + v.getWidth();
                }
                if (right > screenWidth) {
                    right = screenWidth;
                    left = right - v.getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + v.getHeight();
                }
                if (bottom > screenHeight) {
                    bottom = screenHeight;
                    top = bottom - v.getHeight();
                }
                v.layout(left, top, right, bottom);
                Log.i("@@@@@@", "position��" + left + ", " + top + ", " + right + ", " + bottom);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:

                Log.d("aaa", "X==UP" + beginX + "Y==UP" + beginY);
                Log.d("aaa", "UPX==" + event.getRawX() + "UPY==" + event.getRawY());


                Toast.makeText(TakePhotoActivity.this, "x++" + Math.abs(event.getRawX() - beginX) + "Y++" + Math.abs(event.getRawY() - beginY), Toast.LENGTH_SHORT).show();
                if (Math.abs(event.getRawX() - beginX) < 5 && Math.abs(event.getRawY() - beginY) < 5) {
                    isClick = true;
                }

                break;
        }

        Log.d("aaa", "isClick++++++" + isClick);

        return false;

    }


    boolean isClick = false;


}

