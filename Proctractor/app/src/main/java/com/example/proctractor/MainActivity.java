package com.example.proctractor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private ImageView img;
    private TextView txt_X;
    private TextView txt_Y;
    private TextView txt_Z;
    private SensorEventListener sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_X = findViewById(R.id.txtView_X);
        txt_Y = findViewById(R.id.txtView_Y);
        txt_Z = findViewById(R.id.txtView_Z);
        img = findViewById(R.id.imgView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager != null)
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);


        sv = new SensorEventListener ()
        {
            @Override
            public void onSensorChanged(SensorEvent event)
            {
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
                float[] rem = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        rem);
                float orient[] = new float[3];
                SensorManager.getOrientation(rem,orient);
                for (int i =0; i<3; i++) orient[i]=(float) (Math.toDegrees(orient[i]));

                txt_Y.setText(String.valueOf((int)orient[0]));
                txt_X.setText(String.valueOf((int)orient[1]));
                txt_Z.setText(String.valueOf((int)orient[2]));

                img.setRotationX(orient[1]);
                img.setRotationY(orient[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {}
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sv, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sv);
    }
}