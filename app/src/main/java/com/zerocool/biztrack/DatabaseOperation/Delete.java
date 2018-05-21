package com.zerocool.biztrack.DatabaseOperation;

import android.database.sqlite.SQLiteDatabase;

import com.zerocool.biztrack.DataModel.CustomerTable;
import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseHandler.DatabaseManager;

/**
 * Created by CrashOverride on 1/9/2018.
 */

public class Delete {

    ProductTable productTable = new ProductTable();

    public void deleteAllItemInProducts() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(ProductTable.ALL_PRODUCT_TABLE, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }


    public static void singleProduct(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(ProductTable.ALL_PRODUCT_TABLE, ProductTable.COLOUMN_1 + "=" + id, null);
        DatabaseManager.getInstance().closeDatabase();
    }


    public void deleteOneCustomer(String UID) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

//        db.delete(CustomerTable.CUSTOMER_TABLE, CustomerTable.COLOUMN_2 + "= '" + UID+" '", null);
        db.delete(CustomerTable.CUSTOMER_TABLE, CustomerTable.COLOUMN_2 + "= ?", new String[]{UID});

        System.out.println("===============================UID DELETE: "+UID);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void deleteSingleHistory(int id) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(CustomerTable.CUSTOMER_HISTORY, CustomerTable.COLOUMN_21 + "=" + id, null);
        DatabaseManager.getInstance().closeDatabase();
    }


    public void deleteAllHistory(String UID) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

//        db.delete(CustomerTable.CUSTOMER_HISTORY, CustomerTable.COLOUMN_22 + "= '" + UID+" '", null);
        db.delete(CustomerTable.CUSTOMER_HISTORY, CustomerTable.COLOUMN_22 + "= ?", new String[]{UID});

        System.out.println("===============================UID DELETE: "+UID);


        DatabaseManager.getInstance().closeDatabase();
    }



}
