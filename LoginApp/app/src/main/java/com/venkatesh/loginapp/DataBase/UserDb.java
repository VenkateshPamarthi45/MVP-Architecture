package com.venkatesh.loginapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.venkatesh.loginapp.Model.UserModel;

/**
 * Created by Venkatesh on 1/13/16.
 */
public class UserDb extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String DATABASE_NAME = "usersDb";
    public static final String KEY_INDEX = "id";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PHONENO = "phoneno";
    public static final String KEY_EMAIL_ID = "email";
    public static final String KEY_PASSWORD = "password";



    public UserDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_INDEX + " INTEGER PRIMARY KEY, " +
                KEY_FIRSTNAME + " TEXT, "
                + KEY_LASTNAME + " TEXT, " +
                KEY_USERNAME + " TEXT, " +
                KEY_PHONENO + " TEXT, " +
                KEY_EMAIL_ID + " TEXT, " +
                KEY_PASSWORD + " TEXT" + ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ");";
        db.execSQL(DROP_TABLE);
    }

    public void addUser(UserModel userModel){
        try{
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_FIRSTNAME,userModel.getFirstName());
            contentValues.put(KEY_LASTNAME,userModel.getLastName());
            contentValues.put(KEY_USERNAME,userModel.getUserName());
            contentValues.put(KEY_PHONENO,userModel.getPhoneNo());
            contentValues.put(KEY_EMAIL_ID,userModel.getEmailId());
            contentValues.put(KEY_PASSWORD,userModel.getPassword());
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserModel getUser(String userName, String password){
        try{
            String SELECT_USER_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_USERNAME + " = '" + userName + "' AND " + KEY_PASSWORD + " = '" + password + "'; ";
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.rawQuery(SELECT_USER_QUERY, null);
            if(cursor != null){
                cursor.moveToFirst();
            }
            UserModel userModel = new UserModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            cursor.close();
            database.close();
            return userModel;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean isUserPresent(String userName, String password){
        try{
            String SELECT_USER_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_USERNAME + " = '" + userName + "' AND " + KEY_PASSWORD + " = '" + password + "'; ";
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.rawQuery(SELECT_USER_QUERY, null);
            if(cursor != null){
                cursor.moveToFirst();
            }
            int count = cursor.getCount();
            if(count > 0){
                cursor.close();
                database.close();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Boolean isUserNameExists(String userName){
        try{
            String SELECT_USER_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_USERNAME + " = '" + userName + "'; ";
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.rawQuery(SELECT_USER_QUERY, null);
            if(cursor != null){
                cursor.moveToFirst();
            }
            int count = cursor.getCount();
            if(count > 0){
                cursor.close();
                database.close();
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
