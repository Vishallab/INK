package com.vishal.ink.Activities.RecyclerViewAllQuotes;


public class RecyclerViewAllQuotesData {

    private final String count;
    private final String quotes;
    private final String quotesAuthor;

    public RecyclerViewAllQuotesData(String count, String quotes, String quotesAuthor) {
        this.count = count;
        this.quotes = quotes;
        this.quotesAuthor = quotesAuthor;
    }

    public String getCount() {
        return count;
    }

    public String getQuotes() {
        return quotes;
    }

    public String getQuotesAuthor() {
        return quotesAuthor;
    }
}
