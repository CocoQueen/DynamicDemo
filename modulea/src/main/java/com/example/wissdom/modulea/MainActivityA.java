package com.example.wissdom.modulea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * @author：Coco date：2019/3/13
 * version：1.0
 * description:moduleA_project
 * lib组件与主appAndroidManifest合并
 */
public class MainActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moudulea_activity_main);
        try{
            String test = getIntent().getExtras().getString("test");
            Toast.makeText(this,test , Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }

    }

    public void goToModuleB(View view) {
        try {
            Class clazz = Class.forName("com.example.wissdom.moduleb.MainActivityB");
            Intent intent = new Intent(MainActivityA.this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    public void goToModuleApp(View view) {
        try {
            Class clazz = Class.forName("com.example.wissdom.dynamicdemo.MainActivity");
            Intent intent = new Intent(MainActivityA.this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }
}
