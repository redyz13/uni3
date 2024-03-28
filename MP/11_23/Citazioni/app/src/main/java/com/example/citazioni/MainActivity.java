package com.example.citazioni;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Autori.AuthorSelectionListener {
    private FragmentManager fragmentManager;
    private final Autori authorsFragment = new Autori();
    private final Citazione quoteFragment = new Citazione();
    private int mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        // Dimensione display in pixel
        Point size = new Point();
        display.getSize(size);
        int screenw_px = size.x;
        int screenh_px = size.y;
        // Densità
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float screenDensity = metrics.density;
        // Dimensione display in dp
        int screenw_dp = (int) (screenw_px / screenDensity);
        int screenh_dp = (int) (screenh_px / screenDensity);
        // Dimensioni display in cm
        float screenw_cm = 2.54f * screenw_dp / 160;
        float screenh_cm = 2.54f * screenh_dp / 160;

        if (screenw_px < screenh_px) {
            mode = 1; // Portrait
            setContentView(R.layout.layout1);
        } else {
            mode = 2;
            setContentView(R.layout.layout2);
            FrameLayout fla = findViewById(R.id.author_fragment_container);
            fla.getLayoutParams().width = (int) (screenw_px * 0.3);
            FrameLayout flq = findViewById(R.id.quote_fragment_container);
            flq.getLayoutParams().width = (int) (screenw_px * 0.7);
        }

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (mode) {
            case 1:
                fragmentTransaction.replace(R.id.fragment_container, authorsFragment);
                break;
            case 2:
                fragmentTransaction.replace(R.id.author_fragment_container, authorsFragment);
                fragmentTransaction.replace(R.id.quote_fragment_container, quoteFragment);
                break;
        }
        // Non mettiamo la transazione nel backstack perché sostituisce lo screen iniziale
        // E quindi vogliamo uscire dall'applicazione se si preme il tasto indietro
        // ft.addToBackStack(null);
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }

    @Override
    public void onAuthorSelection(int index) {
        switch (mode) {
            case 1:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(authorsFragment);
                fragmentTransaction.add(R.id.fragment_container, quoteFragment);
                // addToBackStack per entrambi i cambiamenti che vengono
                // Così considerati come una sola transizione. Il pulsante back
                // Ripristinerà lo stato precedente i due cambiamenti
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                // Per essere sicuri che il frammento sia effettivamente
                // presente per la chiamata showIndex
                fragmentManager.executePendingTransactions();
                break;
            case 2:
        }
        quoteFragment.showIndex(index, mode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        quoteFragment.showIndex(-1, mode);
    }
}