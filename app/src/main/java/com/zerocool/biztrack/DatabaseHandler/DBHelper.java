package com.zerocool.biztrack.DatabaseHandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zerocool.biztrack.App;
import com.zerocool.biztrack.DataModel.CustomerTable;
import com.zerocool.biztrack.DataModel.CustomerTableRepo;
import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DataModel.ProductTableRepo;


public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;
    // Database ProductData
    private static final String DATABASE_NAME = "Product.db";
    private static final String TAG = DBHelper.class.getSimpleName().toString();

    public DBHelper( ) {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }
    public DBHelper(Context context, String DatabaseName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DatabaseName, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ProductTableRepo.createProductTable());
        db.execSQL(CustomerTableRepo.createCustomerProfileTable());
        db.execSQL(CustomerTableRepo.createCustomerHistoryTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        db.execSQL("DROP TABLE IF EXISTS " + ProductTable.ALL_PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CustomerTable.CUSTOMER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CustomerTable.CUSTOMER_HISTORY);
        onCreate(db);
    }


    public Cursor getData(String sql, Object o){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }


}