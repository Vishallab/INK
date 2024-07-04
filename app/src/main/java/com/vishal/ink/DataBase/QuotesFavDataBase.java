package com.vishal.ink.DataBase;


import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {QuotesFav.class, WriteQuotes.class}, version = 1)
public abstract class QuotesFavDataBase extends RoomDatabase {
    public abstract QuotesFavDAO quotesFavDAO();
}
