package com.example.asynctask;

import static java.lang.Thread.sleep;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ProgressBar progressBar;
    private TextView counterView;
    private int index = 1;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        counterView = findViewById(R.id.counter);
    }

    public void counterButtonPressed(View view) {
        counter++;
        counterView.setText(String.valueOf(counter));
    }

    public void loadButtonPressed(View view) {
        int img_id = 0;
        switch (index) {
            case 1:
                img_id = R.drawable.img0;
                index = 2;
                break;
            case 2:
                img_id = R.drawable.img1;
                index = 3;
                break;
            case 3:
                img_id = R.drawable.img2;
                index = 1;
                break;
        }
        new LoadImageTask().execute(img_id);
    }

    class LoadImageTask extends AsyncTask<Integer, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer... img_ids) {
            Log.d("DEBUG", "index = " + img_ids[0]);
            Bitmap tmp = BitmapFactory.decodeResource(getResources(), img_ids[0]);

            for (int i = 1; i < 11; i++) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                publishProgress(i * 10);
            }

            return tmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            if (values[0] > 75)
                Toast.makeText(getApplicationContext(), "Quasi finito!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            progressBar.setProgress(0);
            imageView.setImageBitmap(result);
        }
    }
}