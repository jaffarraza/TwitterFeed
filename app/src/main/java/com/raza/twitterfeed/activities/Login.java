package com.raza.twitterfeed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.raza.twitterfeed.R;
import com.raza.twitterfeed.prefs.Constants;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

/**
 * Created by jaffarraza on 05/08/16.
 */
public class Login extends AppCompatActivity {

    @Bind(R.id.twitter_login_button)
    TwitterLoginButton twitterLoginButton;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);

        Fabric.with(this, new Twitter(authConfig));

        launchMainActivity();
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Snackbar.make(coordinatorLayout, "Congratulation! You have logged in successfully.", Snackbar.LENGTH_SHORT).show();
                launchMainActivity();
        }

            @Override
            public void failure(TwitterException exception) {
                Snackbar.make(coordinatorLayout, "Some error occurred while login with twitter.", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Handle Twitter login callback.
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void launchMainActivity() {

        // Retrieve twitter sesison of already user logged in.
        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        if (session == null) {
            Snackbar.make(coordinatorLayout, "Try login with Twitter.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        TwitterAuthToken authToken = session.getAuthToken();
        if (authToken == null) {
            Snackbar.make(coordinatorLayout, "Try login with Twitter.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        String token = authToken.token;
        String secret = authToken.secret;
        if (token == null || secret == null) {
            Snackbar.make(coordinatorLayout, "No active session found for this application.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        //Launch Main Activity if user is already logged in.
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
