package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.adapter.ExpandableAdapter;
import com.railwayticket.myapp.mywork.bean.PhoneNumBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowPhoneNumActivity extends BaseActivity {

    private ExpandableListView listView;
    private Context context;
    private ExpandableAdapter adapter;
    private List<PhoneNumBean> list_bean;
    private List<Map<String, String>> list_map_name = new ArrayList<>();
    private List<Map<String, List<String>>> list_map_phoneNums = new ArrayList<>();
    private int flag_int = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_phone_num);
        context = ShowPhoneNumActivity.this;
        listView = (ExpandableListView) findViewById(R.id.listView);
        getData();
        adapter = new ExpandableAdapter(list_map_name, list_map_phoneNums, context);
        listView.setAdapter(adapter);
    }

    private void getData() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            list_bean = new ArrayList<>();
            while (cursor.moveToNext()) {
                PhoneNumBean bean = new PhoneNumBean();
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                if(TextUtils.isEmpty(name)){
                    continue;
                }
                String phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if(TextUtils.isEmpty(phoneNum)){
                    continue;
                }
                bean.setName(name);
                bean.setPhoneNum(phoneNum);
                list_bean.add(bean);
            }
        }

        for (int i = 0; i < list_bean.size(); i++) {
            if (i == 0) {
                map_name = new HashMap<>();
                map_name.put("name", list_bean.get(i).getName());
                list_map_name.add(map_name);
                map_phoneNum = new HashMap<>();
                list_phoneNum = new ArrayList<>();
                list_phoneNum.add(list_bean.get(i).getPhoneNum());
//                map_phoneNum.put("phoneNum", list_phoneNum);
            } else {
                if (list_bean.get(i).getName().equals(list_bean.get(i - 1).getName())) {
                    list_phoneNum.add(list_bean.get(i).getPhoneNum());

                    if (i == list_bean.size() - 1) {
                        map_phoneNum.put("phoneNum", list_phoneNum);
                        list_map_phoneNums.add(map_phoneNum);
                    }
                } else {

                    if (flag_int != -1) {
                        map_phoneNum.put("phoneNum", list_phoneNum);
                        list_map_phoneNums.set(flag_int, map_phoneNum);
//                        list_map_phoneNums.add(flag_int,map_phoneNum);
                    } else {
                        map_phoneNum.put("phoneNum", list_phoneNum);
                        list_map_phoneNums.add(map_phoneNum);

                    }
                    map_phoneNum = new HashMap<>();
                    flag_int=-1;
                    for (int j = 0; j < list_map_name.size() - 1; j++) {
                        if (list_map_name.get(j).get("name").equals(list_bean.get(i).getName())) {
                            flag_int = j;
                            list_phoneNum = list_map_phoneNums.get(j).get("phoneNum");
                        }
                    }
                    if (flag_int == -1) {
                        list_phoneNum = new ArrayList<>();
                        map_name = new HashMap<>();
                        map_name.put("name", list_bean.get(i).getName());
                        list_map_name.add(map_name);
                    }
                    list_phoneNum.add(list_bean.get(i).getPhoneNum());
                    if (i == list_bean.size() - 1) {
                        if (flag_int != -1) {
                            map_phoneNum.put("phoneNum", list_phoneNum);
                            list_map_phoneNums.set(flag_int, map_phoneNum);
//                        list_map_phoneNums.add(flag_int,map_phoneNum);
                        } else {
                            map_phoneNum.put("phoneNum", list_phoneNum);
                            list_map_phoneNums.add(map_phoneNum);

                        }
                    }


                }
            }
        }

    }

    Map<String, String> map_name;
    Map<String, List<String>> map_phoneNum;
    List<String> list_phoneNum;
}
