package com.serenitydojo.playwright.data;

public final class TodoData {

    private TodoData() {
        // Private constructor to prevent instantiation
    }

    public static final String FEED_THE_CAT = "Feed the cat";
    public static final String WALK_THE_DOG = "Walk the dog";
    public static final String BUY_SOME_MILK = "Buy some milk";
    public static final String FEED_THE_CAT_IN_CHINESE = "喂猫";
    public static final String FEED_THE_CAT_IN_ARABIC = "إطعام القط";
    public static final String EMPTY_TODO = "";


    public static final String[] INITIAL_ITEMS = {FEED_THE_CAT, WALK_THE_DOG, BUY_SOME_MILK};
    public static final String[] DUPLICATE_ITEMS = {FEED_THE_CAT, WALK_THE_DOG, FEED_THE_CAT};
    public static final String[] NON_ENGLISH_ITEMS = {
            FEED_THE_CAT, FEED_THE_CAT_IN_CHINESE, FEED_THE_CAT_IN_ARABIC
    };
}
