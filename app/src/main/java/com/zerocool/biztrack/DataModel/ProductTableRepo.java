package com.zerocool.biztrack.DataModel;


public class ProductTableRepo {

    private ProductTable productTable;
    public ProductTableRepo() {
        productTable = new ProductTable();
    }


    public static String createProductTable() {
        return "CREATE TABLE " + ProductTable.ALL_PRODUCT_TABLE + "("

                + ProductTable.COLOUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + ProductTable.COLOUMN_2 + " TEXT,"
                + ProductTable.COLOUMN_3 + " INTEGER,"
                + ProductTable.COLOUMN_4 + " TEXT,"
                + ProductTable.COLOUMN_5 + " INTEGER,"
                + ProductTable.COLOUMN_6 + " TEXT )";
    }









}
