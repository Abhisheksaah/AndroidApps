package com.example.calenderevents.data_src.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.calenderevents.model.Reminder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reminder_db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "reminder_tbl";
    private static final String DESC_COL = "description_col";
    private static final String DATE_COL = "date_col";


    public DBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE "+TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DESC_COL +" TEXT, "+
                DATE_COL +" TEXT )";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Reminder> getAllReminder()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        List<Reminder> reminders = new ArrayList<>();

        try {
            if(cursor.moveToFirst())
            {
                do{
                    String desc = cursor.getString(cursor.getColumnIndexOrThrow(DESC_COL));
                    LocalDate date = LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(DATE_COL)));
                    reminders.add(new Reminder(desc,date));
                }while (cursor.moveToNext());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        cursor.close();
        db.close();
        return reminders;
    }

    public void addReminder(Reminder reminder)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DESC_COL, reminder.description);
        contentValues.put(DATE_COL, reminder.date.toString());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public void deleteReminder(Reminder reminder)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME+" WHERE "+DESC_COL+"='"+reminder.description+
                "' "+"AND "+ DATE_COL+"='"+reminder.date.toString()+"';";
        db.execSQL(query);
        db.close();
    }

}
