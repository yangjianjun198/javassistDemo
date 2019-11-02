package com.yjj.learnDemox;

import android.app.Activity;
import android.util.Log;

/**
 * created by yangjianjun on 2019-11-02
 * life method
 */
public class LifeMethodRecorder {
    private long lastStartTime;
    private String lastMethodName;

    public static LifeMethodRecorder getInstance() {
        return LifeMethodRecorderViewHolder.instance;
    }

    public void start(Activity act, String methodName) {
        this.lastMethodName = methodName;
        lastStartTime = System.currentTimeMillis();
    }

    public void end(Activity activity) {
        long diff = System.currentTimeMillis() - lastStartTime;
        Log.d("LifeMethodRecorder", lastMethodName + " cost time:" + ((float)diff / 1000) + "ms");
    }

    private static class LifeMethodRecorderViewHolder {
        private static LifeMethodRecorder instance = new LifeMethodRecorder();
    }
}
