package com.example.administrator.myapplication;

import android.app.Application;

import com.example.administrator.myapplication.entity.Sensor;
import com.example.administrator.myapplication.entity.SensorEvent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/12/16.
 */
public class MainApplication extends Application {
    private Sensor s;
    private static Sensor sensor;
    private static MainApplication application;
    static boolean flag;

    @Override
    public void onCreate() {
        super.onCreate();
        s = new Sensor();
        application = this;
        SensorUtility.setRemoteAddr("127.0.0.1", 51001);
//      SensorUtility.setRemoteAddr("192.168.1.232", 51001);
        SensorUtility.reStartReadThread();
    }

    public static MainApplication getApplication() {
        return application;
    }

    //解析JSON字符串
    public static Sensor JSONAnalysis(SensorEvent sensorEvent) {
        if (sensorEvent.jstr.equals("{\"err_msg\":\"success\"}") || sensorEvent == null) {
            flag = false;
        } else {
            flag = true;
        }
        if (flag) {
            try {
                JSONObject object = new JSONObject(sensorEvent.jstr);
                int device_id = object.getInt("device_id");
                int device_type = object.getInt("device_type");
                String device_value = object.getString("device_value");
                String transfer_type = object.getString("transfer_type");
                String timestamp = object.getString("timestamp");
                sensor = new Sensor(device_id, device_type, transfer_type, device_value,
                        timestamp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            sensor = null;
        }
        return sensor;
    }

    //转换device_type与type的映射，例如“16”对应“温度”
    public Sensor getDeviceType(Sensor s) {
        if (s.getDevice_type() == 16) {
            s.setType("温度");
            return s;
        } else if (s.getDevice_type() == 17) {
            s.setType("湿度");
            return s;
        } else if (s.getDevice_type() == 18) {
            s.setType("光照");
            return s;
        } else if (s.getDevice_type() == 19) {
            s.setType("可燃气体");
            return s;
        } else if (s.getDevice_type() == 20) {
            s.setType("人体红外");
            return s;
        } else if (s.getDevice_type() == 22) {
            s.setType("气压");
            return s;
        } else if (s.getDevice_type() == 29) {
            s.setType("烟雾");
            return s;
        } else if (s.getDevice_type() == 26) {
            s.setType("二氧化碳");
            return s;
        } else if (s.getDevice_type() == 30) {
            s.setType("超声波");
            return s;
        } else if (s.getDevice_type() == 21) {
            s.setType("加速度");
            return s;
        } else if (s.getDevice_type() == 28) {
            s.setType("颜色");
            return s;
        } else if (s.getDevice_type() == 32) {
            s.setType("磁场");
            return s;
        } else if (s.getDevice_type() == 24) {
            s.setType("继电器");
            return s;
        } else if (s.getDevice_type() == 33) {
            s.setType("步进电视");
            return s;
        } else if (s.getDevice_type() == 25) {
            s.setType("直流电机");
            return s;
        } else if (s.getDevice_type() == 31) {
            s.setType("数码管");
            return s;
        } else if (s.getDevice_type() == 34) {
            s.setType("红外反射");
            return s;
        } else if (s.getDevice_type() == 35) {
            s.setType("触摸按键");
            return s;
        } else if (s.getDevice_type() == 36) {
            s.setType("声音");
            return s;
        } else if (s.getDevice_type() == 37) {
            s.setType("雨滴");
            return s;

        } else if (s.getDevice_type() == 38) {
            s.setType("火焰");
            return s;
        } else if (s.getDevice_type() == 39) {
            s.setType("震动");
            return s;
        } else if (s.getDevice_type() == 40) {
            s.setType("RFID125K");
            return s;
        } else if (s.getDevice_type() == 41) {
            s.setType("RFID13.56M");
            return s;
        } else if (s.getDevice_type() == 23) {
            s.setType("红外遥控");
            return s;
        } else if (s.getDevice_type() == 65) {
            s.setType("温湿度合并");
            return s;
        } else if (s.getDevice_type() == 67) {
            s.setType("PH酸碱度");
            return s;
        } else {
            s.setType("网关");
            return s;
        }
    }
}
