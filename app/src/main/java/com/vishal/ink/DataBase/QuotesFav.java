package com.vishal.ink.DataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotesFav")
public class QuotesFav {

    @PrimaryKey(autoGenerate = true)
    private int id; // Remove `final` if you use auto-generate
    private String quotes;
    private String author;

    // Default constructor is required by Room
    public QuotesFav() {}

    public QuotesFav(int id, String quotes, String author) {
        this.id = id;
        this.quotes = quotes;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
