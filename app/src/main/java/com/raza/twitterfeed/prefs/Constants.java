package com.raza.twitterfeed.prefs;

/**
 * Created by Jaffar on 12/10/2015.
 */
public class Constants {
    public static final String PREFS_NAME = "TWITTER_FEED_PREFS";

    public static final int SUCCESS = 200;
    public static final int SUCCESS_CREATED = 201;
    public static final int SUCCESS_ENDED = 202;
    public static final int UNAUTHORIZE = 400;
    public static final int SERVER_ERROR = 500;

    public static final String PAGE_INDEX = "page_index";

    // Note: Twitter consumer key and secret should be obfuscated in your source code before shipping.
    public static final String TWITTER_KEY = "rPnCA1NZwaNpJ1FHK29hOeI3V";
    public static final String TWITTER_SECRET = "JxNRONLUXkrhkZ831jGsq474VvwtcWRFy1oLr77L9fYpwEOCah";


    public static final String BASE_URL = "https://api.twitter.com/1.1/";
    public static final String USER_STATUSES = "statuses/user_timeline.json";



}
