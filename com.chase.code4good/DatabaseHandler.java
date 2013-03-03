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
    private static final String KEY_LONGTITUDE = "longtitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LASTMODIFIED_DATE = "lastModifiedDate";
    private static final String KEY_LASTMODIFIED_PERSON = "lastModifiedPerson";
    private static final String KEY_IMAGE_URL = "image_url";

    //Persons Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_AUDIO_URL = "audio_url";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_CAMP = "camp";

    //Workers Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CAMPS_TABLE = "CREATE TABLE " + TABLE_CAMPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_DATE + " TEXT,"
                + KEY_TYPE  + " TEXT," + KEY_BADGE + " TEXT," + KEY_NO_PEOPLE + 
                " INTEGER," + KEY_LASTMODIFIED_DATE + " TEXT," + KEY_IMAGE_URL + " TEXT," + 
                KEY_LONGTITUDE + " TEXT," + KEY_LATITUDE + " TEXT," + KEY_LASTMODIFIED_PERSON 
                + " INTEGER," + " FOREIGN KEY (" + KEY_LASTMODIFIED_PERSON + ")	REFERENCES " + 
                TABLE_WORKERS + " (" + KEY_WORKER_ID + "))";
        String CREATE_PERSONS_TABLE = "CREATE TABLE " + TABLE_PERSONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_IMAGE_URL +
                " TEXT," + KEY_AUDIO_URL + " TEXT," + KEY_TAGS + " TEXT," + KEY_CAMP + "INTEGER, " + "FOREIGN KEY (" +
                KEY_CAMP + ") REFERENCES " + TABLE_CAMPS + "))";
        String CREATE_WORKERS_TABLE = "CREATE TABLE" + TABLE_WORKERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        db.execSQL(CREATE_CAMPS_TABLE);
        db.execSQL(CREATE_WORKERS_TABLE);
        db.execSQL(CREATE_PERSONS_TABLE);
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
 
    // Adding new camp
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
        values.put(KEY_LATITUDE, camp.getLatitude());
        values.put(KEY_LONGTITUDE, camp.getLongtitude());
 
        // Inserting Row
        db.insert(TABLE_CAMPS, null, values);
        db.close(); // Closing database connection
    }
    
    //Adding new person
    void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName()); 
        values.put(KEY_IMAGE_URL, person.getImageURL()); 
        values.put(KEY_AUDIO_URL, person.getAudioURL()); 
        values.put(KEY_TAGS, person.getTags()); 
        values.put(KEY_CAMP, person.getCamp()); 
        
        // Inserting Row
        db.insert(TABLE_PERSONS, null, values);
        db.close(); // Closing database connection
    }

    //Adding new worker
    void addWorker(Worker worker) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, worker.getName()); 
        
        // Inserting Row
        db.insert(TABLE_WORKERS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single CAMP
    Camp getCamp(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_CAMPS, new String[] { KEY_ID,
                KEY_FIRST_DATE, KEY_TYPE,KEY_BADGE, KEY_NO_PEOPLE, 
                KEY_LASTMODIFIED_DATE, KEY_IMAGE_URL, KEY_LASTMODIFIED_PERSON, KEY_LONGTITUDE, KEY_LATITUDE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Camp camp = new Camp(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),
                cursor.getString(6),Integer.parseInt(cursor.getString(7)), cursor.getString(8), cursor.getString(9));
        // return camp
        return camp;
    }

    //Getting single Person
    Person getPerson(int id){
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_PERSONS, new String[] { KEY_ID,
                KEY_NAME, KEY_IMAGE_URL,KEY_AUDIO_URL, KEY_TAGS, KEY_CAMP}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Person person = new Person(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                Integer.parseInt(cursor.getString(5)));
        // return camp
        return person;
    }

    //Geting single worker
    Person getWorker(int id){
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_WORKERS, new String[] { KEY_ID,
                KEY_NAME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Worker worker = new Worker(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return camp
        return worker;
    }
    // Getting All Camps
    public ArrayList<Camp> getAllCamps() {
        ArrayList<Camp> campList = new ArrayList<Camp>();
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
                camp.setImageURL(cursor.getString(7));
                camp.setLatitude(cursor.getString(8));
                camp.setLongtitude(cursor.getString(9));
                // Adding camp to list
                campList.add(camp);
            } while (cursor.moveToNext());
        }
        // return CAMP list
        return campList;
    }

    //Getting All Persons
    public ArrayList<Person> getAllPersons(){
        ArrayList<Camp> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PERSONS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setID(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setImageURL(cursor.getString(2));
                person.setAudioURL(cursor.getString(3));
                person.setTags(cursor.getString(4));
                person.setCamp(cursor.getString(5));
                // Adding camp to list
                personList.add(person);
            } while (cursor.moveToNext());
        }
        return personList;
    }

    //Geting All persons in one camp
    public ArrayList<Person> getPersons(int id){
        ArrayList<Camp> personList = new ArrayList<Person>();
        Cursor cursor = db.query(TABLE_PERSONS, new String[] { KEY_ID,
                KEY_NAME, KEY_IMAGE_URL,KEY_AUDIO_URL, KEY_TAGS, KEY_CAMP}, KEY_CAMP + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        SQLiteDatabase db = this.getWritableDatabase();
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setID(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setImageURL(cursor.getString(2));
                person.setAudioURL(cursor.getString(3));
                person.setTags(cursor.getString(4));
                person.setCamp(cursor.getString(5));
                // Adding camp to list
                personList.add(person);
            } while (cursor.moveToNext());
        }
        return personList;
    }

    //Geting all workers
    public ArrayList<Worker> getAllWorkers(){
        ArrayList<Worker> workerList = new ArrayList<Worker>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WORKERS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Worker worker = new Worker();
                worker.setID(Integer.parseInt(cursor.getString(0)));
                worker.setName(cursor.getString(1));
            }while(cursor.moveToNext());
        }
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
        values.put(KEY_LATITUDE,camp.getLatitude());
        values.put(KEY_LONGTITUDE, camp.getLongtitude()); 
        // updating row
        return db.update(TABLE_CAMPS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(camp.getID()) });
    }

    //Updating single person
    public int updatePerson(Person person){
        QLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, person.getName());
        values.put(KEY_IMAGE_URL, person.getImageURL());
        values.put(KEY_AUDIO_URL, person.getAudioURL());
        values.put(KEY_TAGS, person.getTags());
        values.put(KEY_CAMP, person.getCamp());
        return db.update(TABLE_PERSONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(person.getID()) });
    }

    //Updating single worker
    public int updateWorker(Worker worker){
        QLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, worker.getName());
        return db.update(TABLE_WORKERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(worker.getID()) });
    }

    public void deleteCamp(Camp camp) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CAMPS, KEY_ID + " = ?",
                new String[] { String.valueOf(camp.getID()) });
        db.close();
    }

    public void deletePerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSONS, KEY_ID + " = ?",
                new String[] { String.valueOf(person.getID()) });
        db.close();
    }

    public void deleteWorker(Worker worker){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORKERS, KEY_ID + " = ?",
                new String[] { String.valueOf(worker.getID()) });
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

    //Getting persons count
    public int getPersonsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PERSONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }

    //Geeting workers count
    public int getCampsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WORKERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}