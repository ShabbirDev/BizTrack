package com.zerocool.biztrack.DatabaseOperation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zerocool.biztrack.DataModel.CustomerTable;
import com.zerocool.biztrack.DataModel.ProductTable;
import com.zerocool.biztrack.DatabaseHandler.DatabaseManager;
import com.zerocool.biztrack.Fragments.AllProductList;
import com.zerocool.biztrack.Models.ModelRetrieve;
import com.zerocool.biztrack.Utils.UtillMets;

import java.util.ArrayList;
import java.util.Calendar;


public class Retrieve {

    public static final String TAG = Retrieve.class.getSimpleName();
    AllProductList allProductList = new AllProductList();


    public Retrieve() {
//        listDataSetters = new ProductData();
    }


    public static ArrayList<ModelRetrieve> getAllProductList() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + ProductTable.ALL_PRODUCT_TABLE + " ORDER BY " + ProductTable.COLOUMN_1 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //AllProductList allProductList = new AllProductList();
                ModelRetrieve modelRetrieve = new ModelRetrieve();
                modelRetrieve.setProductID(cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_1)));
                modelRetrieve.setProductName(cursor.getString(cursor.getColumnIndex(ProductTable.COLOUMN_2)));
                modelRetrieve.setUnitPrice(cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_3)));
                modelRetrieve.setCompany(cursor.getString(cursor.getColumnIndex(ProductTable.COLOUMN_4)));

                RetrieveList.add(modelRetrieve);
