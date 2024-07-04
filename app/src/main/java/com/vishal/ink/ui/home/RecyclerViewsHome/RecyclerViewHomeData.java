package com.vishal.ink.ui.home.RecyclerViewsHome;

public class RecyclerViewHomeData {
    private final int image;
    private final String text;

    public RecyclerViewHomeData(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
