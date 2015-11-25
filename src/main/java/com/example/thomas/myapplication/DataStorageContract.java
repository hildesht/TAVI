package com.example.thomas.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class DataStorageContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DataStorageContract() {}

    /* Inner class that defines the table contents */
    public static abstract class CarEntry implements BaseColumns {
        public static final String TABLE_NAME = "cars";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_MAKE = "make";
        public static final String COLUMN_NAME_MODEL = "model";
        public static final String COLUMN_NAME_YEAR = "year";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CarEntry.TABLE_NAME + " (" +
                    CarEntry._ID + " INTEGER PRIMARY KEY," +
                    CarEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    CarEntry.COLUMN_NAME_MAKE + TEXT_TYPE + COMMA_SEP +
                    CarEntry.COLUMN_NAME_MODEL + TEXT_TYPE + COMMA_SEP +
                    CarEntry.COLUMN_NAME_YEAR + TEXT_TYPE + COMMA_SEP +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CarEntry.TABLE_NAME;


    public static class DbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }

}
