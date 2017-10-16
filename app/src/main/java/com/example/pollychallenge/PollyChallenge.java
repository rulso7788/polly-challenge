package com.example.pollychallenge;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

public class PollyChallenge extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
    }
}