//                allProductList.refreshList();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }

    public static ArrayList<ModelRetrieve> getInventoryList() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + ProductTable.ALL_PRODUCT_TABLE + " WHERE " + ProductTable.COLOUMN_6 + " = '" + ProductTable.DisCarted + "' ORDER BY " + ProductTable.COLOUMN_1 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();
                modelRetrieve.setProductID(cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_1)));
                modelRetrieve.setProductName(cursor.getString(cursor.getColumnIndex(ProductTable.COLOUMN_2)));
                modelRetrieve.setCompany(cursor.getString(cursor.getColumnIndex(ProductTable.COLOUMN_4)));
                modelRetrieve.setUnitPrice(cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_3)));

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }

    public static ArrayList<ModelRetrieve> getToPurchaseList() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();

        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + ProductTable.ALL_PRODUCT_TABLE + " WHERE " + ProductTable.COLOUMN_6 + " = '" + ProductTable.Carted + "' ORDER BY " + ProductTable.COLOUMN_1 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();
                modelRetrieve.setProductID(cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_1)));
                modelRetrieve.setProductName(cursor.getString(cursor.getColumnIndex(ProductTable.COLOUMN_2)));
                modelRetrieve.setUnitPrice(cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_3)));
                modelRetrieve.setCompany(cursor.getString(cursor.getColumnIndex(ProductTable.COLOUMN_4)));
                modelRetrieve.setQuantity(cursor.getInt(cursor.getColumnIndex(ProductTable.COLOUMN_5)));
                System.out.println("============RETRIEVE DATA/ MODELUPDATE==========================QUANTITY:" + modelRetrieve.getQuantity());

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }


    public static ArrayList<ModelRetrieve> getCustomerProfileList() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " ORDER BY " + CustomerTable.COLOUMN_3 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerUID(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_2)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));

                System.out.println("=================================================" + modelRetrieve.getCustomerDue());

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }

    public static ArrayList<ModelRetrieve> getDuedCustomers() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " ORDER BY " + CustomerTable.COLOUMN_9 + " DESC;";
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " WHERE " + CustomerTable.COLOUMN_9 + " > 0 ORDER BY " + CustomerTable.COLOUMN_9 + " DESC;";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerUID(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_2)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));

                System.out.println("=================================================" + modelRetrieve.getCustomerDue());

                //if(modelRetrieve.getCustomerDue()>0){
                RetrieveList.add(modelRetrieve);
                //}


            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }


    public static ArrayList<ModelRetrieve> getMostValuedCustomers(String StartDate, String EndDate) {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String sql = "SELECT " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_1 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_2 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_3 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_4 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_5 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_6 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_7 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_8 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_9 + ", " +
                "(SELECT SUM (" + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_26 + ") FROM " +
                CustomerTable.CUSTOMER_HISTORY + " WHERE " + CustomerTable.CUSTOMER_HISTORY + " . " + CustomerTable.COLOUMN_22 + " = " + CustomerTable.CUSTOMER_TABLE + " . " +
                CustomerTable.COLOUMN_2 + " AND " + CustomerTable.COLOUMN_29 + " BETWEEN '" + StartDate + "' AND " +
                " '" + EndDate + "' ) AS Total  FROM " +
                CustomerTable.CUSTOMER_TABLE + " ORDER BY Total DESC; ";

        System.out.println("===================================" + CustomerTable.CUSTOMER_HISTORY + " . " + CustomerTable.COLOUMN_23 + "===================" +
                CustomerTable.CUSTOMER_TABLE + " . " + CustomerTable.COLOUMN_5);

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();
                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerUID(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_2)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));

                modelRetrieve.setTotal(cursor.getInt(cursor.getColumnIndex("Total")));
                if (modelRetrieve.getTotal() > 0) {
                    RetrieveList.add(modelRetrieve);
                }


            } while (cursor.moveToNext());
        }
        cursor.close();
        //}
        return RetrieveList;

    }

    public static ModelRetrieve ProfileDetails(String UID) {
        ModelRetrieve modelRetrieve = new ModelRetrieve();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " WHERE "+ CustomerTable.COLOUMN_5+ " = '"+ Phone + "' ORDER BY " + CustomerTable.COLOUMN_1 + " ASC;";
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " WHERE " + CustomerTable.COLOUMN_2 + " = '" + UID + "' ;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerUID(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_2)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_10)));
                modelRetrieve.setAccountCreated(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_11)));

                System.out.println("=================================================customer due from retrieve data " + modelRetrieve.getCustomerDue());
                System.out.println("=================================================customer Shopped from retrieve data " + modelRetrieve.getTotalShopped());
                System.out.println("=================================================customer Cashpaid from retrieve data " + modelRetrieve.getCashPaid());
                System.out.println("=================================================phone number from retreive data " + modelRetrieve.getPhoneNumber());


            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelRetrieve;
    }


    public static ModelRetrieve ProfileDetails2(String UID) {
        ModelRetrieve modelRetrieve = new ModelRetrieve();
        int ShoppTotal = 0;
        int CashTotal = 0;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        String sql = "SELECT  "
//                + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_3 +","
//                + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_4 +","
//                + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_5 +","
//                + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_6 +","
//                + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_24 +","
//                + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_26 +","
//
//                +"FROM " + CustomerTable.CUSTOMER_TABLE +
//                " INNER JOIN " + AdditionalInformation.TABLE + " ON " + TitleEntry.COLOUMN_1 + " = " + AdditionalInformation.COLOUMN_6 +
//                " INNER JOIN "+ CustomerTable.CUSTOMER_HISTORY +" ON "+CustomerTable.COLOUMN_2+ "="+CustomerTable.COLOUMN_22+
//                " WHERE "+ CustomerTable.COLOUMN_2 + " = '"+ UID + "' ;";


//        String sql = "SELECT " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_3 +", " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_4 +", " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_5 +", " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_6 +", " +
//                "(SELECT SUM ("+CustomerTable.CUSTOMER_HISTORY+"."+CustomerTable.COLOUMN_24 +") FROM "+
//                CustomerTable.CUSTOMER_HISTORY +" WHERE "+CustomerTable.CUSTOMER_HISTORY+" . "+ CustomerTable.COLOUMN_22 +
//                " = '"+ UID + "' ) AS ShoppedTotal," +
//                "(SELECT SUM ("+CustomerTable.CUSTOMER_HISTORY+"."+CustomerTable.COLOUMN_26 +") FROM "+
//                CustomerTable.CUSTOMER_HISTORY +" WHERE "+CustomerTable.CUSTOMER_HISTORY+" . "+ CustomerTable.COLOUMN_22 +
//                " = '"+ UID + "' ) AS CashTotal" +
//                "  FROM "+ CustomerTable.CUSTOMER_TABLE+" ; ";


        String sql = "SELECT " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_3 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_4 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_5 + ", " +
                "" + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_6 + ", " +

                " " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_24 + "," +
                " " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_26 +
                " FROM " + CustomerTable.CUSTOMER_TABLE +
                " INNER JOIN " + CustomerTable.CUSTOMER_HISTORY + " ON " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_2 +
                " = " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_22 +
                " WHERE " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_22 + " = '" + UID + "'";


//        String sql = "SELECT " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_3 +", " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_4 +", " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_5 +", " +
//                ""+CustomerTable.CUSTOMER_TABLE+"."+CustomerTable.COLOUMN_6 +", " +
//                "(SELECT SUM ("+CustomerTable.CUSTOMER_HISTORY+"."+CustomerTable.COLOUMN_24 +") AS ShoppedTotal," +
//                " SUM ("+CustomerTable.CUSTOMER_HISTORY+"."+CustomerTable.COLOUMN_26 +") AS CashTotal " +
//                " FROM "+CustomerTable.CUSTOMER_HISTORY+" WHERE "+ CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_22 +" = "
//                + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_2 +")"+
//                "  FROM "+ CustomerTable.CUSTOMER_TABLE+" WHERE "+ CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_2 +" ='"+UID+"' ; ";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_24)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_26)));

//                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex("ShoppedTotal")));
//                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex("CashTotal")));
//                modelRetrieve.setCustomerDue(modelRetrieve.getTotalShopped() - modelRetrieve.getCashPaid());

//                System.out.printf("=Shopped:================================="+modelRetrieve.getTotalShopped()+"==================="+modelRetrieve.getCashPaid()+"====================="+modelRetrieve.getCustomerDue());
//
////                ShoppTotal = ShoppTotal + modelRetrieve.getTotalShopped();
////                CashTotal = CashTotal + modelRetrieve.getCashPaid();
//
//
//                if (modelRetrieve.getTotalShopped() != 0) {
                ShoppTotal = ShoppTotal + modelRetrieve.getTotalShopped();
//                }
//                if (modelRetrieve.getCashPaid() != 0){
                CashTotal = CashTotal + modelRetrieve.getCashPaid();
//                }
//
                modelRetrieve.setTotalShopped(ShoppTotal);
                modelRetrieve.setCashPaid(CashTotal);
                modelRetrieve.setCustomerDue(ShoppTotal - CashTotal);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelRetrieve;
    }


    public static boolean isCustomerExists(String PhoneNumber) {
        ModelRetrieve modelRetrieve = new ModelRetrieve();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " WHERE " + CustomerTable.COLOUMN_5 + " = '" + PhoneNumber + "' ORDER BY " + CustomerTable.COLOUMN_1 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }


    }


    public static ArrayList<ModelRetrieve> getCustomerHistory(String UID) {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_HISTORY + " WHERE " + CustomerTable.COLOUMN_22 + " = '" + UID + "' ORDER BY " + CustomerTable.COLOUMN_21 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerHistoryID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_21)));
                modelRetrieve.setCustomerUID(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_22)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_23)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_24)));
                modelRetrieve.setTotalRecievable(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_25)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_26)));
                modelRetrieve.setCashBack(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_27)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_28)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_29)));

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }

    public static ModelRetrieve getCustomerPaymentHistory(String UID) {
        ModelRetrieve modelRetrieve = new ModelRetrieve();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_HISTORY + " WHERE " + CustomerTable.COLOUMN_22 + " = '" + UID + "' ORDER BY " + CustomerTable.COLOUMN_21 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                //ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerHistoryID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_21)));
                modelRetrieve.setCustomerUID(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_22)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_23)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_24)));
                modelRetrieve.setTotalRecievable(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_25)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_26)));
                modelRetrieve.setCashBack(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_27)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_28)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_29)));

                //RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelRetrieve;
    }


    public int totalCash(Calendar startDate, Calendar endDate) {
        int cash = 0;
        ModelRetrieve modelRetrieve = new ModelRetrieve();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_HISTORY +
                " ORDER BY " + CustomerTable.COLOUMN_21 + " DESC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_26)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_29)));

                if (startDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) < 0 &&
                        endDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) > 0) {
                    cash = cash + modelRetrieve.getCashPaid();
                }
            } while (cursor.moveToNext());

        }
        cursor.close();
        return cash;
    }

    public int totalSales(Calendar startDate, Calendar endDate) {
        int Sales = 0;
        ModelRetrieve modelRetrieve = new ModelRetrieve();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_HISTORY +
                " ORDER BY " + CustomerTable.COLOUMN_21 + " DESC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {

                modelRetrieve.setCashBack(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_27)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_24)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_29)));

                if (startDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) < 0 &&
                        endDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) > 0) {
//                    Sales = Sales + modelRetrieve.getTotalShopped() - modelRetrieve.getCashBack();
                    Sales = (Sales + modelRetrieve.getTotalShopped()) - modelRetrieve.getCashBack();

                }
            } while (cursor.moveToNext());

        }
        cursor.close();
        return Sales;
    }

    public int totalRecievables(Calendar startDate, Calendar endDate) {
        int Recievable = 0;
        ModelRetrieve modelRetrieve = new ModelRetrieve();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE +
                " ORDER BY " + CustomerTable.COLOUMN_1 + " DESC;";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_10)));
                if (startDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) < 0 &&
                        endDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) > 0) {
                    Recievable = Recievable + modelRetrieve.getCustomerDue();
                }
            } while (cursor.moveToNext());

        }
        cursor.close();
        return Recievable;
    }


    public int getTodaysCashTotal() {
        int sum = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        String sql = "SELECT SUM("+CustomerTable.COLOUMN_26+") as Total FROM "+ CustomerTable.CUSTOMER_HISTORY +" WHERE " + CustomerTable.COLOUMN_29 +
// " BETWEEN datetime('now', 'start of day') AND datetime('now', 'localtime');";

        String sql = "SELECT SUM(" + CustomerTable.COLOUMN_26 + ") as Total FROM " + CustomerTable.CUSTOMER_HISTORY +
                " WHERE " + CustomerTable.COLOUMN_29 + " >= datetime('now', '-.1 hour') AND " + CustomerTable.COLOUMN_29 + " <= datetime('now', 'localtime');";


//        String sql = "SELECT SUM("+CustomerTable.COLOUMN_26+") as Total FROM "+ CustomerTable.CUSTOMER_HISTORY +
//                " WHERE datetime(" + CustomerTable.COLOUMN_29 + ") >= datetime('now', '-.016 hours');";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst())
            sum = cursor.getInt(cursor.getColumnIndex("Total"));

        cursor.close();
        return sum;
    }

    public static ArrayList<ModelRetrieve> getCustomerOfMonth(Calendar startDate, Calendar endDate) {
        int Cash = 0;
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();


        String sql = "SELECT " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_1 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_3 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_4 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_5 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_6 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_7 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_8 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_9 + ", " +
                " " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_10 + ", " +
                " " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_26 + ", " +
                " " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_29 + ", " +
                " " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_23 + " " +
                " FROM " + CustomerTable.CUSTOMER_TABLE +
                " INNER JOIN " + CustomerTable.CUSTOMER_HISTORY + " ON " + CustomerTable.CUSTOMER_TABLE + "." + CustomerTable.COLOUMN_5 +
                " = " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_23 +
                " ORDER BY " + CustomerTable.CUSTOMER_HISTORY + "." + CustomerTable.COLOUMN_26 + " DESC ;";

//            " FROM " + CustomerTable.CUSTOMER_HISTORY +
//                    " INNER JOIN " + CustomerTable.CUSTOMER_TABLE + " ON " + CustomerTable.COLOUMN_23 + " = " + CustomerTable.COLOUMN_5 +
//                    " WHERE "+CustomerTable.COLOUMN_23+" = '01750035452' " +
//                    " AND "+CustomerTable.COLOUMN_29+" BETWEEN datetime('now', 'start of day') AND datetime('now', 'localtime') ORDER BY Total DESC;";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();


                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));

                String phone1 = modelRetrieve.getPhoneNumber();

                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_10)));

                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_26)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_29)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_23)));
                String phone2 = modelRetrieve.getPhoneNumber();


