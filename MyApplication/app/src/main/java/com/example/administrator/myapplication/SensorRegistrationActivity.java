package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.administrator.myapplication.adapter.PopSensorAdapter;
import com.example.administrator.myapplication.entity.Sensor;
import com.example.administrator.myapplication.entity.SensorEvent;
import com.example.administrator.myapplication.entity.Sensors;
import com.example.administrator.myapplication.sensor_uuid.UUidDao;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;


public class SensorRegistrationActivity extends Activity implements View.OnClickListener {
    private Button add;
    private ImageView back_sensor_registration;
    private AppManager appManager;
    LinearLayout linearLayout;
    PopupWindow popupWindow;
    ListView sensorList;
    ImageView backPop;
    Sensors mData;
    PopSensorAdapter adapter;
    Sensor s;
    EditText textSenId;//传感器的编号
    EditText editTyId;//类型编号
    EditText editSenName;//传感器名
    EditText editUUID;//平台UUID
    String uuid;
    int sendid;
    Sensor sensor;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_registration);
        initData();
        back_sensor_registration.setOnClickListener(this);
        appManager.getInstance().addActivity(this);
        linearLayout.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册
        EventBus.getDefault().register(this);
    }

    //数据的初始化
    public void initData() {
        add = (Button) findViewById(R.id.add_registration);
        textSenId = (EditText) findViewById(R.id.textSenId);
        editTyId = (EditText) findViewById(R.id.editTyId);
        editSenName = (EditText) findViewById(R.id.editSenName);
        editUUID = (EditText) findViewById(R.id.editUUID);
        back_sensor_registration = (ImageView) findViewById(R.id.back_sensor_registration);
        linearLayout = (LinearLayout) findViewById(R.id.select_image);
        mData = new Sensors();
        adapter = new PopSensorAdapter(mData, this);
        sensor = new Sensor();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_sensor_registration:
                finish();
                break;
            case R.id.select_image:
                setSensor();
                break;
            case R.id.add_registration:
                addUUID();
                break;
        }
    }

    //添加uuid数据和传感器的关联，并保存在本地数据库
    public void addUUID() {
        uuid = editUUID.getText().toString();
        String textId = textSenId.getText().toString();
        if (TextUtils.isEmpty(uuid)) {
            Toast.makeText(this, "uuid不能为空", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(textId)) {
            Toast.makeText(this, "传感器号不能为空", Toast.LENGTH_SHORT).show();
        }
        if (!TextUtils.isEmpty(uuid) && !TextUtils.isEmpty(textId)) {
            sendid = Integer.parseInt(textId);
            Sensor s = new Sensor();
            s.setDevice_type(sendid);
            s.setUuid(uuid);
            UUidDao dao = new UUidDao(this);
            dao.saveOrupdateUser(s);
            try {
                JSONObject object = new JSONObject();
                object.put("cmd", "add_uuid");
                JSONObject args = new JSONObject();
                args.put("device_id", sendid);
                args.put("uuid", uuid);
                object.put("args", args);
                SensorUtility.sendCmd(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(SensorRegistrationActivity.this, "添加成功", Toast.LENGTH_LONG).show();
        }
    }

    //设置传感器的弹窗，popWindow
    public void setSensor() {
        View window = LayoutInflater.from(this).inflate(R.layout.sensor_pop_list, null);
        final PopupWindow pw = new PopupWindow(window,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        sensorList = (ListView) window.findViewById(R.id.sensor_List);
        backPop = (ImageView) window.findViewById(R.id.backPop);
        // 点击pop外部，window消失，需要设置三个属性
        pw.setFocusable(true);
        pw.setOutsideTouchable(true);
        pw.setBackgroundDrawable(getResources().getDrawable(R.color.white));
        //当popwindow弹出时，设置后面的背景透明度
        final WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.8f;
        getWindow().setAttributes(lp);
        pw.setAnimationStyle(R.style.mypopWindow);
        pw.showAsDropDown(linearLayout);

        //点击*消失
        backPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
        //pop消失的时候后面背景颜色消失
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
                //请求获取数据
            }
        });
        sensorList.setAdapter(adapter);
        sensorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                s = (Sensor) adapter.getItem(position);
                setAlert(s, pw);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(SensorEvent sensorEvent) {
        sensor = MainApplication.JSONAnalysis(sensorEvent);
        mData.add(sensor);
        adapter.notifyDataSetChanged();
    }

    //设置alert
    public void setAlert(final Sensor s, final PopupWindow p) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setTitle("设置").setMessage("选择" + s.getDevice_id() + "传感器？");
        a.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                p.dismiss();
                textSenId.setText(s.getDevice_id() + "");
                editTyId.setText(s.getDevice_type() + "");
                editSenName.setText(MainApplication.getApplication().getDeviceType(s).getType());
            }
        });
        a.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                p.dismiss();
            }
        });
        AlertDialog alertDialog = a.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
