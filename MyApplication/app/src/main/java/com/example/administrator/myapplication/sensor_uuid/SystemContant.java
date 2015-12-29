package com.example.administrator.myapplication.sensor_uuid;

/**
 * Created by Administrator on 2015/12/21.
 */
public class SystemContant {
    //定义表的相关常量
    //uuid_sensor表
    public static final class UUIDTable {
        public static final String TABLENAME = "uuid_sensor";
        public static final String UUID = "uuid";
        public static final String DEVICEID = "deviceid";

        public static String getUuidTable() {
            StringBuilder sb = new StringBuilder();
            sb.append("create table if not exists ");
            sb.append(TABLENAME);
            sb.append("(");
            sb.append(UUID);
            sb.append(" varchar(30) PRIMARY KEY , ");
            sb.append(DEVICEID);
            sb.append(" int(11) ");
            sb.append(")");
            return sb.toString();
        }
    }
}
