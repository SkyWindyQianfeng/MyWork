package com.railwayticket.myapp.mywork.utils;

import android.content.Context;

import com.student.entity.Student;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5 0005.
 */
public class CommonUtil {

    private GreenDaoManager manager;
    public CommonUtil(Context context){
        manager=GreenDaoManager.getInstance();
        manager.init(context);
    }


    public boolean insertStudent(Student student){
        boolean flag=false;
        flag=manager.getDaoSession().insertOrReplace(student)!=-1?true:false;
        return flag;

    }

    public boolean insertListStudent(List<Student> list){
        boolean flag=false;

        try{
            for(Student student:list){
                manager.getDaoSession().insertOrReplace(student);
            }
            flag=true;
        }catch (Exception e){}

        return flag;
    }



    public boolean deleteStudent(Student student){
        boolean flag=false;
        try {
        manager.getDaoSession().delete(student);
            flag=true;
        }catch (Exception e){}
        return flag;
    }

    public boolean deleteListStudent(List<Student> list){
        boolean flag=false;

        try {
            for (Student student:list){
                manager.getDaoSession().delete(student);
            }
        }catch (Exception e){

        }
        return flag;
    }


    public boolean updateStudent(Student student){
        boolean flag=false;
        try {
        manager.getDaoSession().update(student);
            flag=true;
        }catch (Exception e){}
        return flag;
    }

    public boolean updateListStudent(List<Student> list){
        boolean flag=false;
        try {

            for (Student student:list){
                manager.getDaoSession().update(student);
            }

            flag=true;
        }catch (Exception e){}
        return flag;
    }


    public List<Student> loadList(){

        List<Student> list=new ArrayList<>();
        list=manager.getDaoSession().loadAll(Student.class);
        return list;
    }

    public Student loadStudent(long key){
        Student load = manager.getDaoSession().load(Student.class, key);
        return load;
    }



    public void queryDB(){
        QueryBuilder<Student> studentQueryBuilder = manager.getDaoSession().getStudentDao().queryBuilder();
    }


}
