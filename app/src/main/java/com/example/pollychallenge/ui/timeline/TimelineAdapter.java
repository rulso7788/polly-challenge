package com.example.pollychallenge.ui.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pollychallenge.R;
import com.example.pollychallenge.util.CircleTransform;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

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
        Picasso.with(holder.avatar.getContext())
                .load(tweet.user.profileImageUrl)
                .transform(new CircleTransform())
                .into(holder.avatar);
        holder.name.setText(tweet.user.name);
        holder.username.setText("@"+tweet.user.screenName);
        holder.text.setText(tweet.text);
        holder.retweet.setText(String.valueOf(tweet.retweetCount));
        holder.retweet.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_retweet, 0, 0, 0);
        holder.likes.setText(String.valueOf(tweet.favoriteCount));
        holder.likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_like, 0, 0, 0);
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
        ImageView avatar;
        TextView name;
        TextView username;
        TextView text;
        TextView retweet;
        TextView likes;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.iv_avatar);
            name = itemView.findViewById(R.id.tv_name);
            username = itemView.findViewById(R.id.tv_username);
            text = itemView.findViewById(R.id.tv_tweet);
            retweet = itemView.findViewById(R.id.tv_retweet);
            likes = itemView.findViewById(R.id.tv_likes);
        }
    }

}
