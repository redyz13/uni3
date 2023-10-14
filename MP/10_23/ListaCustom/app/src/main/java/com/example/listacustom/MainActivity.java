package com.example.listacustom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] array = { "Pasquale", "Maria", "Michele", "Antonella", "Vincenzo",
                "Teresa", "Roberto", "Rossella", "Antonio", "Luca", "Liliana",
                "Stefania", "Francesca", "Andrea", "Marco", "Elisa", "Anna", "Lorenzo" };

        ListView listView = findViewById(R.id.mylistview);

        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<>());

        listView.setAdapter(customAdapter);

        for (String i : array) {
            Contatto contatto = new Contatto(i, "111-2222-333", ResourcesCompat.
                    getDrawable(getResources(), R.drawable.icons8_contacts_500, null));
            customAdapter.add(contatto);
        }
    }

    public void onPictureClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Contatto contatto = customAdapter.getItem(position);
        assert contatto != null;
        Toast.makeText(getApplicationContext(),
                        "Click su foto posizione n." + position + ": " + contatto.getName(), Toast.LENGTH_LONG)
                .show();
    }

    public void onNameClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Contatto contatto = customAdapter.getItem(position);
        assert contatto != null;
        Toast.makeText(getApplicationContext(),
                        "Click su nome posizione n." + position + ": " + contatto.getName(), Toast.LENGTH_LONG)
                .show();
    }

    public void onTelClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Contatto contatto = customAdapter.getItem(position);
        assert contatto != null;
        Toast.makeText(getApplicationContext(),
                        "Click su telefono posizione n." + position + ": " + contatto.getName(), Toast.LENGTH_LONG)
                .show();
    }
}