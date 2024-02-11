package com.example.calcolatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView input, output, memoryView;
    private DecimalFormat decimalFormat;
    private boolean equalJustPressed, operatorPresent, dotPresent;
    private char op;
    private double mem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        memoryView = findViewById(R.id.memoryValue);

        decimalFormat = new DecimalFormat("0.###");
    }

    public void buttonPressed(View view) {
        Button buttonPressed = (Button) view;
        String currentText = input.getText().toString();
        input.setText(currentText.concat(buttonPressed.getText().toString()));

        // Cancella il risultato se si digita un numero dopo aver premuto uguale
        if (equalJustPressed)
            output.setText("");
    }

    public void operatorPressed(View view) {
        Button operatorPressed = (Button) view;

        if (!operatorPresent) {
            this.op = operatorPressed.getText().charAt(0); // Salva l'operatore
            String currentText = input.getText().toString();
            input.setText(currentText.concat(operatorPressed.getText().toString()));
            operatorPresent = true;
            // Si può di nuovo usare il punto nel secondo operatore
            dotPresent = false;
        }
    }

    @SuppressLint("SetTextI18n")
    public void equalPressed(View view) {
        // Se non è presente un operatore dai come risultato il numero stesso
        if (!operatorPresent) {
            output.setText(input.getText().toString());
            return;
        }

        String currentText = input.getText().toString();

        int indexOfOperator = currentText.indexOf(op);
        String leftExpression = currentText.substring(0, indexOfOperator);
        String rightExpression = currentText.substring(indexOfOperator + 1);

        // Se gli operandi non sono correttamente definiti non effettuare operazioni
        if (leftExpression.equals("") || rightExpression.equals("")) {
            return;
        }

        double x = Double.parseDouble(leftExpression);
        double y = Double.parseDouble(rightExpression);
        double res;

        switch (this.op) {
            case '/': res = x / y; break;
            case '*': res = x * y; break;
            case '-': res = x - y; break;
            case '+': res = x + y; break;
            default: res = 0;
        }

        output.setText(decimalFormat.format(res));
        equalJustPressed = true;
    }

    public void dotPressed(View view) {
        Button dotPressed = (Button) view;

        if (!dotPresent) {
            String currentText = input.getText().toString();
            input.setText(currentText.concat(dotPressed.getText().toString()));
            dotPresent = true;
        }
    }

    public void headerButtonPressed(View view) {
        Button headerButtonPressed = (Button) view;

        switch (headerButtonPressed.getText().toString()) {
            case "C": clearScreen(); break;
            case "MC": memoryClear(); break;
            case "MS": memorySet(); break;
            case "MR": memoryRead();
        }
    }

    private void clearScreen() {
        input.setText("");
        output.setText("");
        operatorPresent = false;
        dotPresent = false;
    }

    private void memoryClear() {
        this.mem = 0;
        memoryView.setText("");
    }

    private void memorySet() {
        String outputText = output.getText().toString();

        if (!outputText.equals("")) {
            this.mem = Double.parseDouble(outputText);
            memoryView.setText(decimalFormat.format(this.mem));
        }
    }

    private void memoryRead() {
        if (this.mem == 0)
            return;

        // Se non è già presente un punto o il numero è intero
        if (!dotPresent || this.mem % 1 == 0) {
            String currentText = input.getText().toString();
            input.setText(currentText.concat(decimalFormat.format(this.mem)));

            // Se il numero salvato non è intero vuol dire che un punto è stato inserito
            if (!(this.mem % 1 == 0))
                dotPresent = true;
        }
    }
}