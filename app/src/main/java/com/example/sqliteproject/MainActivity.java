package com.example.sqliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            // Open or create a database named "Musicians" in private mode
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);

            // Create a table named "musicians" if it does not already exist, with columns for id, name, and age
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INTEGER)");

            // Insert data into the musicians table
            // database.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars', 60)");
            // database.execSQL("INSERT INTO musicians (name, age) VALUES ('James', 50)");
            // database.execSQL("INSERT INTO musicians (name, age) VALUES ('Kirk', 30)");

            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'");
            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3");

            //database.execSQL("DELETE FROM musicians WHERE id = 2 ");


            // Execute a raw query to select all records from the musicians table
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians /*WHERE name = 'James'*/", null); // where command does filtering
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null);
            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);

            // Get the index of the columns "name", "age", and "id"
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIX = cursor.getColumnIndex("id");

            // Loop through the result set and print out the name, age, and id of each record
            while (cursor.moveToNext()) {
                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("ID : " + cursor.getInt(idIX));
            }

            // Close the cursor to release its resources
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}