package com.example.wissdom.moduleb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 *
 * @author：Coco
 * date：2019/3/13
 * version：1.0
 * description:MainActivityB.java
 *  组件与主appApplication冲突与初始化
 *
 */
public class MainActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
    }

    public void goToModuleA(View view) {
        try {
            Class clazz = Class.forName("com.example.wissdom.modulea.MainActivityA");
            Intent intent = new Intent(MainActivityB.this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    public void goToModuleApp(View view) {
        try {
            Class clazz = Class.forName("com.example.wissdom.dynamicdemo.MainActivity");
            Intent intent = new Intent(MainActivityB.this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }
}
