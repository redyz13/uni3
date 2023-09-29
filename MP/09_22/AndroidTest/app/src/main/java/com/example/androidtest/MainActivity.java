package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.paolo);
    }

    public void action(View view) {
        Random random = new Random();

        int R = random.nextInt(256);
        int G = random.nextInt(256);
        int B = random.nextInt(256);

        textView.setBackgroundColor(Color.rgb(R, G, B));
    }
}