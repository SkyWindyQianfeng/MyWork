package com.railwayticket.myapp.mywork.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.bean.SmsMessageContent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianyuanyuan on 2016/8/22.
 */
public class GetPhoneNum extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private List list;
    private ArrayAdapter<String> arrayAdapter;
    private Cursor cursor;
    private ArrayList<SmsMessageContent> listMessage;

    private Button btn_checkBook;
    private Button btn_call;
    private Button btn_sendMessage;
    private Button btn_checkMessage;
    private Context context;

    private final int CALL_REQUEST_CODE = 2;
    private final int SEND_MESSAGE_REQUEST_CODE = 3;
    private final int CHECK_MESSAGE_REQUEST_CODE = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("lau", "ssOnCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_get_phone_num);
        context = GetPhoneNum.this;
        initView();
        setListener();


//        getData();
//        arrayAdapter = new ArrayAdapter<String>(GetPhoneNum.this, android.R.layout.simple_list_item_1, list);
//        setListAdapter(arrayAdapter);
//        getListView().setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lau","onResume");
    }

    private void setListener() {
        btn_checkBook.setOnClickListener(this);
        btn_sendMessage.setOnClickListener(this);
        btn_call.setOnClickListener(this);
        btn_checkMessage.setOnClickListener(this);
    }

    private void initView() {
        btn_checkBook = (Button) findViewById(R.id.btn_checkBook);
        btn_call = (Button) findViewById(R.id.btn_call);
        btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);
        btn_checkMessage = (Button) findViewById(R.id.btn_checkMessage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        String[] contacts = getPhoneContacts(uri);
        if (requestCode == CALL_REQUEST_CODE && resultCode == RESULT_OK) {

            Log.d("FFFF", contacts[0] + "++++++" + contacts[1]);


            //打电话功能实现
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contacts[1]));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        } else if (requestCode == SEND_MESSAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            //发送短信（跳转至短信界面的实现）
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto://" + contacts[1]));
            intent.putExtra("sms_body", "短信内容");
            startActivity(intent);

            //发送短信（无需跳转）
            /*SmsManager smsManager = SmsManager.getDefault();
            String content = "很久以前在知乎上看过一个问答，对比几十年前，有哪些消失的职业 当时看了几个答案就挺感慨，今天在路上又想起来一个。在大约20多年前的时候，收水费和查电表绝对是个好职业，挨家挨户去转着看一眼多少字了，收收钱。基本不会特别辛苦。 但今天早上的时候才意识到这个职业真的要快消失了，现在电卡都已经是远程上网的";
            List<String> texts = smsManager.divideMessage(content);
            for (int i = 0; i < texts.size(); i++) {
                smsManager.sendTextMessage(contacts[1], null, texts.get(i), null, null);
            }*/

        } else if (requestCode == CHECK_MESSAGE_REQUEST_CODE && resultCode == RESULT_OK) {

//            Toast.makeText(GetPhoneNum.this,contacts[1],Toast.LENGTH_LONG).show();
            Uri uriSms = Uri.parse("content://sms/");
            ContentResolver contentResolver = getContentResolver();
            String where = "address=" + contacts[1];
            Cursor smsContent = contentResolver.query(uriSms, null, where, null, null);
            listMessage = new ArrayList<>();
            if (smsContent != null) {
                while (smsContent.moveToNext()) {

                    SmsMessageContent content = new SmsMessageContent();
                    String name = smsContent.getString(smsContent.getColumnIndex("person"));
                    String phoneNum = smsContent.getString(smsContent.getColumnIndex("address"));
                    String body = smsContent.getString(smsContent.getColumnIndex("body"));
//                    Toast.makeText(GetPhoneNum.this, body, Toast.LENGTH_LONG).show();
                    content.setBody(body);
                    content.setName(name);
                    content.setPhoneNum(phoneNum);
                    listMessage.add(content);
                }

//                for (int i = 0; i < listMessage.size(); i++) {
//                    Log.d("MMMMMM",listMessage.get(i).toString());
//                }

                Intent intent = new Intent(GetPhoneNum.this, ShowSmsMessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", listMessage);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //获取电话号码
            int phoneNumIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            contact[1] = cursor.getString(phoneNumIndex);
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            contact[0] = cursor.getString(nameIndex);
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_checkBook:
                intent = new Intent(context, ShowPhoneNumActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_call:
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, CALL_REQUEST_CODE);
                break;
            case R.id.btn_sendMessage:
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, SEND_MESSAGE_REQUEST_CODE);
                break;
            case R.id.btn_checkMessage:
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, CHECK_MESSAGE_REQUEST_CODE);
                break;
        }
    }
}
