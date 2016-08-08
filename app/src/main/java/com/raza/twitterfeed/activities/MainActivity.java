package com.raza.twitterfeed.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.raza.twitterfeed.R;
import com.raza.twitterfeed.bLogic.Utils;
import com.raza.twitterfeed.fragments.TwitterFeed;
import com.raza.twitterfeed.fragments.UserTimeLine;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {
    @Bind(R.id.pager_header)
    PagerTabStrip pagerHeader;
    @Bind(R.id.vpPager)
    ViewPager vpPager;

    private PagerAdapter vPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        ButterKnife.bind(this);

        //Initialize Pager Adapter for View Pager.
        vPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(vPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_new_quote) {
            if (item.getTitle().equals("Arabic")) {
                Utils.getInstance().setLocale(this, "ar_");
                item.setTitle(getString(R.string.english));
            } else {
                Utils.getInstance().setLocale(this, "en");
                item.setTitle(getString(R.string.arabic));
            }
        }
        return true;

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.locale.getLanguage().equals("ar")) {

        }
    }

    public static class PagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show UserTimeLine Fragment
                    return UserTimeLine.newInstance(0);
                case 1: // Fragment # 0 - This will show TwitterFeed
                    return TwitterFeed.newInstance(1, "@OLXEgypt");
                case 2: // Fragment # 1 - This will show TwitterFeed with different title
                    return TwitterFeed.newInstance(2, "@AndroidDev");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position) {
                case 0:
                    title = "User Timeline";
                    break;
                case 1:
                    title = "@OLXEgypt";
                    break;
                case 2:
                    title = "@AndroidDev";
                    break;
            }
            return title;
        }

    }

}
