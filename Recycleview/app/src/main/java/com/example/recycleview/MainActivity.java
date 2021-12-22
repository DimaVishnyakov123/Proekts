package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,movies);
        recyclerView.setAdapter(adapter);

    }

    private void setData()
    {
        movies.add(new Model(R.drawable.ic_launcher_background,"Оно"));
        movies.add(new Model(R.drawable.ic_launcher_background,"Главный герой"));
        movies.add(new Model(R.drawable.ic_launcher_background,"Главный герой"));
        movies.add(new Model(R.drawable.ic_launcher_background,"Главный герой"));
        movies.add(new Model(R.drawable.ic_launcher_background,"Главный герой"));
        movies.add(new Model(R.drawable.ic_launcher_background,"Главный герой"));
        movies.add(new Model(R.drawable.ic_launcher_background,"Главный герой"));
    }
}