package com.example.administrator.myapplication;

import android.util.Log;

import com.example.administrator.myapplication.entity.SensorEvent;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import de.greenrobot.event.EventBus;

/**
 * Created by wangzhiqiang on 2015/12/9.
 */
public class SensorUtility {
    private static String remoteIp;
    private static int remotePort;
    private static Thread thread = null;
    private final static String TAG = "SensorUtility";
    private static boolean reconnect = false;
    private static boolean threadRuning = true;
    private static String line1;
    private static boolean flag = false;

    public static void
    setReconnect(boolean b) {
        reconnect = b;
    }

    public static void setRemoteAddr(String ip, int port) {
        remoteIp = ip;
        remotePort = port;
    }

    public static void sendCmd(JSONObject jso) {
        final String str = jso.toString();
        Log.e(TAG, "sendCmd:" + str);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(remoteIp, remotePort);
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    out.println(str);
                    out.flush();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void stopReadThread() {
        threadRuning = false;
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    public static void reStartReadThread() {
        threadRuning = true;
        Log.e("threadRunning", threadRuning + "");
        if (thread == null) {
            thread = new startThread();
            thread.start();
        }
    }

    public static class startThread extends Thread {
        public void run() {
            while (threadRuning) {
                final String requestCmd = "{\"cmd\":\"request_push\"}";
                try {
                    setReconnect(false);
                    if (remoteIp == null || remotePort == 0) {
                        //输入日志文件，便于debug
                        Log.e(TAG, remoteIp + "" + remotePort);
                    } else {
                        Socket socket = new Socket(remoteIp, remotePort);
                        socket.setSoTimeout(5000);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.println(requestCmd);
                        out.flush();
                        InputStream input = socket.getInputStream();
                        BufferedReader bff = new BufferedReader(new InputStreamReader(input));
                        String line = null;
                        while (!reconnect) {
                            line = bff.readLine();
                            if (null != line) {
                                EventBus.getDefault().post(new SensorEvent(line));
                            }

                        }
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void reConnect() {
        setReconnect(true);
    }
}
