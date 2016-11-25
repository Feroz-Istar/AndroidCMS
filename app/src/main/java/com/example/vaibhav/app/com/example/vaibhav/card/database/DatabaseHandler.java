package com.example.vaibhav.app.com.example.vaibhav.card.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feroz on 24/11/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "talentify";
    // Contacts table name
    private static final String TABLE_CONTACTS = "content";
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTENT_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT"+ ")";
        sqLiteDatabase.execSQL(CREATE_CONTENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void saveContent(String id,String content){

        Cursor cursor =getData(Integer.parseInt(id));
        if(cursor != null && cursor.getCount() >0){
            System.out.println("updateContent done");
            updateContent(id,content);
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, id);
            contentValues.put(KEY_NAME, content);
            db.insert(TABLE_CONTACTS, null, contentValues);
            System.out.println("saveContent done");
        }
    }

    public void updateContent(String id,String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID,id);
        contentValues.put(KEY_NAME,content);
        db.update(TABLE_CONTACTS, contentValues, "id = ? ", new String[] { id} );
        System.out.println("updateContent done");

    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("delete content done");

        return db.delete(TABLE_CONTACTS,
                "id = ? ",
                new String[] { Integer.toString(id) });

    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from content where id="+id+"", null );
        System.out.println("get content done");

        return res;
    }


    public List<String> getAllContacts() {
        List<String> contactList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                contactList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}
