package com.example.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.servicedemo.IMyServer;

public class MyService extends Service {
    private static final String TAG = "MyTest";

    private MyBinder myBinder = new MyBinder();

    public MyService() {
        super();
        Log.d(TAG, "MyService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService onBind");
        return stub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "MyService onDestroy");
        super.onDestroy();
    }


    private IMyServer.Stub stub = new IMyServer.Stub() {
        @Override
        public void say(String word) throws RemoteException {
            Log.d(TAG, "receive say content:" + word + " in server");
        }

        @Override
        public int tell(String word, int age) throws RemoteException {
            Log.d(TAG, "receive tell content:" + word + " age:" + age + " in server");
            return age + 1;
        }

        @Override
        public void getStudentInfo(int age, Student student) throws RemoteException {
            Log.d(TAG, student.getName() + " in server");
        }
    };
}
