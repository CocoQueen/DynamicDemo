package com.example.wissdom.moduleb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
