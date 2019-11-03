package com.yjj.lifemethodlib;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by yangjianjun on 2019-11-02
 * life method
 */
public class LifeMethodRecorder {
    private Map<Integer, Record> cacheRecordMap;
    private List<LifeTimeListener> lifeTimeListeners;

    public static LifeMethodRecorder getInstance() {
        return LifeMethodRecorderViewHolder.instance;
    }

    public interface LifeTimeListener {
        void onRecord(String actName, String method, long costTime);
    }

    LifeMethodRecorder() {
        cacheRecordMap = new HashMap<>(8);
    }

    public void start(Activity act, String methodName) {
        int actHashCode = System.identityHashCode(act);
        if (cacheRecordMap.containsKey(actHashCode)) {
            cacheRecordMap.get(actHashCode).incRefs();
            return;
        }
        cacheRecordMap.put(actHashCode,
            new Record(hashCode(), System.currentTimeMillis(), methodName,
                act.getClass().getSimpleName()));
    }

    public void end(Activity act) {
        int actHashCode = System.identityHashCode(act);
        if (!cacheRecordMap.containsKey(actHashCode)) {
            return;
        }
        Record record = cacheRecordMap.get(actHashCode);
        if (record.refs == 0) {
            notifyTimeListener(record);
            cacheRecordMap.remove(actHashCode);
        } else {
            record.decRefs();
        }
    }

    public void register(LifeTimeListener listener) {
        if (lifeTimeListeners == null) {
            lifeTimeListeners = new ArrayList<>(8);
        }
        if (listener == null || lifeTimeListeners.contains(listener)) {
            return;
        }
        lifeTimeListeners.add(listener);
    }

    public void unRegister(LifeTimeListener listener) {
        if (lifeTimeListeners == null || listener == null) {
            return;
        }
        lifeTimeListeners.remove(listener);
    }

    private void notifyTimeListener(Record record) {
        if (lifeTimeListeners == null) {
            return;
        }
        long diff = System.currentTimeMillis() - record.time;
        for (LifeTimeListener listener : lifeTimeListeners) {
            listener.onRecord(record.actName, record.name, diff);
        }
    }

    private static class LifeMethodRecorderViewHolder {
        private static LifeMethodRecorder instance = new LifeMethodRecorder();
    }

    static class Record {
        private int key;
        private long time;
        private String name;
        private String actName;
        private int refs = 0;

        public Record(int key, long time, String name, String actName) {
            this.key = key;
            this.time = time;
            this.name = name;
            this.actName = actName;
        }

        public void incRefs() {
            this.refs++;
        }

        public void decRefs() {
            this.refs--;
        }
    }
}
