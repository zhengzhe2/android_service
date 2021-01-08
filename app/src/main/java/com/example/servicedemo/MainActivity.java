package com.example.servicedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import com.example.servicedemo.service.CountManager;
import com.example.servicedemo.service.MyBinder;
import com.example.servicedemo.service.MyService;
import com.example.servicedemo.service.Student;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTest";
    private MyService myService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MainActivity onCreate");
        setContentView(R.layout.activity_main);
        bindService();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy");
        unBindService();
    }

    private void startService() {
        Log.d(TAG, "MainActivity startService");
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void stopService() {
        Log.d(TAG, "MainActivity stopService");
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void bindService() {
        Log.d(TAG, "MainActivity bindService");
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void unBindService() {
        Log.d(TAG, "MainActivity unBindService");
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "MainActivity onServiceConnected");

//            Parcel data = Parcel.obtain();
//            Parcel reply = Parcel.obtain();
//
//            String word = "Hello Server, how are you ?";
//            Log.d(TAG, "MainActivity onServiceConnected word:" + word);
//            data.writeString(word);
//            try {
//                service.transact(2, data, reply, 0);
//                String responseWord = reply.readString();
//                Log.d(TAG, "MainActivity onServiceConnected responseWord:" + responseWord);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            IMyServer iMyServer = IMyServer.Stub.asInterface(service);
            try {
//                iMyServer.say("how are you ?");
//                int result = iMyServer.tell("how are you ?", 18);
//                Log.d(TAG, "receive return content:" + result + " in client");

                iMyServer.getStudentInfo(2, new Student("xiaoming", 18));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "MainActivity onServiceDisconnected");
        }
    };


}