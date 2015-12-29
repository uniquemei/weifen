package com.example.administrator.myapplication.entity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
public class Sensors {
    private List<Sensor> mData;

    public Sensors(List<Sensor> mData) {
        this.mData = mData;

    }

    public Sensors() {
        super();
        mData = new ArrayList<>();
    }

    public boolean add(Sensor s) {
        for (int i = 0; i < mData.size(); i++) {
            if (s.getDevice_id() == mData.get(i).getDevice_id()) {
                mData.get(i).setDevice_value(s.getDevice_value());
                return false;
            }
        }
        mData.add(s);
        return true;
    }

    //清空数据
    public void clearData() {
        mData.clear();
    }

    //
    public int getCount() {
        return mData.size();
    }

    public Sensor getItem(int i) {
        return mData.get(i);
    }

    public long getItemId(int position) {
        return position;
    }
}
