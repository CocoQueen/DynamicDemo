package com.example.wissdom.modulea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wissdom.common.base.BaseActivity;
import com.github.mzule.activityrouter.annotation.Router;

/**
 * @author：Coco date：2019/3/13
 * version：1.0
 * description:moduleA_project
 * lib组件与主appAndroidManifest合并
 */
@Router("test")
public class MainActivityA extends BaseActivity {

    @Override
    public String initActionBar() {
        return null;
    }

    @Override
    public int getRootView() {
        return R.layout.moudulea_activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try{
//            String test = getIntent().getExtras().getString("test");
//            Toast.makeText(this,test , Toast.LENGTH_SHORT).show();
//        }catch (Exception e){
//
//        }

    }

    public void goToModuleB(View view) {
//        try {
//            Class clazz = Class.forName("com.example.wissdom.moduleb.MainActivityB");
//            Intent intent = new Intent(MainActivityA.this, clazz);
//            startActivity(intent);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        finish();
    }

    public void goToModuleApp(View view) {
//        try {
//            Class clazz = Class.forName("com.example.wissdom.dynamicdemo.MainActivity");
//            Intent intent = new Intent(MainActivityA.this, clazz);
//            startActivity(intent);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        finish();
    }
}
