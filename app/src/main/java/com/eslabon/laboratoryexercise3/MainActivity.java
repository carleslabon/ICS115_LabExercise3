package com.eslabon.laboratoryexercise3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etMessage, filename;
    Button sharedPrefBtn, intStorBtn, intCacheBtn, extCacheBtn, extStorBtn, extPubBtn;
    FileOutputStream fos;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = (EditText) findViewById(R.id.dataInput);
        filename = (EditText) findViewById(R.id.filenameInput);

        sharedPrefBtn = (Button) findViewById(R.id.sharedPrefBtn);
        intStorBtn = (Button) findViewById(R.id.intStorBtn);
        intCacheBtn = (Button) findViewById(R.id.intCacheBtn);
        extCacheBtn = (Button) findViewById(R.id.extCacheBtn);
        extStorBtn = (Button) findViewById(R.id.extStorBtn);
        extPubBtn = (Button) findViewById(R.id.extPubStorBtn);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void sharedPreferences(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(filename.getText().toString(), etMessage.getText().toString());
        editor.commit();
        Toast.makeText(this, "Successfully written to Shared Preferences.", Toast.LENGTH_SHORT).show();
    }

    public void internalStorage(View view) {
        String message = etMessage.getText().toString();
        try {
            fos = openFileOutput(filename.getText().toString(), Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully written to Internal Storage.", Toast.LENGTH_SHORT).show();
    }

    public void saveInternalCache(View view) {
        File folder = getCacheDir();
        File file = new File (folder, filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to Internal Cache.", Toast.LENGTH_LONG).show();
    }

    public void saveExternalCache(View view){
        File folder = getExternalCacheDir();
        File file = new File (folder, filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Cache.", Toast.LENGTH_LONG).show();
    }

    public void saveExternalStorage(View view) {
        File folder = getExternalFilesDir("Temp");
        File file = new File (folder, filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Storage.", Toast.LENGTH_LONG).show();
    }

    public void saveExternalPublic(View view) {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File (folder, filename.getText().toString());
        String message = etMessage.getText().toString();
        writeData(file, message);
        Toast.makeText(this, "Successfully written to External Public Storage.", Toast.LENGTH_LONG).show();
    }

    public void writeData(File file, String message){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void secondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}