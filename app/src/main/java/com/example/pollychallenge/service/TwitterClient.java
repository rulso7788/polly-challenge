package com.example.pollychallenge.service;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

public class TwitterClient {

    TwitterSession session;
    TwitterListener listener;

    public TwitterClient(TwitterSession session) {
        this.session = session;
    }

    public void setTwitterListener(TwitterListener listener) {
        this.listener = listener;
    }

    public void unsetTwitterListener() {
        this.listener = null;
    }

    public void getTimeline() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Tweet> tweets = result.data;
                listener.onTimelineReady(tweets);
            }

            @Override
            public void failure(TwitterException exception) {
                exception.printStackTrace();
                listener.onTimelineError("Failed to retrieve tweets");
            }
        };
        TwitterApiClient client = new TwitterApiClient(session);
        client.getStatusesService().homeTimeline(null, null, null, null, null, null, null).enqueue(callback);
    }

    public interface TwitterListener {
        void onTimelineReady(List<Tweet> tweets);
        void onTimelineError(String error);
    }
}
