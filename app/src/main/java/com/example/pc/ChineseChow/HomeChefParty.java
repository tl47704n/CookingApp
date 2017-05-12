package com.example.pc.ChineseChow;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by pc on 2017/3/19.
 */

public class HomeChefParty extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
