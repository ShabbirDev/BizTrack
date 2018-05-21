package com.zerocool.biztrack.Models;

/**
 * Created by CrashOverride on 1/9/2018.
 */

public class ModelUpdate {


    private String ProductName,IfCarted,Company;
    private int UnitPrice,ProductID,Quantity;




    private String CustomerName,CustomerUID,Address,CustomerImage,Date,PhoneNumber,AccountCreated;
    private int CustomerID, TotalRecievable,CashPaid,CustomerDue,CustomerHistoryID,TotalShopped;
    private int CashBack;


    public String getCustomerUID() {
        return CustomerUID;
    }

    public void setCustomerUID(String customerUID) {
        CustomerUID = customerUID;
    }

    public String getAccountCreated() {
        return AccountCreated;
    }

    public void setAccountCreated(String accountCreated) {
        AccountCreated = accountCreated;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getIfCarted() {
        return IfCarted;
    }

    public void setIfCarted(String ifCarted) {
        IfCarted = ifCarted;
    }

    public int getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }





















    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCustomerImage() {
        return CustomerImage;
    }

    public void setCustomerImage(String customerImage) {
        CustomerImage = customerImage;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getTotalRecievable() {
        return TotalRecievable;
    }

    public void setTotalRecievable(int totalRecievable) {
        TotalRecievable = totalRecievable;
    }

    public int getCashPaid() {
        return CashPaid;
    }

    public void setCashPaid(int cashPaid) {
        CashPaid = cashPaid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getCustomerDue() {
        return CustomerDue;
    }

    public void setCustomerDue(int customerDue) {
        CustomerDue = customerDue;
    }

    public int getCustomerHistoryID() {
        return CustomerHistoryID;
    }

    public void setCustomerHistoryID(int customerHistoryID) {
        CustomerHistoryID = customerHistoryID;
    }

    public int getCashBack() {
        return CashBack;
    }

    public void setCashBack(int cashBack) {
        CashBack = cashBack;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public int getTotalShopped() {
        return TotalShopped;
    }

    public void setTotalShopped(int totalShopped) {
        TotalShopped = totalShopped;
    }


}
