package com.zerocool.biztrack.DatabaseOperation;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.zerocool.biztrack.DataModel.CustomerTable;
import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseHandler.DatabaseManager;
import com.zerocool.biztrack.Models.ModelUpdate;

/**
 * Created by CrashOverride on 1/9/2018.
 */

public class Update {

    public  boolean Product(ModelUpdate modelUpdate) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.COLOUMN_2, modelUpdate.getProductName());
        contentValues.put(ProductTable.COLOUMN_3, modelUpdate.getUnitPrice());
        contentValues.put(ProductTable.COLOUMN_4, modelUpdate.getCompany());
        db.update(ProductTable.ALL_PRODUCT_TABLE, contentValues, ProductTable.COLOUMN_1 + "=" + modelUpdate.getProductID(), null);
        DatabaseManager.getInstance().closeDatabase();
        return true;
    }


    public  boolean Carted(ModelUpdate modelUpdate) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.COLOUMN_5, modelUpdate.getQuantity());
        contentValues.put(ProductTable.COLOUMN_6, modelUpdate.getIfCarted());
        db.update(ProductTable.ALL_PRODUCT_TABLE, contentValues, ProductTable.COLOUMN_1 + "=" + modelUpdate.getProductID(), null);
        DatabaseManager.getInstance().closeDatabase();
        return true;
    }



    public  boolean DisCarted(ModelUpdate modelUpdate) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.COLOUMN_5, modelUpdate.getQuantity());
        contentValues.put(ProductTable.COLOUMN_6, modelUpdate.getIfCarted());
        db.update(ProductTable.ALL_PRODUCT_TABLE, contentValues, ProductTable.COLOUMN_1 + "=" + modelUpdate.getProductID(), null);
        DatabaseManager.getInstance().closeDatabase();
        return true;
    }






    public  boolean CustomerDue(ModelUpdate modelUpdate) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CustomerTable.COLOUMN_7, modelUpdate.getTotalShopped());
        contentValues.put(CustomerTable.COLOUMN_8, modelUpdate.getCashPaid());
        contentValues.put(CustomerTable.COLOUMN_9, modelUpdate.getCustomerDue());
        contentValues.put(CustomerTable.COLOUMN_10, modelUpdate.getDate());

        System.out.println("=============================================update data : due"+modelUpdate.getCustomerDue());
        System.out.println("=============================================update data: UID number"+modelUpdate.getCustomerUID());

//        db.update(CustomerTable.CUSTOMER_TABLE, contentValues, CustomerTable.COLOUMN_2 + " = '" + modelUpdate.getCustomerUID()+" '", null);
        db.update(CustomerTable.CUSTOMER_TABLE, contentValues, CustomerTable.COLOUMN_2 + " = ?", new String[]{modelUpdate.getCustomerUID()});

        DatabaseManager.getInstance().closeDatabase();
        return true;
    }

    public  boolean updateCustomerProfile(ModelUpdate modelUpdate) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomerTable.COLOUMN_3, modelUpdate.getCustomerName());
        contentValues.put(CustomerTable.COLOUMN_4, modelUpdate.getCustomerImage());
        contentValues.put(CustomerTable.COLOUMN_5, modelUpdate.getPhoneNumber());
        contentValues.put(CustomerTable.COLOUMN_6, modelUpdate.getAddress());

//        db.update(CustomerTable.CUSTOMER_TABLE, contentValues, CustomerTable.COLOUMN_1 + "=" + modelUpdate.getCustomerID(), null);
//        db.update(CustomerTable.CUSTOMER_TABLE, contentValues, CustomerTable.COLOUMN_2 + "= '" + modelUpdate.getCustomerUID()+" '", null);
        db.update(CustomerTable.CUSTOMER_TABLE, contentValues, CustomerTable.COLOUMN_22 + " = ?", new String[]{modelUpdate.getCustomerUID()});

        System.out.println("=========================================PhoneNumber Profile:========="+modelUpdate.getPhoneNumber());

        DatabaseManager.getInstance().closeDatabase();
        return true;
    }


    public  boolean ProfileHistory(ModelUpdate modelUpdate) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomerTable.COLOUMN_23, modelUpdate.getPhoneNumber());

        db.update(CustomerTable.CUSTOMER_HISTORY, contentValues, CustomerTable.COLOUMN_23 + "=" + modelUpdate.getPhoneNumber(), null);
        System.out.println("=========================================PhoneNumber History:========="+modelUpdate.getPhoneNumber());

        DatabaseManager.getInstance().closeDatabase();
        return true;
    }




//
//    public  boolean updateToPurchase(ModelUpdate modelUpdate) {
//        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ProductTable.COLOUMN_25, modelUpdate.getProductName());
//        contentValues.put(ProductTable.COLOUMN_27, modelUpdate.getProductPrice());
//        db.update(ProductTable.TO_PURCHASE_TABLE, contentValues, ProductTable.COLOUMN_23 + "=" + modelUpdate.getProductID(), null);
//        DatabaseManager.getInstance().closeDatabase();
//        return true;
//    }


}