//                modelRetrieve.setTotal(cursor.getInt(cursor.getColumnIndex("Total")));

                System.out.println("===========================================HELLO FROM RETRIEVE JOINING TABLE");
                if (phone1.equals(phone2)) {
                    if (startDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) < 0 &&
                            endDate.compareTo(UtillMets.getQueryTime(modelRetrieve.getDate())) > 0) {
                        Cash = Cash + modelRetrieve.getCashPaid();
                        //RetrieveList.add(modelRetrieve);
                    }

                }
                if (Cash > 0) {
                    RetrieveList.add(modelRetrieve);
                }

//                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //}
        return RetrieveList;

    }

    public static ArrayList<ModelRetrieve> getValuedCustomersOfMonth() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        ArrayList<ModelRetrieve> TotalList = new ArrayList<ModelRetrieve>();
        //TotalList = getMostValuedCustomers();

        RetrieveList.clear();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " ORDER BY " + CustomerTable.COLOUMN_8 + " DESC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_10)));

                System.out.println("=================================================" + modelRetrieve.getCustomerDue());

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }


    public static ArrayList<ModelRetrieve> getTodaysValuedCustomers() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_HISTORY + " WHERE " + CustomerTable.COLOUMN_29 + " BETWEEN datetime('now', 'start of day') AND datetime('now', 'localtime')  " +
                "ORDER BY SELECT SUM(" + CustomerTable.COLOUMN_26 + ") as Total FROM " + CustomerTable.CUSTOMER_HISTORY + "" + CustomerTable.COLOUMN_8 + " DESC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));
                modelRetrieve.setDate(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_10)));

                System.out.println("=================================================" + modelRetrieve.getCustomerDue());

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }

    public int getOutlyData(String startDate, String endDate) {
        double outdoorHourly = 0;
        int sum = 0;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

//        String sql = "SELECT SUM("+CustomerTable.COLOUMN_26+") as Total FROM "+ CustomerTable.CUSTOMER_HISTORY +
//                " WHERE datetime(" + CustomerTable.COLOUMN_29 + ") >= datetime('now', '-1 hour');";


        String sql = "Select SUM("
                + CustomerTable.COLOUMN_26 + ") FROM (Select * FROM "
                + CustomerTable.CUSTOMER_HISTORY + " WHERE " + CustomerTable.COLOUMN_29 + " >= '"
                + endDate + "' AND " + CustomerTable.COLOUMN_29 + " < '" + startDate
                + "');";
        Cursor cursor = db.rawQuery(sql, null);

        try {

            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {
//                        outdoorHourly = cursor.getInt(0);
                        sum = cursor.getInt(0);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String hourlyData = decimalFormat.format(outdoorHourly);
//        hourlyData = hourlyData.replace(",", ".");
//        return hourlyData;

        return sum;

    }

    public int getTotalRecievable() {
        int sum = 0;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//        String sumQuery=String.format("SELECT SUM(%s) as Total FROM %s",CustomerTable.COLOUMN_24,CustomerTable.CUSTOMER_HISTORY);
        String sql = String.format("SELECT SUM(%s) as Total FROM %s", CustomerTable.COLOUMN_9, CustomerTable.CUSTOMER_TABLE);
        Cursor cursor = db.rawQuery(sql, null);
        //Cursor cursor=database.rawQuery(sumQuery,null);
        if (cursor.moveToFirst())
            sum = cursor.getInt(cursor.getColumnIndex("Total"));

        cursor.close();
        return sum;
    }

    public static ArrayList<ModelRetrieve> getValuedCustomers() {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " ORDER BY " + CustomerTable.COLOUMN_8 + " DESC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));

                System.out.println("=================================================" + modelRetrieve.getCustomerDue());

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }

    public static ArrayList<ModelRetrieve> getCustomerProfilee(int CustomerID) {
        ArrayList<ModelRetrieve> RetrieveList = new ArrayList<ModelRetrieve>();
        RetrieveList.clear();
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String sql = "SELECT * FROM " + CustomerTable.CUSTOMER_TABLE + " WHERE " + CustomerTable.COLOUMN_1 + " = '" + CustomerID + "' ORDER BY " + CustomerTable.COLOUMN_3 + " ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRetrieve modelRetrieve = new ModelRetrieve();

                modelRetrieve.setCustomerID(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_1)));
                modelRetrieve.setCustomerName(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_3)));
                modelRetrieve.setCustomerImage(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_4)));
                modelRetrieve.setPhoneNumber(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_5)));
                modelRetrieve.setAddress(cursor.getString(cursor.getColumnIndex(CustomerTable.COLOUMN_6)));
                modelRetrieve.setTotalShopped(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_7)));
                modelRetrieve.setCashPaid(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_8)));
                modelRetrieve.setCustomerDue(cursor.getInt(cursor.getColumnIndex(CustomerTable.COLOUMN_9)));

                RetrieveList.add(modelRetrieve);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return RetrieveList;
    }


}
