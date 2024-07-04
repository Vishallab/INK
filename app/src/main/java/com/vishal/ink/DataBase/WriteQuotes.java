package com.vishal.ink.DataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "writeQuotes")
public class WriteQuotes {

    @PrimaryKey(autoGenerate = true)
    private int idWrite;
    private String quotesWrite;
    private String authorWrite;

    // Default constructor is required by Room
    public WriteQuotes() {}

    public WriteQuotes(int idWrite, String quotesWrite, String authorWrite) {
        this.idWrite = idWrite;
        this.quotesWrite = quotesWrite;
        this.authorWrite = authorWrite;
    }

    public int getIdWrite() {
        return idWrite;
    }

    public void setIdWrite(int idWrite) {
        this.idWrite = idWrite;
    }

    public String getQuotesWrite() {
        return quotesWrite;
    }

    public void setQuotesWrite(String quotesWrite) {
        this.quotesWrite = quotesWrite;
    }

    public String getAuthorWrite() {
        return authorWrite;
    }

    public void setAuthorWrite(String authorWrite) {
        this.authorWrite = authorWrite;
    }
}
