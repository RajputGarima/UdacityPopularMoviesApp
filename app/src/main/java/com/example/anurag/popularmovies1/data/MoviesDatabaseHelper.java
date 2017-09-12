package com.example.anurag.popularmovies1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.anurag.popularmovies1.data.MoviesContract.*;

/**
 * Created by anurag on 10/12/2016.
 */

public class MoviesDatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;


    public MoviesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " + MoviesEntry.TABLE_NAME + " ( " +
                MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MoviesEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                MoviesEntry.COLUMN_ADULT + " BOOLEAN NOT NULL, " +
                MoviesEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
                MoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MoviesEntry.COLUMN_OVERVIEW + " TEXT NOT NULL ," +
                MoviesEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL ," +
                MoviesEntry.COLUMN_ID + " REAL NOT NULL," +
                MoviesEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                MoviesEntry.COLUMN_BACKDROP + " TEXT NOT NULL ," +
                MoviesEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL , " +
                MoviesEntry.COLUMN_POPULARITY + " REAL NOT NULL );";

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoviesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
