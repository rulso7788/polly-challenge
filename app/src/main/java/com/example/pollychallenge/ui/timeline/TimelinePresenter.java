package com.example.pollychallenge.ui.timeline;

import android.util.Log;

import com.example.pollychallenge.service.TwitterClient;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by admin on 10/16/17.
 */

public class TimelinePresenter implements TimelineContract.Presenter, TwitterClient.TwitterListener {

    private TimelineContract.View timelineView;
    private TwitterClient twitterClient;

    public TimelinePresenter(TwitterSession session, TimelineContract.View timelineView) {
        this.timelineView = timelineView;
        twitterClient = new TwitterClient(session);
    }

    @Override
    public void init() {
        twitterClient.setTwitterListener(this);
    }

    @Override
    public void getTimeline() {
        twitterClient.getTimeline();
    }

    @Override
    public void tearDown() {
        twitterClient.unsetTwitterListener();
    }

    @Override
    public void onTimelineReady(List<Tweet> tweets) {
        timelineView.showTimeLine(tweets);
    }

    @Override
    public void onTimelineError(String error) {
        timelineView.showError(error);
    }
}
