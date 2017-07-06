package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class DaoMaker {

    public static void main(String[] args){
        Schema schema=new Schema(1,"com.student.entity");
        addStudent(schema);
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            new DaoGenerator().generateAll(schema,"D:\\liu\\study\\MyWork\\app\\src\\main\\java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void addStudent(Schema schema){
        Entity entity=schema.addEntity("Student");
        entity.addIdProperty();
        entity.addStringProperty("name");
        entity.addIntProperty("age");
        entity.addStringProperty("sex");
        entity.addIntProperty("score");
    }

}
