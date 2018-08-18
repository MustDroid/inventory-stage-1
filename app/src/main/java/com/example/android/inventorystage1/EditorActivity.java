/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.inventorystage1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.inventorystage1.data.Book;
import com.example.android.inventorystage1.data.BookDbHelper;


public class EditorActivity extends AppCompatActivity {
    private BookDbHelper mDbHelper;

    private EditText editBookName;
    private EditText editQuantity;
    private EditText editPrice;
    private EditText editSupplierName;
    private EditText editSupplierPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mDbHelper = new BookDbHelper(this);

        editBookName = (EditText) findViewById(R.id.editName);
        editQuantity = (EditText) findViewById(R.id.editPrice);
        editPrice = (EditText) findViewById(R.id.editQuantity);
        editSupplierName = (EditText) findViewById(R.id.editSupplierName);
        editSupplierPhoneNumber = (EditText) findViewById(R.id.editSupplierPhoneNumber);
    }

    private boolean insertBook() {
        try {
            Book book = new Book(editBookName.getText().toString(),
                    Integer.parseInt(editQuantity.getText().toString()),
                    Double.parseDouble(editPrice.getText().toString()),
                    editSupplierName.getText().toString(),
                    editSupplierPhoneNumber.getText().toString()
            );

            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Book.Contract.COLUMN_NAME, book.getName());
            values.put(Book.Contract.COLUMN_QUANTITY, book.getQuantity());
            values.put(Book.Contract.COLUMN_PRICE, book.getPrice());
            values.put(Book.Contract.COLUMN_SUPPLIER_NAME, book.getSupplierName());
            values.put(Book.Contract.COLUMN_SUPPLIER_PHONE_NUMBER, book.getSupplierPhoneNumber());

            long newRowId = db.insert(Book.Contract.TABLE_NAME, null, values);

            if (newRowId == -1) {
                Toast.makeText(this, R.string.editor_saving_error, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_saving_success) + newRowId, Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        catch(NumberFormatException ex) {
            Toast.makeText(this, getString(R.string.editor_saving_invalid_number_format) + " " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        catch(Exception ex) {
            Toast.makeText(this, R.string.editor_saving_unknown_error, Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:

                if(insertBook()) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                return true;

            case R.id.action_delete:

                return true;

            case android.R.id.home:

                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}