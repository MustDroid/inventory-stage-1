package com.example.android.inventorystage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Emoke Hajdu on 7/26/2018.
 */

public class BookDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = BookDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "book.db";

    private static final int DATABASE_VERSION = 1;

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_BOOK_TABLE =  "CREATE TABLE " + Book.Contract.TABLE_NAME + " ("
                + Book.Contract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Book.Contract.COLUMN_NAME + " TEXT NOT NULL, "
                + Book.Contract.COLUMN_PRICE + " REAL, "
                + Book.Contract.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + Book.Contract.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + Book.Contract.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);";


        db.execSQL(SQL_CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
