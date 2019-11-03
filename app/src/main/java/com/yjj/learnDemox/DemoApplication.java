package com.yjj.learnDemox;

import android.app.Application;
import android.util.Log;

import com.yjj.lifemethodlib.LifeMethodRecorder;

/**
 * created by yangjianjun on 2019-11-03
 * todo something
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LifeMethodRecorder.getInstance().register(new LifeMethodRecorder.LifeTimeListener() {
            @Override
            public void onRecord(String actName, String method, long costTime) {
                Log.d("LifeRecordKit", getRecordDesc(actName, method, costTime));
            }
        });
    }

    private String getRecordDesc(String actName, String method, long costTime) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(actName);
        sb.append("] ");
        sb.append("<");
        sb.append(method);
        sb.append("> cost time: ");
        sb.append((costTime * 1D / 1000));
        sb.append("ms");
        return sb.toString();
    }
}
