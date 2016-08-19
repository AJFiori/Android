package com.example.anthonyjfiori.sqllitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anthony J. Fiori on 8/18/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bankManager";

    // Contacts table name
    private static final String TABLE_BANKACCOUNTS = "bankAccount";

    // BankAccount Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BANKACCOUNTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANKACCOUNTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new BankAccount
    void addBankAccount(BankAccount addAccount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
    // BankAccount Name
        values.put(KEY_NAME, addAccount.getName());

    // BankAccount Type
        values.put(KEY_TYPE, addAccount.getType());

        // Inserting Row
        db.insert(TABLE_BANKACCOUNTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single BankAccount
    BankAccount getBankAccount(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BANKACCOUNTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_TYPE }, KEY_NAME+ "=?",
                new String[] {name}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        BankAccount getAccount = new BankAccount(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return bankAccount
        return getAccount;
    }

    // Getting All Bank Accounts
    public List<BankAccount> getAllAccounts() {
        List<BankAccount> accountList = new ArrayList<BankAccount>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BANKACCOUNTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BankAccount getAllAccounts = new BankAccount();
                getAllAccounts.setID(Integer.parseInt(cursor.getString(0)));
                getAllAccounts.setName(cursor.getString(1));
                getAllAccounts.setType(cursor.getString(2));
                // Adding contact to list
                accountList.add(getAllAccounts);
            } while (cursor.moveToNext());
        }

        // return contact list
        return accountList;
    }

    // Updating single Bank Account
    public int updateBankAccount(BankAccount updateAccount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, updateAccount.getName());
        values.put(KEY_TYPE, updateAccount.getType());

        // updating row
        return db.update(TABLE_BANKACCOUNTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(updateAccount.getID()) });
    }

    // Deleting single bank account
    public void deleteBankAccount(BankAccount deleteAccount) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BANKACCOUNTS, KEY_ID + " = ?",
                new String[] { String.valueOf(deleteAccount.getID()) });
        db.close();
    }

    // Getting Bank Account Count
    public int getBankAccountCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BANKACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int results = cursor.getCount();
        cursor.close();

        // return count
        return results;
    }
}

