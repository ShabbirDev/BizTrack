package com.zerocool.biztrack.DatabaseOperation;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zerocool.biztrack.DataModel.CustomerTable;
import com.zerocool.biztrack.DatabaseHandler.DatabaseManager;
import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.Models.ModelSave;

/**
 * Created by CrashOverride on 1/9/2018.
 */

public class Save {


    public int saveProducts(ModelSave modelSave) {
        int ID;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductTable.COLOUMN_2, modelSave.getProductName());
        values.put(ProductTable.COLOUMN_3, modelSave.getUnitPrice());
        values.put(ProductTable.COLOUMN_4, modelSave.getCompany());
        values.put(ProductTable.COLOUMN_5, modelSave.getQuantity());
        values.put(ProductTable.COLOUMN_6, modelSave.getIfCarted());


        ID = (int) db.insert(ProductTable.ALL_PRODUCT_TABLE, null, values);
        //System.out.println("==========================="+ID+"===============");
        DatabaseManager.getInstance().closeDatabase();

        return ID;

    }


    public  String CustomerProfile(ModelSave modelSave) {
        int ID;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerTable.COLOUMN_2, modelSave.getCustomerUID());
        values.put(CustomerTable.COLOUMN_3, modelSave.getCustomerName());
        values.put(CustomerTable.COLOUMN_4, modelSave.getCustomerImage());
        values.put(CustomerTable.COLOUMN_5, modelSave.getPhoneNumber());
        values.put(CustomerTable.COLOUMN_6, modelSave.getAddress());
        values.put(CustomerTable.COLOUMN_7, modelSave.getTotalShopped());
        values.put(CustomerTable.COLOUMN_8, modelSave.getCashPaid());
        values.put(CustomerTable.COLOUMN_9, modelSave.getCustomerDue());
        values.put(CustomerTable.COLOUMN_10, modelSave.getDate());
        values.put(CustomerTable.COLOUMN_11, modelSave.getAccountCreated());


        ID = (int) db.insert(CustomerTable.CUSTOMER_TABLE, null, values);
        System.out.println("=================================Save=====phone="+modelSave.getPhoneNumber()+"===============");
        DatabaseManager.getInstance().closeDatabase();

        return modelSave.getCustomerUID();

    }


    public static int CustomerHistory(ModelSave modelSave) {
        int ID;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerTable.COLOUMN_22, modelSave.getCustomerUID());
        values.put(CustomerTable.COLOUMN_23, modelSave.getPhoneNumber());
        values.put(CustomerTable.COLOUMN_24, modelSave.getTotalShopped());
        values.put(CustomerTable.COLOUMN_25, modelSave.getTotalRecievable());
        values.put(CustomerTable.COLOUMN_26, modelSave.getCashPaid());
        values.put(CustomerTable.COLOUMN_27, modelSave.getCashBack());
        values.put(CustomerTable.COLOUMN_28, modelSave.getCustomerDue());
        values.put(CustomerTable.COLOUMN_29, modelSave.getDate());


        ID = (int) db.insert(CustomerTable.CUSTOMER_HISTORY, null, values);

        System.out.println("===========================" + modelSave.getPhoneNumber() + "========Phone=======");
        System.out.println("===========================" + modelSave.getTotalRecievable() + "====Recievable===========");
        System.out.println("===========================" + modelSave.getCashPaid() + "========CashPaid=======");
        System.out.println("===========================" + modelSave.getCustomerDue() + "======CusDue=========");

        DatabaseManager.getInstance().closeDatabase();

        return ID;

    }


    public int getID() {
        Cursor cursor = null;
        int id = 0;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        try {
            String query = " SELECT " + ProductTable.COLOUMN_1 + " FROM " + ProductTable.ALL_PRODUCT_TABLE + " ORDER BY " + ProductTable.COLOUMN_1 + " DESC LIMIT 0,1";
            cursor = db.rawQuery(query, new String[]{});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id = cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_1));
            }
            return id;
        } finally {
            cursor.close();
        }
    }


}
