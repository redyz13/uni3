package com.example.slotmachine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final TextView[] rotoriAttuali = new TextView[4];
    private final TextView[] rotoriPrecedenti = new TextView[4];
    private final TextView[] rotoriSuccessivi = new TextView[4];
    private TextView displayPunteggio;
    private Button playButton, resetButton;
    private int punteggio = 0;
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();

        playButton.setOnClickListener(v -> {
            int[] numeri = new int[4];
            for (int i = 0; i < 4; i++) {
                numeri[i] = random.nextInt(10);
                aggiornaRotori(i, numeri[i]);
            }
            calcolaPunteggio(numeri);
        });

        resetButton.setOnClickListener(v -> {
            punteggio = 0;
            aggiornaPunteggio();
        });

        if (savedInstanceState != null) {
            punteggio = savedInstanceState.getInt("PUNTEGGIO");
            aggiornaPunteggio();

            for (int i = 0; i < 4; i++) {
                int rotoreAttuale = savedInstanceState.getInt("ROTORE_ATTUALE" + (i + 1));
                aggiornaRotori(i, rotoreAttuale);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)  {
        super.onSaveInstanceState(outState);

        outState.putInt("PUNTEGGIO", punteggio);

        for (int i = 0; i < 4; i++)
            outState.putInt("ROTORE_ATTUALE" + (i + 1), Integer.parseInt(rotoriAttuali[i].getText().toString()));
    }

    private void calcolaPunteggio(int[] numeri) {
        int[] occorrenze = new int[10];

        for (int numero : numeri)
            occorrenze[numero]++;

        for (int k : occorrenze) {
            if (k == 2) {
                punteggio += 10;
            } else if (k == 3) {
                punteggio += 25;
            } else if (k == 4) {
                punteggio += 50;
            }
        }

        aggiornaPunteggio();
    }

    private void setViews() {
        for (int i = 0; i < 4; i++) {
            String rotoreAttualeId = "rotor" + (i + 1) + "_current";
            String rotorePrecedenteId = "rotor" + (i + 1) + "_prev";
            String rotoreSuccessivoId = "rotor" + (i + 1) + "_next";
            rotoriAttuali[i] = findViewById(getResources().getIdentifier(rotoreAttualeId, "id", getPackageName()));
            rotoriPrecedenti[i] = findViewById(getResources().getIdentifier(rotorePrecedenteId, "id", getPackageName()));
            rotoriSuccessivi[i] = findViewById(getResources().getIdentifier(rotoreSuccessivoId, "id", getPackageName()));
        }

        displayPunteggio = findViewById(R.id.scoreDisplay);
        playButton = findViewById(R.id.playButton);
        resetButton = findViewById(R.id.resetButton);
    }

    private void aggiornaRotori(int i, int number) {
        rotoriAttuali[i].setText(String.valueOf(number));
        rotoriPrecedenti[i].setText(String.valueOf(number == 0 ? 9 : number - 1));
        rotoriSuccessivi[i].setText(String.valueOf(number == 9 ? 0 : number + 1));
    }

    private void aggiornaPunteggio() {
        displayPunteggio.setText("Punteggio: " + punteggio);
    }
}
