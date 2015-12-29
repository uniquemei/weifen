package com.example.administrator.myapplication.entity;

/**
 * Created by Administrator on 2015/12/16.
 */
public class Sensor {
    private int device_id;
    private int device_type;
    private String transfer_type;
    private String device_value;
    private String timestamp;
    private String type;
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(String transfer_type) {
        this.transfer_type = transfer_type;
    }

    public String getDevice_value() {
        return device_value;
    }

    public void setDevice_value(String device_value) {
        this.device_value = device_value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestampe) {
        this.timestamp = timestampe;
    }

    public Sensor(int device_id, int device_type, String transfer_type, String device_value, String timestamp) {
        this.device_id = device_id;
        this.device_type = device_type;
        this.transfer_type = transfer_type;
        this.device_value = device_value;
        this.timestamp = timestamp;
    }

    public Sensor() {
        super();
    }
}
