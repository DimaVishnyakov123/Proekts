package com.example.listview;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lst;
    TextView txt;
    String[] device = {"Планшеты", "Телефоны", "Ноутбуки", "Компьютеры"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = findViewById(R.id.listView);
        txt = findViewById(R.id.text);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_multiple_choice, device
        );
        lst.setAdapter(adapter);
        String[] slovo = {"", "", "", ""};
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getApplicationContext(),
                // ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                for(int b = 0; b < 4; b++)
                {
                    if(lst.isItemChecked(b)) {
                        slovo[b] = device[b];
                    }
                    else
                    {
                        slovo[b] = "";
                    }
                }
                String fin = "";
                for(int b = 0; b < 4; b++)
                {
                    if(slovo[b] != "")
                    {
                        fin += "|" + slovo[b] + "|";
                    }
                }
                txt.setText(fin);
            }
        });
    }
}
