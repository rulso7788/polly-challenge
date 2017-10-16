package com.example.pollychallenge.ui.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pollychallenge.R;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by admin on 10/16/17.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private List<Tweet> tweets;

    public TimelineAdapter(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.name.setText(tweet.user.name);
        holder.username.setText("@"+tweet.user.screenName);
        holder.time.setText(tweet.createdAt);
        holder.text.setText(tweet.text);
        holder.retweet.setText(String.valueOf(tweet.retweetCount));
        holder.likes.setText(String.valueOf(tweet.favoriteCount));
    }

    @Override
    public int getItemCount() {
        return tweets == null ? 0 : tweets.size();
    }

    public void updateDataset(List<Tweet> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView username;
        TextView time;
        TextView text;
        TextView retweet;
        TextView likes;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            username = itemView.findViewById(R.id.tv_username);
            time = itemView.findViewById(R.id.tv_time);
            text = itemView.findViewById(R.id.tv_tweet);
            retweet = itemView.findViewById(R.id.tv_retweet);
            likes = itemView.findViewById(R.id.tv_likes);
        }
    }

}
