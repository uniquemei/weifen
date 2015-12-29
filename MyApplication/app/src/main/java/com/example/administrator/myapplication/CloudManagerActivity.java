package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myapplication.entity.Sensor;
import com.example.administrator.myapplication.sensor_uuid.UUidDao;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CloudManagerActivity extends Activity implements View.OnClickListener {
    private ImageView back_cloud_manager;
    private AppManager appManager;
    EditText cloudAdres;
    EditText cloudPort;
    CheckBox checkBox;
    String address;
    String port;
    Button cloudSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_manager);
        initData();
        back_cloud_manager.setOnClickListener(this);
        appManager.getInstance().addActivity(this);
        cloudSave.setOnClickListener(this);
    }

    //数据的初始化
    public void initData() {
        back_cloud_manager = (ImageView) findViewById(R.id.back_cloud_manager);
        cloudAdres = (EditText) findViewById(R.id.cloudAdres);
        cloudPort = (EditText) findViewById(R.id.cloudPort);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        cloudSave = (Button) findViewById(R.id.cloudSave);
        //初始化button状态
        cloudSave.setEnabled(false);
        //对checkbox设置监听
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cloudSave.setEnabled(true);
                } else {
                    cloudSave.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_cloud_manager:
                finish();
                break;
            case R.id.cloudSave:
                if (checkBox.isChecked()) {
                    UUidDao dao = new UUidDao(this);
                    List<Sensor> list = dao.findSensor();
                    for (int i = 0; i < list.size(); i++) {
                        int deviceId = list.get(i).getDevice_id();
                        String uid = list.get(i).getUuid();
                        try {
                            JSONObject object = new JSONObject();
                            object.put("cmd", "add_uuid");
                            JSONObject args = new JSONObject();
                            args.put("device_id", deviceId);
                            args.put("uuid", uid);
                            object.put("args", args);
                            SensorUtility.sendCmd(object);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    //将数据上传至后台
                    try {
                        JSONObject object = new JSONObject();
                        object.put("cmd", "connect_to_platform");
                        JSONObject args = new JSONObject();
                        args.put("ip", "192.168.1.214");
                        args.put("port", 9900);
                        object.put("args", args);
                        SensorUtility.sendCmd(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(this, "上传成功！", Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }
}
