package com.example.pollychallenge.ui.timeline;


import com.example.pollychallenge.service.TwitterClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

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
