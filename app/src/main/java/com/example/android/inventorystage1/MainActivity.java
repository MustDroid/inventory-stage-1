package com.example.android.inventorystage1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventorystage1.data.Book;
import com.example.android.inventorystage1.data.BookDbHelper;

public class MainActivity extends AppCompatActivity {

    private BookDbHelper mDbHelper;

    private TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new BookDbHelper(this);
        txtContent = (TextView)findViewById(R.id.txtContent);
        readDataFromDatabase();
    }

    private void readDataFromDatabase() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        String[] projection = {
                Book.Contract._ID,
                Book.Contract.COLUMN_NAME,
                Book.Contract.COLUMN_QUANTITY,
                Book.Contract.COLUMN_PRICE,
                Book.Contract.COLUMN_SUPPLIER_NAME,
                Book.Contract.COLUMN_SUPPLIER_PHONE_NUMBER
        };


        Cursor cursor = db.query(
                Book.Contract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            txtContent.setText("The books table contains " + cursor.getCount() + " books.\n\n");
            txtContent.append(Book.Contract._ID + " - " +
                    Book.Contract.COLUMN_NAME + " - " +
                    Book.Contract.COLUMN_QUANTITY + " - " +
                    Book.Contract.COLUMN_PRICE + " - " +
                    Book.Contract.COLUMN_SUPPLIER_NAME +
                    Book.Contract.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");


            int idColumnIndex = cursor.getColumnIndex(Book.Contract._ID);
            int nameColumnIndex = cursor.getColumnIndex(Book.Contract.COLUMN_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(Book.Contract.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(Book.Contract.COLUMN_PRICE);
            int supplierNameColumnIndex = cursor.getColumnIndex(Book.Contract.COLUMN_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(Book.Contract.COLUMN_SUPPLIER_PHONE_NUMBER);


            while (cursor.moveToNext()) {

                int ID = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String quantity = cursor.getString(quantityColumnIndex);
                String price = cursor.getString(priceColumnIndex);
                String supplierName = cursor.getString(supplierNameColumnIndex);
                String supplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);


                txtContent.append(("\n" + ID + " - " +
                        name + " - " +
                        quantity + " - " +
                        price + " - " +
                        supplierName + " - " +
                        supplierPhoneNumber));
            }
        } finally {

            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_data:
                Intent intent = new Intent(this, EditorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

                return true;
            case R.id.action_delete_all_entries:
                deleteAll();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAll() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + Book.Contract.TABLE_NAME);
        Toast.makeText(this, "All data have been deleted from database!", Toast.LENGTH_SHORT).show();
        readDataFromDatabase();
    }
}
