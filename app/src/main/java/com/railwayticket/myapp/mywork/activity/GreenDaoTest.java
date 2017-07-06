package com.railwayticket.myapp.mywork.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.railwayticket.myapp.mywork.R;
import com.railwayticket.myapp.mywork.utils.CommonUtil;
import com.student.entity.Student;

import java.util.List;

public class GreenDaoTest extends BaseActivity {
    private Button insert;
    private Context context;
    private CommonUtil util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_test);
        insert= (Button) findViewById(R.id.btn_insert);
        util=new CommonUtil(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GreenDaoTest.this,"数据添加成功",Toast.LENGTH_SHORT).show();
                for(int i=0;i<10;i++){
                    Student student=new Student();
                    student.setAge(10+i);
//                student.setId(2008l);
                    student.setName("小睿睿"+i);
                    student.setScore(100-i);
                    student.setSex("男");
                    util.insertStudent(student);

                }
                }
        });
    }


    public void selectData(View view){
        List<Student> students = util.loadList();
        Log.i("dao", students.toString());
    }

    public void selectOneData(View view){
        Log.i("dao",util.loadStudent(11)+"");
    }

//    public void selectAllData(View view){
//
//    }

    public void updateData(View view){
        Student student=new Student();
        student.setId(1l);
        student.setSex("girl");
        student.setScore(101);
        student.setName("link222");
        student.setAge(28);
        util.updateStudent(student);
    }


}
