package com.example.servicedemo.service;

import android.app.Service;
import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyBinder extends Binder {
    private static final String TAG = "MyTest";

    IMyServer iMyServer = new IMyServer() {
        @Override
        public void say(String word) {
            Log.d(TAG, "MyBinder say:" + word);
        }
    };

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

        String word = data.readString();
        iMyServer.say(word);

        String replyWord = "I'm fine, and you?";
        Log.d(TAG, "MyBinder say:" + replyWord);
        reply.writeString(replyWord);
        return true;
    }
}
