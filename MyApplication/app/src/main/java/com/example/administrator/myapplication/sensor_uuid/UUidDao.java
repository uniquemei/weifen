package com.example.administrator.myapplication.sensor_uuid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.myapplication.entity.Sensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/21.
 */
public class UUidDao {
    private DBHelper db;

    public UUidDao(Context context) {
        db = new DBHelper(context);
    }

    //保存用户，执行插入操作
    public void doInsert(Sensor sensor) {
        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SystemContant.UUIDTable.DEVICEID, sensor.getDevice_id());
        values.put(SystemContant.UUIDTable.UUID, sensor.getUuid());
        database.insert(SystemContant.UUIDTable.TABLENAME, null, values);
        database.close();
    }

    //判断用户是否存在
    public boolean checkId(String uuid) {
        boolean flag = false;
        SQLiteDatabase database = db.getWritableDatabase();
        Cursor c = database.query(SystemContant.UUIDTable.TABLENAME, new String[]{SystemContant.UUIDTable.DEVICEID, SystemContant.UUIDTable.UUID},
                SystemContant.UUIDTable.UUID + "=?",
                new String[]{uuid}, null, null, null);
        if (c.moveToNext()) {
            flag = true;
        } else {
            flag = false;
        }
        c.close();
        database.close();
        return flag;
    }

    //删除原来存在的deviceid
    public void del(Sensor s) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(SystemContant.UUIDTable.TABLENAME, SystemContant.UUIDTable.UUID + " =?",
                new String[]{s.getUuid()});
        db.close();
    }

    // 更新用户，先删除原用户，在插入用户
    public void saveOrupdateUser(Sensor s) {
        if (checkId(s.getUuid())) {
            del(s);
        }
        doInsert(s);
    }

    //删除所有的，清空表
    public void delAll() {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(SystemContant.UUIDTable.TABLENAME, null, null);
        db.close();
    }

    //查找
    public List<Sensor> findSensor() {
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(SystemContant.UUIDTable.TABLENAME, new String[]
                {SystemContant.UUIDTable.UUID, SystemContant.UUIDTable.DEVICEID}, null, null, null, null, null);

        List<Sensor> sensors = new ArrayList<Sensor>();
        while (c.moveToNext()) {
            Sensor s = new Sensor();
            s.setUuid(c.getString(c.getColumnIndex(SystemContant.UUIDTable.UUID)));
            s.setDevice_id(c.getInt(c.getColumnIndex(SystemContant.UUIDTable.DEVICEID)));
            sensors.add(s);
        }
        c.close();
        database.close();
        return sensors;
    }
}
