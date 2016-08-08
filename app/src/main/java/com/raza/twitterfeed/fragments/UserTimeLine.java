package com.raza.twitterfeed.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.raza.twitterfeed.R;
import com.raza.twitterfeed.prefs.Constants;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jaffarraza on 05/08/16.
 */
public class UserTimeLine extends BaseFragment {

    @Bind(R.id.list_view)
    ListView listView;

    // newInstance constructor for creating fragment with arguments
    public static UserTimeLine newInstance(int page) {
        UserTimeLine timeLineFragment = new UserTimeLine();
        Bundle args = new Bundle();
        args.putInt(Constants.PAGE_INDEX, page);
        timeLineFragment.setArguments(args);
        return timeLineFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_timeline;
    }

    @Override
    public void initViews(View parent, Bundle savedInstanceState) {
        super.initViews(parent, savedInstanceState);
        ButterKnife.bind(this, parent);

        //Load tweets for User TimeLine
        loadTweets();
    }

    private void loadTweets() {

        UserTimeline userTimeline = new UserTimeline.Builder().build();
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity()).setTimeline(userTimeline)
                .build();
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}