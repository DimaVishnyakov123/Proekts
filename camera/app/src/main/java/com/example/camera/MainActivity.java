package com.example.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import static com.example.camera.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    private int CAMERA_CAPTURE;

    Button btn;
    Button btn2;
    ImageView img;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        btn = findViewById(R.id.btnTakePhoto);
        img = findViewById(R.id.img);
        btn2 = findViewById(R.id.btnTakeVideo);
        video = findViewById(R.id.video);
        btn.setOnClickListener(view ->{
            try {
                CAMERA_CAPTURE =1;
                //Используем стандартное системное намерение на использование камеры:
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Задаем возможность работать с полученными с камеры данными:
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            }
            catch (ActivityNotFoundException cant) {
                //Показываем сообщение об ошибке:
                String errorMessage = "Камера не поддерживается вашим устройством";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }

        });

        btn2.setOnClickListener(view ->{
            try {
                CAMERA_CAPTURE =2;
                //Используем стандартное системное намерение на использование видео:
                Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                //Задаем возможность работать с полученными данными:
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            }
            catch (ActivityNotFoundException cant) {
                //Показываем сообщение об ошибке:
                String errorMessage = "Камера не поддерживается вашим устройством";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(CAMERA_CAPTURE == 1) {

            Bundle extras = data.getExtras();
            Bitmap thumbnailBitmap = (Bitmap) extras.get("data");
            img.setImageBitmap(thumbnailBitmap);
        }
        else {
            Uri videoURI = data.getData();
            video.setVideoURI(videoURI);
            video.requestFocus();
            //автоматическое воспроизведение
            video.start();
        }


    }
}