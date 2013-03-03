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

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAMPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
        // Create tables again
        onCreate(db);
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addCamp(Camp camp) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_DATE, camp.getDate()); 
        values.put(KEY_TYPE, camp.getType()); 
        values.put(KEY_BADGE, camp.getBadge()); 
        values.put(KEY_NO_PEOPLE, camp.getNoPeople()); 
        values.put(KEY_LASTMODIFIED_DATE, camp.getLastModifiedDate()); 
        values.put(KEY_IMAGE_URL, camp.getImageURL());
        values.put(KEY_LASTMODIFIED_PERSON, camp.getLastModifiedPerson()); 
 
        // Inserting Row
        db.insert(TABLE_CAMPS, null, values);
        db.close(); // Closing database connection
    }
    
    // Getting single CAMP
    Camp getCamp(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_CAMPS, new String[] { KEY_ID,
                KEY_FIRST_DATE, KEY_TYPE,KEY_BADGE, KEY_NO_PEOPLE, 
                KEY_LASTMODIFIED_DATE, KEY_IMAGE_URL, KEY_LASTMODIFIED_PERSON}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Camp camp = new Camp(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                Integer.parseInt(cursor.getString(6)));
        // return camp
        return camp;
    }

    // Getting All Camps
    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> campList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CAMPS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Camp camp = new Camp();
                camp.setID(Integer.parseInt(cursor.getString(0)));
                camp.setDate(cursor.getString(1));
                camp.setType(cursor.getString(2));
                camp.setBadge(cursor.getString(3));
                camp.setNoPeople(cursor.getString(4));
                camp.setLastModifiedDate(cursor.getString(5));
                camp.setLastModifiedPerson(Integer.parseInt(cursor.getString(6)));
                // Adding camp to list
                campList.add(camp);
            } while (cursor.moveToNext());
        }
        // return contact list
        return campList;
    }
    // Updating single camp
    public int updateCamp(Camp camp) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_DATE, camp.getDate()); 
        values.put(KEY_TYPE, camp.getType()); 
        values.put(KEY_BADGE, camp.getBadge()); 
        values.put(KEY_NO_PEOPLE, camp.getNoPeople()); 
        values.put(KEY_LASTMODIFIED_DATE, camp.getLastModifiedDate()); 
        values.put(KEY_IMAGE_URL, camp.getImageURL());
        values.put(KEY_LASTMODIFIED_PERSON, camp.getLastModifiedPerson()); 
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    public void deleteCamp(Camp camp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(camp.getID()) });
        db.close();
    }

    // Getting camps Count
    public int getCampsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CAMPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}