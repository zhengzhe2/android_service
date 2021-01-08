package com.example.servicedemo.service;

import android.util.Log;

public class CountManager {
    private static volatile CountManager instance;

    private CountManager() {
        startCount();
    }

    private int count = 0;

    public static CountManager getInstance() {
        if (instance == null) {
            synchronized (CountManager.class) {
                if (instance == null) {
                    instance = new CountManager();
                }
            }
        }
        return instance;
    }

    private void startCount() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public int getCount() {
        return count;
    }
}
