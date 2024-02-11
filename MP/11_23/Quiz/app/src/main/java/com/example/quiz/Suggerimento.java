package com.example.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Suggerimento extends Activity {
    TextView textViewRisposta;
    private boolean risposta;
    private boolean rispostaMostrata = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suggerimento);

        TextView textViewQuesito = findViewById(R.id.testoQuesito);
        textViewRisposta = findViewById(R.id.testoRisposta);

        Intent i = getIntent();
        String quesito = i.getStringExtra("TESTO_QUESITO");
        risposta = i.getBooleanExtra("RISPOSTA_QUESITO", risposta);

        textViewQuesito.setText(quesito);
        setReturnIntent();
    }

    private void setReturnIntent() {
        Intent data = new Intent();
        data.putExtra("RISPOSTA_MOSTRATA", rispostaMostrata);
        setResult(RESULT_OK, data);
    }

    public void onClickMostra(View v) {
        textViewRisposta.setText(risposta ? "Vero" : "Falso");
        rispostaMostrata = true;
        setReturnIntent();
    }

    public void onClickTorna(View v) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Risposta mostrata", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}
