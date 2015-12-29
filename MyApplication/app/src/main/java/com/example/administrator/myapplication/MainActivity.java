package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.adapter.SensorAdapter;
import com.example.administrator.myapplication.entity.Sensor;
import com.example.administrator.myapplication.entity.SensorEvent;
import com.example.administrator.myapplication.entity.Sensors;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity implements View.OnClickListener, SensorAdapter.onCheckBoxClickListenner {
    private DrawerLayout drawerLayout;
    private ActionBar actionBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Intent intent;
    private TextView sensor_registration;
    private TextView weifenManage;
    private TextView exit;
    private TextView netWorkSetting;
    private AppManager appManager;
    private ListView myList;
    SensorAdapter adapter;
    Sensors mData;
    TextView sensor_text;
    boolean flag = false;
    String remoteIp;
    int remotePort;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        //生成返回按钮
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("传感器");
        initData();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.close, R.string.open);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        sensor_registration.setOnClickListener(this);
        weifenManage.setOnClickListener(this);
        exit.setOnClickListener(this);
        appManager.getInstance().addActivity(this);
        adapter.setCkeckBoxListenner(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    //数据的初始化
    public void initData() {
        sensor_text = (TextView) findViewById(R.id.sensor_text);
        drawerLayout = (DrawerLayout) findViewById(R.id.draw);
        sensor_registration = (TextView) findViewById(R.id.sensor_registration);
        weifenManage = (TextView) findViewById(R.id.weifenManage);
        exit = (TextView) findViewById(R.id.exit);

        myList = (ListView) findViewById(R.id.list_sensor);
        mData = new Sensors();
        adapter = new SensorAdapter(mData, this);
        myList.setAdapter(adapter);
    }

    //抽屉布局需要重写onOptionsItemSelected（），onConfigurationChanged（）方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item);
    }

    // 设备更改的时候
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sensor_registration:
                intent = new Intent(this, SensorRegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.weifenManage:
                intent = new Intent(this, CloudManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                appManager.getInstance().finishAll();
                SensorUtility.reConnect();
                SensorUtility.stopReadThread();
                break;
        }
    }

    public void onEventMainThread(SensorEvent s) {
        sensor = MainApplication.JSONAnalysis(s);
        mData.add(sensor);
        adapter.notifyDataSetChanged();
    }

    //反注册
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //对checkbox进行监听
    @Override
    public void checkBoxClick(Sensor s) {
        int id = s.getDevice_id();
        try {
            JSONObject object = new JSONObject();
            object.put("cmd", "set_switch");
            JSONObject args = new JSONObject();
            args.put("device_id", id);
            args.put("device_type", 24);
            args.put("transfer_type", "zigbee");
            args.put("device_value", "true");
            object.put("args", args);
            SensorUtility.sendCmd(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void noCheckBoxClick(Sensor s) {
        int id = s.getDevice_id();
        try {
            JSONObject object = new JSONObject();
            object.put("cmd", "set_switch");
            JSONObject args = new JSONObject();
            args.put("device_id", id);
            args.put("device_type", 24);
            args.put("transfer_type", "zigbee");
            args.put("device_value", "false");
            object.put("args", args);
            SensorUtility.sendCmd(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }
}
