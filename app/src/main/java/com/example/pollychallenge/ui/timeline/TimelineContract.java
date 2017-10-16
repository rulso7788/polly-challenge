package com.example.pollychallenge.ui.timeline;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public interface TimelineContract {

    interface View {
        void showTimeLine(List<Tweet> tweets);
        void showError(String error);
    }

    interface Presenter {
        void init();
        void getTimeline();
        void tearDown();
    }

}
