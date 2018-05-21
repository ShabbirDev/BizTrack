package com.zerocool.biztrack.DataModel;


public class CustomerTableRepo {

    private ProductTable productTable;
    public CustomerTableRepo() {
        productTable = new ProductTable();
    }


    public static String createCustomerProfileTable() {
        return "CREATE TABLE " + CustomerTable.CUSTOMER_TABLE + "("

                + CustomerTable.COLOUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + CustomerTable.COLOUMN_2 + " TEXT,"
                + CustomerTable.COLOUMN_3 + " TEXT,"
                + CustomerTable.COLOUMN_4 + " TEXT,"
                + CustomerTable.COLOUMN_5 + " TEXT,"
                + CustomerTable.COLOUMN_6 + " TEXT,"
                + CustomerTable.COLOUMN_7 + " INTEGER,"
                + CustomerTable.COLOUMN_8 + " INTEGER,"
                + CustomerTable.COLOUMN_9 + " INTEGER,"
                + CustomerTable.COLOUMN_10 + " TEXT,"
                + CustomerTable.COLOUMN_11 +" TEXT )";
    }



    public static String createCustomerHistoryTable() {
        return "CREATE TABLE " + CustomerTable.CUSTOMER_HISTORY + "("

                + CustomerTable.COLOUMN_21 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + CustomerTable.COLOUMN_22 + " TEXT,"
                + CustomerTable.COLOUMN_23 + " TEXT,"
                + CustomerTable.COLOUMN_24 + " INTEGER,"
                + CustomerTable.COLOUMN_25 + " INTEGER,"
                + CustomerTable.COLOUMN_26 + " INTEGER,"
                + CustomerTable.COLOUMN_27 + " INTEGER,"
                + CustomerTable.COLOUMN_28 + " INTEGER,"
                + CustomerTable.COLOUMN_29 + " TEXT )";
    }








}
