package com.example.wissdom.dynamicdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author：Coco date：2019/3/13
 * version：1.0
 * description:
 * first_commit:组件化第三方SDK版本控制
 * <p>
 * 使用Intent进行跳转通信（一般使用ActivityRouter与EventBus）
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToModuleA(View view) {
        try {
            Class clazz = Class.forName("com.example.wissdom.modulea.MainActivityA");
            Intent intent = new Intent(MainActivity.this, clazz);
            intent.putExtra("test", "123");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

    private void goToClassForName(String packageName) {
        try {
            Class clazz = Class.forName(packageName);
            Intent intent = new Intent(MainActivity.this, clazz);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void goToModuleB(View view) {
        goToClassForName("com.example.wissdom.moduleb.MainActivityB");
        finish();
    }
}
