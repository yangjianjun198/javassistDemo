package com.yjj.learnDemox;

import android.view.View;

/**
 * created by yangjianjun on 2019-11-02
 * 点击manager
 */
public class MyClickManager {
    private static MyClickManager instance = new MyClickManager();

    public static MyClickManager getInstance() {
        return instance;
    }

    public void onBeforeClick(View view) {}

    public void onAfterClick(View view) {}
}
