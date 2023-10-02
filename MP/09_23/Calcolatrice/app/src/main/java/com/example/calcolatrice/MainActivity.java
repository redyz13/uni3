package com.example.calcolatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView TVdisplay1, TVdisplay2;
    private DecimalFormat decimalFormat;
    private boolean equalJustPressed, operatorPresent, dotPresent;
    private char op;
    private double mem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TVdisplay1 = findViewById(R.id.display1);
        TVdisplay2 = findViewById(R.id.display2);

        decimalFormat = new DecimalFormat("0.#");
    }

    public void buttonPressed(View view) {
        Button buttonPressed = (Button) view;
        String currentText = TVdisplay1.getText().toString();
        TVdisplay1.setText(currentText.concat(buttonPressed.getText().toString()));

        // Cancella il risultato se si digita un numero dopo aver premuto uguale
        if (equalJustPressed)
            TVdisplay2.setText("");
    }

    public void operatorPressed(View view) {
        Button operatorPressed = (Button) view;

        if (!operatorPresent) {
            this.op = operatorPressed.getText().toString().charAt(0); // Salva l'operatore
            String currentText = TVdisplay1.getText().toString();
            TVdisplay1.setText(currentText.concat(operatorPressed.getText().toString()));
            operatorPresent = true;
            // Si può di nuovo usare il punto nel secondo operatore
            dotPresent = false;
        }
    }

    public void headerButtonPressed(View view) {
        Button headerButtonPressed = (Button) view;

        switch (headerButtonPressed.getText().toString()) {
            case "C": clearScreen(); break;
            case "MC": memoryClear(); break;
            case "MS": memorySet(); break;
            case "MR": memoryRecall();
        }
    }

    public void equalPressed(View view) {
        // Se non è presente un operatore dai come risultato il numero stesso
        if (!operatorPresent) {
            TVdisplay2.setText(TVdisplay1.getText());
            return;
        }

        String display = TVdisplay1.getText().toString();

        int indexOfOperator = display.indexOf(op);
        String leftExpr = display.substring(0, indexOfOperator);
        String rightExpr = display.substring(indexOfOperator + 1);

        // Se gli operandi non sono correttamente definiti non effettuare operazioni
        if (leftExpr.equals("") || rightExpr.equals(""))
            return;

        double x = Double.parseDouble(leftExpr);
        double y = Double.parseDouble(rightExpr);
        double res;

        System.out.println(leftExpr);
        System.out.println(rightExpr);

        switch (this.op) {
            case '/': res = x / y; break;
            case '*': res = x * y; break;
            case '-': res = x - y; break;
            case '+': res = x + y; break;
            default: res = 0;
        }

        TVdisplay2.setText(decimalFormat.format(res));
        equalJustPressed = true;
    }

    public void dotPressed(View view) {
        Button dotPressed = (Button) view;

        if (!dotPresent) {
            String currentText = TVdisplay1.getText().toString();
            TVdisplay1.setText(currentText.concat(dotPressed.getText().toString()));
            dotPresent = true;
        }
    }

    private void clearScreen() {
        TVdisplay1.setText("");
        TVdisplay2.setText("");
        operatorPresent = false;
        dotPresent = false;
    }

    private void memoryClear() {
        this.mem = 0;
    }

    private void memorySet() {
        String displayText = TVdisplay2.getText().toString();

        if (!displayText.equals(""))
            this.mem = Double.parseDouble(displayText);
    }

    private void memoryRecall() {
        if (mem == 0)
            return;

        // Se non è già presente un punto e il numero è intero
        if (!dotPresent || this.mem % 1 == 0) {
            String currentText = TVdisplay1.getText().toString();
            TVdisplay1.setText(currentText.concat(decimalFormat.format(this.mem)));

            // Se il numero salvato non è intero vuol dire che un punto è stato inserito
            if (!(this.mem % 1 == 0))
                dotPresent = true;
        }
    }

}