package com.railwayticket.myapp.mywork.utils;

import android.content.Context;

import com.student.dao.DaoMaster;
import com.student.dao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * 1、创建数据库
 * 2、创建数据库的表
 * 3、包括对数据库的增删改查
 * 4、对数据库的升级
 * Created by Administrator on 2017/4/5 0005.
 */
public class GreenDaoManager {

    private  static final String TAG=GreenDaoManager.class.getSimpleName();
    private  volatile static GreenDaoManager manager;
    private static final String DB_NAME="mydb.sqlite";
    private static DaoMaster.DevOpenHelper helper;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;

    public void init(Context context){
        this.context=context;
    }
    public static GreenDaoManager getInstance(){
        GreenDaoManager instance=null;
        if(manager==null){
            synchronized (GreenDaoManager.class){
                if(instance==null){
            instance=new GreenDaoManager();
manager=instance;
                }
            }
        }

        return instance;
    }

    public DaoMaster getDaoMaster(){
        if(daoMaster==null){
            DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(context,DB_NAME,null);
            daoMaster=new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }


    public DaoSession getDaoSession(){
        if(daoSession==null){
            if(daoMaster==null){
                daoMaster=getDaoMaster();
            }
            daoSession=daoMaster.newSession();
        }
        return daoSession;
    }

    public void setDebug(){
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES=true;
    }


    /**
     * 关闭数据库连接，以免造成资源浪费
     */
    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper(){
        if(helper!=null){
            helper.close();
            helper=null;
        }
    }


    public void closeDaoSession(){
        if(daoSession!=null){
            daoSession.clear();
            daoSession=null;
        }
    }

}
