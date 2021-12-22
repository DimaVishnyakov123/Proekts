package com.example.contaks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static final int REQUEST_CODE_READ_FILES = 1;
    private static boolean READ_CONTACTS_GRANTED = false;
    private static boolean READ_FILES_GRANTED = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_FILES_GRANTED = true;
        }
        else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_FILES);

        }
        if (READ_CONTACTS_GRANTED){
            getFiles();
        }
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_READ_FILES) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                READ_FILES_GRANTED = true;
            }
        }
        if (READ_FILES_GRANTED) {
            getFiles();
        }
        else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show();
        }
    }

    private void getFiles(){
        ArrayList<String> contacts = new ArrayList<String>();

        File dir = new File("/storage/emulated/0/Android/");
        File[] files = dir.listFiles();
        TextView abs = findViewById(R.id.text);
        abs.setText("Путь к файлу: " + "/storage/emulated/0/Android");
        for (int i = 0; i < files.length; i++) {
            contacts.add(files[i].getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        ListView contactList = findViewById(R.id.listview);
        contactList.setAdapter(adapter);


    }



}