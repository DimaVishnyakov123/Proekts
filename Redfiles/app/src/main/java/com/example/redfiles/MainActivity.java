package com.example.redfiles;

import androidx.activity.contextaware.ContextAware;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    public static  boolean READ_FILE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Получаем разрешения
        int hasReadContactPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        if(hasReadContactPermission == PackageManager.PERMISSION_GRANTED)
            READ_FILE = true;
        else{
            //Вызов диалогового окна для установки разрешения        }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_CONTACTS);
        }

        //Если разрешение дано, читаем/показываем контакты
        if(READ_FILE){
            getFile();
        }


}

    private void getFile() {
        ContentResolver contentResolver  = getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns.DATA},
                null, null, null);

        ArrayList<String> images = new ArrayList<>();

        if(cursor!=null){
            while (cursor.moveToNext()){
                //получаем каждый контакт
                @SuppressLint("Range")
                String contact = cursor.getString(
                        cursor.getColumnIndex(MediaStore.MediaColumns.DATA)
                );
                images.add(contact);
            }
            cursor.close();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, images);

        ListView contactList = findViewById(R.id.ListView);

        contactList.setAdapter(adapter);
        }
    }
