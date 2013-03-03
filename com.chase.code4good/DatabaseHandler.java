package com.chase.code4good;

import java.util.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "code4Good";

    //Camps Table Name
    private static final String TABLE_CAMPS = "camps";
    //Persons Table Name
    private static final String TABLE_PERSONS = "persons";
    //Workers Table Name
    private static final String TABLE_WORKERS = "workers";

    //Camps Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_DATE = "firstDate";
    private static final String KEY_TYPE = "type";
    private static final String KEY_BADGE = "badge";
    private static final String KEY_NO_PEOPLE = "NoPeople";
    private static final String KEY_LASTMODIFIED_DATE = "lastModifiedDate";
    private static final String KEY_LASTMODIFIED_PERSON = "lastModifiedPerson";
    private static final String KEY_IMAGE_URL = "image_url";

    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CAMPS_TABLE = "CREATE TABLE " + TABLE_CAMPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_DATE + " TEXT,"
                + KEY_TYPE  + " TEXT," + KEY_BADGE + " TEXT," + KEY_NO_PEOPLE + 
                " INTEGER," + KEY_LASTMODIFIED_DATE + " TEXT," + KEY_IMAGE_URL + " TEXT," + KEY_LASTMODIFIED_PERSON 
                + " INTEGER," + " FOREIGN KEY (" + KEY_LASTMODIFIED_PERSON + ")	REFERENCES " + 
                TABLE_WORKERS + " (" + KEY_WORKER_ID + "))";
        db.execSQL(CREATE_CAMPS_TABLE);
    }
}