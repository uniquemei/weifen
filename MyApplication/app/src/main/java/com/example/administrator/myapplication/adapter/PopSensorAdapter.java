package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Sensor;
import com.example.administrator.myapplication.entity.Sensors;

/**
 * Created by Administrator on 2015/12/16.
 */
public class PopSensorAdapter extends BaseAdapter {
    Sensors mData;
    Context mContext;

    public PopSensorAdapter(Sensors mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.getCount();
    }

    @Override
    public Object getItem(int i) {
        return mData.getItem(i);
    }

    @Override
    public long getItemId(int position) {
        return mData.getItemId(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        viewHolder vHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.sensor_popwindow_list_item, null);
            vHolder = new viewHolder();
            vHolder.imageView = (ImageView) view.findViewById(R.id.pop_device_image);
            vHolder.device_type_num = (TextView) view.findViewById(R.id.pop_device_type_num);
            vHolder.device_typ = (TextView) view.findViewById(R.id.pop_device_type);
            vHolder.device_id = (TextView) view.findViewById(R.id.pop_senId);
            view.setTag(vHolder);
        } else {
            vHolder = (viewHolder) view.getTag();
        }
        //捆绑数据
        Sensor s = mData.getItem(position);
        if (s.getDevice_type() == 16) {
            vHolder.imageView.setImageResource(R.mipmap.temp);
            vHolder.device_typ.setText("温度(℃)");
        } else if (s.getDevice_type() == 17) {
            vHolder.imageView.setImageResource(R.mipmap.hum);
            vHolder.device_typ.setText("湿度(%)");
        } else if (s.getDevice_type() == 18) {
            vHolder.imageView.setImageResource(R.mipmap.light);
            vHolder.device_typ.setText("光照(LX)");
        } else if (s.getDevice_type() == 19) {
            vHolder.imageView.setImageResource(R.mipmap.keran);
            vHolder.device_typ.setText("可燃气体(x)");
        } else if (s.getDevice_type() == 20) {
            vHolder.imageView.setImageResource(R.mipmap.renti);
            vHolder.device_typ.setText("人体红外");
        } else if (s.getDevice_type() == 22) {
            vHolder.imageView.setImageResource(R.mipmap.pa);
            vHolder.device_typ.setText("气压(Pa)");
        } else if (s.getDevice_type() == 29) {
            vHolder.imageView.setImageResource(R.mipmap.smoke);
            vHolder.device_typ.setText("烟雾");
        } else if (s.getDevice_type() == 26) {
            vHolder.imageView.setImageResource(R.mipmap.co2);
            vHolder.device_typ.setText("二氧化碳(ppm)");
        } else if (s.getDevice_type() == 30) {
            vHolder.imageView.setImageResource(R.mipmap.chaoshen);
            vHolder.device_typ.setText("超声波(cm)");
        } else if (s.getDevice_type() == 21) {
            vHolder.imageView.setImageResource(R.mipmap.speed6);
            vHolder.device_typ.setText("加速度(m/s)");
        } else if (s.getDevice_type() == 28) {
            vHolder.imageView.setImageResource(R.mipmap.color);
            vHolder.device_typ.setText("颜色(RGB)");
        } else if (s.getDevice_type() == 32) {
            vHolder.imageView.setImageResource(R.mipmap.cichang);
            vHolder.device_typ.setText("磁场(N/C)");
        } else if (s.getDevice_type() == 24) {
            vHolder.imageView.setImageResource(R.mipmap.swich);
            vHolder.device_typ.setText("继电器");
        } else if (s.getDevice_type() == 33) {
            vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
            vHolder.device_typ.setText("步进电视");
        } else if (s.getDevice_type() == 25) {
            vHolder.imageView.setImageResource(R.mipmap.dianji);
            vHolder.device_typ.setText("直流电机");
        } else if (s.getDevice_type() == 31) {
            vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
            vHolder.device_typ.setText("数码管");
        } else if (s.getDevice_type() == 34) {
            vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
            vHolder.device_typ.setText("红外反射");
        } else if (s.getDevice_type() == 35) {
            vHolder.imageView.setImageResource(R.mipmap.anjian);
            vHolder.device_typ.setText("触摸按键");
        } else if (s.getDevice_type() == 36) {
            vHolder.imageView.setImageResource(R.mipmap.sound);
            vHolder.device_typ.setText("声音");
        } else if (s.getDevice_type() == 37) {
            vHolder.imageView.setImageResource(R.mipmap.renti);
            vHolder.device_typ.setText("雨滴");
        } else if (s.getDevice_type() == 38) {
            vHolder.imageView.setImageResource(R.mipmap.fire);
            vHolder.device_typ.setText("火焰");
        } else if (s.getDevice_type() == 39) {
            vHolder.imageView.setImageResource(R.mipmap.zhengdong);
            vHolder.device_typ.setText("震动");
        } else if (s.getDevice_type() == 40) {
            vHolder.imageView.setImageResource(R.mipmap.rfid);
            vHolder.device_typ.setText("RFID125K");
        } else if (s.getDevice_type() == 41) {
            vHolder.imageView.setImageResource(R.mipmap.rfid);
            vHolder.device_typ.setText("RFID13.56M");
        } else if (s.getDevice_type() == 23) {
            vHolder.imageView.setImageResource(R.mipmap.hongwai);
            vHolder.device_typ.setText("红外遥控");
        } else if (s.getDevice_type() == 65) {
            vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
            vHolder.device_typ.setText("温湿度合并");
        } else if (s.getDevice_type() == 67) {
            vHolder.imageView.setImageResource(R.mipmap.ph);
            vHolder.device_typ.setText("PH酸碱度");
        } else {
            vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
            vHolder.device_typ.setText("网关");
        }
        vHolder.device_type_num.setText(s.getDevice_type()+"");
        vHolder.device_id.setText(s.getDevice_id() + "");
        return view;
    }

    public class viewHolder {
        ImageView imageView;
        TextView device_type_num;//传感器类型编号
        TextView device_typ;//传感器名
        TextView device_id;
    }
}
