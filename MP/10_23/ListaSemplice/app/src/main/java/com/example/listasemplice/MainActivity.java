package com.example.listasemplice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] array = { "Pasquale", "Maria", "Michele", "Antonella", "Vincenzo",
                "Teresa", "Roberto", "Rossella", "Antonio", "Luca", "Liliana",
                "Stefania", "Francesca", "Andrea", "Marco", "Elisa", "Anna", "Lorenzo" };

        listView = findViewById(R.id.mylistview);

        Log.d("DEBUG", "ListView create: listView = " + listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_element,
                R.id.textViewList, array);

        Log.d("DEBUG", "ArrayAdapter create: arrayAdapter = " + arrayAdapter);

        listView.setAdapter(arrayAdapter);

        Log.d("DEBUG", "Done!");

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String  str  = listView.getItemAtPosition(position).toString();
            // Show Toast
            Toast.makeText(getApplicationContext(),
                            "Click su posizione n." + position + ": " + str, Toast.LENGTH_LONG)
                    .show();
        });
    }
}
