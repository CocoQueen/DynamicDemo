package com.example.wissdom.common.base;


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create: 2018/4/24 9:34
 *
 * @author: Coco
 * Description:  activity控制类
 * Version: 1.0
 **/
@SuppressWarnings("unused")
public class ActivityController {
    /**
     * 静态单例内部类
     **/
    private ActivityController() {
    }

    public static ActivityController getInstance() {
        return ActivityController2.t;
    }

    private static class ActivityController2 {
        private static ActivityController t = new ActivityController();
    }

    private List<Activity> mBaseActivities = new ArrayList<>();

    public void addActivity(Activity baseActivity) {
        mBaseActivities.add(baseActivity);
    }

    public void removeActivity(Activity baseActivity) {
        mBaseActivities.remove(baseActivity);
    }

    private Activity getActivity(Class activityClass) {
        for (Activity baseActivity : mBaseActivities) {
            if (baseActivity.getClass() == activityClass) {
                return baseActivity;
            }
        }
        throw new RuntimeException("NO SUCH ACTIVITY FOUND");
    }

    private void killActivity(Class activityClass) {
        for (int i = mBaseActivities.size() - 1; i >= 0; i--) {
            if (activityClass == mBaseActivities.get(i).getClass()) {
                mBaseActivities.get(i).finish();
            }
        }
    }

    public void killAll() {
        for (int i = mBaseActivities.size() - 1; i >= 0; i--) {
            mBaseActivities.get(i).finish();
        }
    }
}
