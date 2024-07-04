package com.vishal.ink.ui.home.RecyclerViewsHome;


import com.vishal.ink.R;

import java.util.ArrayList;

public class CommonData {

    public static ArrayList<RecyclerViewHomeData> getPopularData() {
        ArrayList<RecyclerViewHomeData> popularDataList = new ArrayList<>();

        popularDataList.add(new RecyclerViewHomeData(R.drawable.love_image, "Love Quotes"));
        popularDataList.add(new RecyclerViewHomeData(R.drawable.angry_image, "Angry Quotes"));
        popularDataList.add(new RecyclerViewHomeData(R.drawable.motivational_image, "Motivational Quotes"));
        popularDataList.add(new RecyclerViewHomeData(R.drawable.sad_image, "Sad Quotes"));
        popularDataList.add(new RecyclerViewHomeData(R.drawable.alone_image, "Alone Quotes"));
        popularDataList.add(new RecyclerViewHomeData(R.drawable.life_image, "Life Quotes"));

        return popularDataList;
    }

    public static ArrayList<RecyclerViewHomeData> getCategoryData() {
        ArrayList<RecyclerViewHomeData> categoryDataList = new ArrayList<>();

        categoryDataList.add(new RecyclerViewHomeData(R.drawable.time_image, "Time Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.positive_image, "Positive Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.inspiration_image, "Inspiration Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.best_life_image, "Best Life Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.success_image, "Success Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.wisdom_image, "Wisdom Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.relationship_image, "Relationship Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.nature_image, "Nature Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.love_image, "Love Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.angry_image, "Angry Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.motivational_image, "Motivational Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.sad_image, "Sad Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.alone_image, "Alone Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.life_image, "Life Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.attitude_image, "Attitude Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.trust_image, "Trust Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.friendship_image, "Friendship Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.leadership_image, "Leadership Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.happyness_image, "Happiness Quotes"));
        categoryDataList.add(new RecyclerViewHomeData(R.drawable.smile_image, "Smiling Quotes"));

        return categoryDataList;
    }
}
