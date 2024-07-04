package com.vishal.ink.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuotesFavDAO {

    // For favorite quotes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuotes(QuotesFav quotesFav);

    @Delete
    void deleteQuotes(QuotesFav quotesFav);

    @Query("SELECT * FROM quotesFav")
    LiveData<List<QuotesFav>> getAllQuotes();

    // For written quotes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWriteQuotes(WriteQuotes quotesWrite);

    @Delete
    void deleteWriteQuotes(WriteQuotes quotesWrite);

    @Query("SELECT * FROM writeQuotes")
    LiveData<List<WriteQuotes>> getAllWriteQuotes();
}
