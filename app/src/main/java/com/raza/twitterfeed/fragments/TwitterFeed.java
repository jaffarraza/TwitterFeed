package com.raza.twitterfeed.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.raza.twitterfeed.R;
import com.raza.twitterfeed.prefs.Constants;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jaffarraza on 05/08/16.
 */
public class TwitterFeed extends BaseFragment {

    @Bind(R.id.list_view)
    ListView listView;
    private int page;
    private String label;

    // newInstance constructor for creating fragment with arguments
    public static TwitterFeed newInstance(int page, String label) {
        TwitterFeed feedFragment = new TwitterFeed();
        Bundle args = new Bundle();
        args.putInt(Constants.PAGE_INDEX, page);
        args.putString("label", label);
        feedFragment.setArguments(args);
        return feedFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_timeline;
    }

    @Override
    public void initViews(View parent, Bundle savedInstanceState) {
        super.initViews(parent, savedInstanceState);
        ButterKnife.bind(this, parent);

        page = getArguments().getInt(Constants.PAGE_INDEX, 0);
        label = getArguments().getString("label", "");

        //Load tweets for Search Label
        SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query(label)
                .build();
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity()).setTimeline(searchTimeline)
                .build();
        listView.setAdapter(adapter);
    }


    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}