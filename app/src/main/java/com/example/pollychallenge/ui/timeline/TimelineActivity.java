package com.example.pollychallenge.ui.timeline;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.pollychallenge.R;
import com.example.pollychallenge.ui.login.MainActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity implements TimelineContract.View {

    private static final String TAG = TimelineActivity.class.getSimpleName() + "_TAG";

    private TimelinePresenter presenter;
    private RecyclerView timelineRV;
    private TimelineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (session != null) {
            initViews();
            presenter = new TimelinePresenter(session, this);
            presenter.init();
        } else {
            showError("Invalid session");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getTimeline();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.tearDown();
    }

    @Override
    public void showTimeLine(List<Tweet> tweets) {
        adapter.updateDataset(tweets);
        for(Tweet tweet: tweets) {
            Log.d(TAG, "showTimeLine: " + tweet.text + " user: " + tweet.retweetCount);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        timelineRV = findViewById(R.id.rv_timeline);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new TimelineAdapter(new ArrayList<Tweet>(0));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(timelineRV.getContext(),
                layoutManager.getOrientation());
        timelineRV.setLayoutManager(layoutManager);
        timelineRV.addItemDecoration(dividerItemDecoration);
        timelineRV.setAdapter(adapter);
    }
}
