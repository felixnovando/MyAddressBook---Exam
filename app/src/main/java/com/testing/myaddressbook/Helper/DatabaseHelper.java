package com.testing.myaddressbook.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Insert;

import com.testing.myaddressbook.Models.Coordinates;
import com.testing.myaddressbook.Models.Employee;
import com.testing.myaddressbook.Models.Location;
import com.testing.myaddressbook.Models.Name;
import com.testing.myaddressbook.Models.Picture;
import com.testing.myaddressbook.Models.Registered;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "employee.db";
    public static final String TABLE_NAME = "employee_table";
    public static final String [] COL_NAMES = {
            "id", "email", "phone", "cell", "first_name", "last_name",
            "city", "country", "latitude", "longitude", "registered_date",
            "thumbnail_url", "medium_url", "large_url"
    };

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + " ("
                + "id INTEGER PRIMARY KEY,"
                + "email TEXT,"
                + "phone TEXT,"
                + "cell TEXT,"
                + "first_name TEXT,"
                + "last_name TEXT,"
                + "city TEXT,"
                + "country TEXT,"
                + "latitude REAL,"
                + "longitude REAL,"
                + "registered_date DATETIME,"
                + "thumbnail_url TEXT,"
                + "medium_url TEXT,"
                + "large_url TEXT"
                + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String convertDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return dateString;
    }

    public boolean addData(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COL_NAMES[0], employee.getId());
        cv.put(COL_NAMES[1], employee.getEmail());
        cv.put(COL_NAMES[2], employee.getPhone());
        cv.put(COL_NAMES[3], employee.getCell());
        cv.put(COL_NAMES[4], employee.getName().firstName);
        cv.put(COL_NAMES[5], employee.getName().lastName);
        cv.put(COL_NAMES[6], employee.getLocation().city);
        cv.put(COL_NAMES[7], employee.getLocation().country);
        cv.put(COL_NAMES[8], employee.getLatitude());
        cv.put(COL_NAMES[9], employee.getLongitude());
        cv.put(COL_NAMES[10], convertDate(employee.getRegisterDate()));
        cv.put(COL_NAMES[11], employee.getPicture().thumbnailUrl);
        cv.put(COL_NAMES[12], employee.getPicture().mediumUrl);
        cv.put(COL_NAMES[13], employee.getPicture().largeUrl);

        long insert = db.insert(TABLE_NAME, null, cv);
        if(insert == -1) return false;
        return true;
    }

    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> list = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Employee employee = new Employee(
                        cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        new Name(
                                cursor.getString(4),
                                cursor.getString(5)
                        ),
                        new Location(
                                cursor.getString(6),
                                cursor.getString(7),
                                new Coordinates(
                                        cursor.getDouble(8),
                                        cursor.getDouble(9)
                                )
                        ),
//                      datenya di get as long
                        new Registered(
                                new Date(cursor.getLong(10) * 1000)
                        ),
                        new Picture(
                                cursor.getString(11),
                                cursor.getString(12),
                                cursor.getString(13)
                        )
                );
                list.add(employee);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String query = String.format("DELETE FROM %s WHERE id = %d",TABLE_NAME, id);
        database.execSQL(query);
    }


}
