package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private TextView questionNumber;
    private TextView questionText;
    private TextView correctAnswers;
    private TextView wrongAnswers;
    private TextView totalAnswers;

    private int currentQuestion = 0;
    private int correct = 0;
    private int wrong = 0;
    private int total = 0;

    private final Quesito[] questions = new Quesito[]{
            new Quesito("Il risultato di 1 + 1 è 2", true),
            new Quesito("Il risultato di 1 + 1 è 3", false),
            new Quesito("Il risultato di 2 + 2 è 4", true),
            new Quesito("Il risultato di 2 + 2 è 5", false),
            new Quesito("Il risultato di 3 * 3 è 9", true),
            new Quesito("Il risultato di 3 * 4 è 9", false),
    };

    private final int QUESTIONS = questions.length;
    private final boolean[] seenAdvice = new boolean[QUESTIONS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionNumber = findViewById(R.id.questionNumber);
        questionText = findViewById(R.id.questionText);
        correctAnswers = findViewById(R.id.correctAnswers);
        wrongAnswers = findViewById(R.id.wrongAnswers);
        totalAnswers = findViewById(R.id.totalAnswers);

        updateAnswer();

        Arrays.fill(seenAdvice, false);
    }

    @SuppressLint("SetTextI18n")
    private void updateAnswer() {
        questionNumber.setText(String.valueOf(currentQuestion + 1));
        questionText.setText(questions[currentQuestion].getTesto());
        correctAnswers.setText("Risposte corrette: " + correct);
        wrongAnswers.setText("Risposte sbagliate: " + wrong);
        totalAnswers.setText("Risposte totali: " + total);
    }

    public void onClickAnotherAnswer(View v) {
        Button b = (Button) v;

        if (b.getId() == R.id.nextButton)
            currentQuestion++;
        else if (b.getId() == R.id.backButton)
            currentQuestion--;

        currentQuestion = currentQuestion % QUESTIONS;
        updateAnswer();
    }

    public void onClickAnswer(View v) {
        Button b = (Button) v;
        Quesito q = questions[currentQuestion];
        boolean correctAnswer = q.getRisposta();
        boolean answer;
        String response;
        total++;

        answer = b.getId() == R.id.trueButton;
        response = (answer == correctAnswer) ? "Corretto!" : "Sbagliato!";

        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();

        if (!q.hasBeenCounted()) {
            if (answer == correctAnswer) {
                if (!seenAdvice[currentQuestion])
                    correct++;
            } else {
                wrong++;
            }
        }
        q.setCounted(true);
        currentQuestion++;
        currentQuestion = currentQuestion % QUESTIONS;
        updateAnswer();
    }

    public void onClickAdvice(View v) {
        Intent i = new Intent(this, Suggerimento.class);
        i.putExtra("TESTO_QUESITO", questions[currentQuestion].getTesto());
        i.putExtra("RISPOSTA_QUESITO", questions[currentQuestion].getRisposta());
        startActivityForResult(i, 666);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 666) return;
        if (resultCode != RESULT_OK) return;
        if (data == null) return;

        seenAdvice[currentQuestion] = data.getBooleanExtra("RISPOSTA_MOSTRATA", false);
        Toast.makeText(this, "Suggerimento mostrato!", Toast.LENGTH_SHORT).show();
    }
}