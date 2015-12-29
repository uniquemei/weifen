package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

/**
 * Created by Administrator on 2015/12/15.
 */
public class AppManager {
    private AppManager() {};
    private static AppManager instance;

    public static AppManager getInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    synchronized (AppManager.class) {
                        instance = new AppManager();
                    }
                }
            }
        }
        return instance;
    }

    //定义一个用来存储activity集合的栈
    private Stack<Activity> activityStack;

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    //消灭当前的activity
    public void finishLastActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    //消灭所有的activity
    public void finishAll() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
    }
}
