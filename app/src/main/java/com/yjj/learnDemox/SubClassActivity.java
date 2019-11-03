package com.yjj.learnDemox;

import android.os.Bundle;

/**
 * created by yangjianjun on 2019-11-03
 * 测试子继承activity
 */
public class SubClassActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subclass);
    }
}
