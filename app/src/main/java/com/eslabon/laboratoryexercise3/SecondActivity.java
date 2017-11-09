package com.eslabon.laboratoryexercise3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;

public class SecondActivity  extends AppCompatActivity {

    EditText showFileName;
    Button loadSharedBtn, loadIntStoreBtn, loadIntCache, loadExtCache, loadExtStor, loadExtPub;
    TextView textDisplay;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        showFileName = (EditText) findViewById(R.id.fnInput);

        loadSharedBtn = (Button) findViewById(R.id.loadSharedBtn);
        loadIntStoreBtn = (Button) findViewById(R.id.loadIntStorBtn);
        loadIntCache = (Button) findViewById(R.id.loadIntCache);
        loadExtCache = (Button) findViewById(R.id.loadExtCache);
        loadExtStor = (Button) findViewById(R.id.loadExtStor);
        loadExtPub = (Button) findViewById(R.id.loadExtPub);

        textDisplay = (TextView) findViewById(R.id.textDisplay);

        preferences = getSharedPreferences("sharedText", MODE_PRIVATE);
    }

    public void loadSharedPreferences(View view) {
        String sharedPrefMessage = preferences.getString(showFileName.getText().toString(), "");
        textDisplay.setText(sharedPrefMessage);
    }

    public void loadInternalStorage(View view) {
        StringBuilder buffer = new StringBuilder();
        int read;
        try {
            fis = openFileInput(showFileName.getText().toString());
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textDisplay.setText(buffer.toString());
    }

    public void loadIntCache(View view) {
        StringBuilder buffer = new StringBuilder();
        int read = 0 ;
        try {
            FileInputStream fis = new FileInputStream(new File(getCacheDir(), showFileName.getText().toString()));
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textDisplay.setText(buffer.toString());
    }

    public void loadExtCache(View view) {
        StringBuilder buffer = new StringBuilder();
        int read = 0;
        try {
            FileInputStream fis = new FileInputStream(new File(getExternalCacheDir(), showFileName.getText().toString()));
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textDisplay.setText(buffer.toString());
    }

    public void loadExtStor(View view) {
        StringBuilder buffer = new StringBuilder();
        int read = 0;
        try {
            fis = new FileInputStream(new File(getExternalFilesDir("temp"), showFileName.getText().toString()));
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textDisplay.setText(buffer.toString());
    }

    public void loadExtPub(View view) {
        StringBuilder buffer = new StringBuilder();
        int read = 0;
        try {
            fis =  new FileInputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), showFileName.getText().toString()));
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        textDisplay.setText(buffer.toString());
    }

    public void mainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}