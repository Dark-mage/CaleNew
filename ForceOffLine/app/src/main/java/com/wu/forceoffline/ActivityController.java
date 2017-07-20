package com.wu.forceoffline;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wu on 2017/7/20.
 */

public class ActivityController {
    private static List<Activity> activityList = new ArrayList<>();
    public  static void  addActivity(Activity activity) {
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }
    public static void closeAllActivity(){
        for(Activity activity:activityList){
            if(activity!=null&&!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
