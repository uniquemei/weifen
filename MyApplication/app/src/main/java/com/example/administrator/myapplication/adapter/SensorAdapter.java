package com.example.administrator.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.entity.Sensor;
import com.example.administrator.myapplication.entity.Sensors;

/**
 * Created by Administrator on 2015/12/16.
 */
public class SensorAdapter extends BaseAdapter {
    Sensors mData;
    Context mContext;
    boolean flag = false;
    public onCheckBoxClickListenner listenner4;

    public SensorAdapter(Sensors mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    public void setCkeckBoxListenner(onCheckBoxClickListenner listenner4) {
        this.listenner4 = listenner4;
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
    public int getViewTypeCount() {
        return 2;
    }

    public static final int SINGLE_IMG = 0;
    public static final int fan_IMAG = 1;

    @Override
    public int getItemViewType(int position) {
        Sensor s = mData.getItem(position);
        if (s != null) {
            if (s.getDevice_type() == 24) {
                return fan_IMAG;
            } else {
                return SINGLE_IMG;
            }
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        viewHolder vHolder = null;
        fanHolder fHolder = null;
        int type = getItemViewType(position);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            switch (type) {
                case SINGLE_IMG:
                    view = inflater.inflate(R.layout.sensor_list_item2, null);
                    vHolder = new viewHolder();
                    vHolder.imageView = (ImageView) view.findViewById(R.id.device_image);
                    vHolder.type = (TextView) view.findViewById(R.id.type);
                    vHolder.value = (TextView) view.findViewById(R.id.value);
                    vHolder.sendId = (TextView) view.findViewById(R.id.senId);
                    view.setTag(vHolder);
                    break;
                case fan_IMAG:
                    view = inflater.inflate(R.layout.fan_list_item2, null);
                    fHolder = new fanHolder();
                    fHolder.imageView = (ImageView) view.findViewById(R.id.device2_image);
                    fHolder.sendId = (TextView) view.findViewById(R.id.senId2);
                    fHolder.type = (TextView) view.findViewById(R.id.type2);
                    fHolder.checkBox = (CheckBox) view.findViewById(R.id.fan_checkbox);
                    view.setTag(fHolder);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case SINGLE_IMG:
                    vHolder = (viewHolder) view.getTag();
                    break;
                case fan_IMAG:
                    fHolder = (fanHolder) view.getTag();
                    break;
                default:
                    break;
            }

        }

        //捆绑数据
        final Sensor s = mData.getItem(position);
        switch (type) {
            case SINGLE_IMG:
                if (s.getDevice_type() == 16) {
                    vHolder.imageView.setImageResource(R.mipmap.temp);
                    vHolder.type.setText("温度(℃)");
                } else if (s.getDevice_type() == 17) {
                    vHolder.imageView.setImageResource(R.mipmap.hum);
                    vHolder.type.setText("湿度(%)");
                } else if (s.getDevice_type() == 18) {
                    vHolder.imageView.setImageResource(R.mipmap.light);
                    vHolder.type.setText("光照(LX)");
                } else if (s.getDevice_type() == 19) {
                    vHolder.imageView.setImageResource(R.mipmap.keran);
                    vHolder.type.setText("可燃气体(x)");
                } else if (s.getDevice_type() == 20) {
                    vHolder.imageView.setImageResource(R.mipmap.renti);
                    vHolder.type.setText("人体红外");
                } else if (s.getDevice_type() == 22) {
                    vHolder.imageView.setImageResource(R.mipmap.pa);
                    vHolder.type.setText("气压(Pa)");
                } else if (s.getDevice_type() == 29) {
                    vHolder.imageView.setImageResource(R.mipmap.smoke);
                    vHolder.type.setText("烟雾");
                } else if (s.getDevice_type() == 26) {
                    vHolder.imageView.setImageResource(R.mipmap.co2);
                    vHolder.type.setText("二氧化碳(ppm)");
                } else if (s.getDevice_type() == 30) {
                    vHolder.imageView.setImageResource(R.mipmap.chaoshen);
                    vHolder.type.setText("超声波(cm)");
                } else if (s.getDevice_type() == 21) {
                    vHolder.imageView.setImageResource(R.mipmap.speed6);
                    vHolder.type.setText("加速度(m/s)");
                } else if (s.getDevice_type() == 28) {
                    vHolder.imageView.setImageResource(R.mipmap.color);
                    vHolder.type.setText("颜色(RGB)");
                } else if (s.getDevice_type() == 32) {
                    vHolder.imageView.setImageResource(R.mipmap.cichang);
                    vHolder.type.setText("磁场(N/C)");
                } else if (s.getDevice_type() == 33) {
                    vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
                    vHolder.type.setText("步进电视");
                } else if (s.getDevice_type() == 25) {
                    vHolder.imageView.setImageResource(R.mipmap.dianji);
                    vHolder.type.setText("直流电机");
                } else if (s.getDevice_type() == 31) {
                    vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
                    vHolder.type.setText("数码管");
                } else if (s.getDevice_type() == 34) {
                    vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
                    vHolder.type.setText("红外反射");
                } else if (s.getDevice_type() == 35) {
                    vHolder.imageView.setImageResource(R.mipmap.anjian);
                    vHolder.type.setText("触摸按键");
                } else if (s.getDevice_type() == 36) {
                    vHolder.imageView.setImageResource(R.mipmap.sound);
                    vHolder.type.setText("声音");
                } else if (s.getDevice_type() == 37) {
                    vHolder.imageView.setImageResource(R.mipmap.renti);
                    vHolder.type.setText("雨滴");
                } else if (s.getDevice_type() == 38) {
                    vHolder.imageView.setImageResource(R.mipmap.fire);
                    vHolder.type.setText("火焰");
                } else if (s.getDevice_type() == 39) {
                    vHolder.imageView.setImageResource(R.mipmap.zhengdong);
                    vHolder.type.setText("震动");
                } else if (s.getDevice_type() == 40) {
                    vHolder.imageView.setImageResource(R.mipmap.rfid);
                    vHolder.type.setText("RFID125K");
                } else if (s.getDevice_type() == 41) {
                    vHolder.imageView.setImageResource(R.mipmap.rfid);
                    vHolder.type.setText("RFID13.56M");
                } else if (s.getDevice_type() == 23) {
                    vHolder.imageView.setImageResource(R.mipmap.hongwai);
                    vHolder.type.setText("红外遥控");
                } else if (s.getDevice_type() == 65) {
                    vHolder.imageView.setImageResource(R.mipmap.ic_drawer);
                    vHolder.type.setText("温湿度合并");
                } else if (s.getDevice_type() == 67) {
                    vHolder.imageView.setImageResource(R.mipmap.ph);
                    vHolder.type.setText("PH酸碱度");
                }
                vHolder.value.setText(s.getDevice_value());
                vHolder.sendId.setText(s.getDevice_id() + "");
                break;
            case fan_IMAG:
                fHolder.imageView.setImageResource(R.mipmap.swich);
                fHolder.type.setText("继电器");
                fHolder.sendId.setText(s.getDevice_id() + "");
                fHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox c = (CheckBox) v;
                        if (listenner4 != null) {
                            if (c.isChecked()) {
                                listenner4.checkBoxClick(s);
                            } else {
                                listenner4.noCheckBoxClick(s);
                            }

                        }
                    }
                });
                break;

        }
        return view;
    }

    public class viewHolder {
        ImageView imageView;
        TextView type;
        TextView value;
        TextView sendId;
    }

    //风扇的
    public class fanHolder {
        ImageView imageView;
        TextView type;
        TextView sendId;
        TextView textValue;
        CheckBox checkBox;
    }

    public interface onCheckBoxClickListenner {
        public void checkBoxClick(Sensor s);

        public void noCheckBoxClick(Sensor s);
    }
}
