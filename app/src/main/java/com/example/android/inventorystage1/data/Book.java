package com.example.android.inventorystage1.data;

import android.provider.BaseColumns;

/**
 * Created by Emoke Hajdu on 7/26/2018.
 */

public class Book {
    public final class Contract {
        private Contract() {}

        public final static String TABLE_NAME = "books";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME ="name";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_QUANTITY = "quantity";
        public final static String COLUMN_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }

    public Book(String name, int quantity, double price, String supplierName, String supplierPhoneNumber) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.supplierName = supplierName;
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    private String name;
    private int quantity;
    private double price;
    private String supplierName;
    private String supplierPhoneNumber;
}
