package com.example.thomas.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public final class DataStorageContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DataStorageContract() {}

    /* Inner class that defines the table contents */
    public static abstract class CarEntry implements BaseColumns {
        public static final String TABLE_NAME = "cars";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_MAKE = "make";
        public static final String COLUMN_MODEL = "model";
        public static final String COLUMN_YEAR = "year";
        public static final String COLUMN_FAVORITE = "isFavorite";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CarEntry.TABLE_NAME + " (" +
                    CarEntry.COLUMN_NAME + " TEXT PRIMARY KEY," +
                    CarEntry.COLUMN_MAKE + TEXT_TYPE + COMMA_SEP +
                    CarEntry.COLUMN_MODEL + TEXT_TYPE + COMMA_SEP +
                    CarEntry.COLUMN_FAVORITE + INTEGER_TYPE + " DEFAULT 0" + COMMA_SEP +
                    CarEntry.COLUMN_YEAR + TEXT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CarEntry.TABLE_NAME;


    public static class DbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "FeedReader.db";

        private static DbHelper sInstance;

        public static synchronized DbHelper getInstance(Context context) {
            // Use the application context, which will ensure that you
            // don't accidentally leak an Activity's context.
            // See this article for more information: http://bit.ly/6LRzfx
            if (sInstance == null) {
                sInstance = new DbHelper(context.getApplicationContext());
            }
            return sInstance;
        }

        private DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            Log.d("Initial SQL", SQL_CREATE_ENTRIES);
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


        /**
         * Queries for passed car name returning one result. Cursor is pre moved to the first result.
         * @param carName The car name to query for.
         * @return Cursor The query result.
         */
        public Cursor queryForCar(String carName) {
            SQLiteDatabase db = this.getReadableDatabase();
            String selection = CarEntry.COLUMN_NAME + "='" + carName + "'";
            Cursor c = db.query(
                    CarEntry.TABLE_NAME,
                    null,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null,                                 // The sort order
                    "1"                        //limit
            );
            c.moveToFirst();
            return c;
        }


        /**
         * Queries for all cars in the cars table.
         * TODO Remove if only used for testing.
         * @return Query results
         */
        public Cursor queryForAllCars() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.query(
                    CarEntry.TABLE_NAME,
                    null,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );
            return c;
        }

        public boolean createCar(String carName, String carMake, String carModel, String carYear) {

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(DataStorageContract.CarEntry.COLUMN_NAME, carName);
            values.put(DataStorageContract.CarEntry.COLUMN_MAKE, carMake);
            values.put(DataStorageContract.CarEntry.COLUMN_MODEL, carModel);
            values.put(DataStorageContract.CarEntry.COLUMN_YEAR, carYear);

                long newRowId;
                SQLiteDatabase db = this.getReadableDatabase();
                newRowId = db.insertOrThrow(DataStorageContract.CarEntry.TABLE_NAME, null, values);

            return true;
        }

        /**
         * Checks for cars
         * @return if cars exist
         */
        public boolean doCarsExist() {
            String sql = "SELECT COUNT(*) FROM " + CarEntry.TABLE_NAME;
            Cursor c = this.getDb().rawQuery(sql, new String[] {});
            int count = c.getCount();
            c.close();

            return count >= 0;
        }

        /**
         * returns the name of the favorite car or null
         */
        public String getFavoriteCar() {
            String sql = "SELECT " + CarEntry.COLUMN_NAME + " FROM " + CarEntry.TABLE_NAME + " WHERE isFavorite = 1 LIMIT 1";
            Cursor c = this.getDb().rawQuery(sql, new String[] {});
            if (c.getCount() > 0) {
                return c.getString(0);
            } else {
                return null;
            }
        }


        /**
         * Helper function to reduce text
         */
        private SQLiteDatabase getDb() {
            return this.getReadableDatabase();
        }

        // -------------------- Testing Functions  --------------------
        public void debugAllCars() {
            Cursor allCars = this.queryForAllCars();

            while(allCars.moveToNext()) {
                String name = allCars.getString(0);
                Log.d("Car name", name);
            }

            allCars.close();
        }

    }

}
