package com.example.minshift;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final int[][] winNumbers = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private final int[][] initButtons = {{9, 3, 2}, {1, 7, 4}, {5, 6, 8}};
    private final int[][] numbers = {{9, 3, 2}, {1, 7, 4}, {5, 6, 8}};
    private final Button[][] buttons = new Button[3][3];
    private TextView shiftCounterView;
    private TextView topPlayerView;
    private TextView playerName;
    private int shiftCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shiftCounterView = (TextView) findViewById(R.id.shift_counter);
        playerName = (TextView) findViewById(R.id.player_name);
        topPlayerView = (TextView) findViewById(R.id.top_player);
        topPlayerView.setText(getHighScoreName() + " - " + getHighScore());

        showPlayerNameDialog();
        initButtons();
    }


    private void initButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = ((Button) findViewById(resID));
                buttons[i][j].setText(String.valueOf(initButtons[i][j]));
            }
        }
    }

    public void leftShift(View v) {
        shiftCounter++;
        shiftCounterView.setText(String.valueOf(shiftCounter));

        int row = Integer.parseInt(v.getTag().toString());

        int first = numbers[row][0];

        for (int i = 0; i < 2; i++)
            numbers[row][i] = numbers[row][i + 1];

        numbers[row][2] = first;

        updateButtons();
        checkWin();
    }

    public void rightShift(View v) {
        shiftCounter++;
        shiftCounterView.setText(String.valueOf(shiftCounter));

        int row = Integer.parseInt(v.getTag().toString());

        int last = numbers[row][2];

        for (int i = 2; i > 0; i--)
            numbers[row][i] = numbers[row][i - 1];

        numbers[row][0] = last;

        updateButtons();
        checkWin();
    }

    public void upShift(View v) {
        shiftCounter++;
        shiftCounterView.setText(String.valueOf(shiftCounter));

        int col = Integer.parseInt(v.getTag().toString());

        int first = numbers[0][col];

        for (int i = 0; i < 2; i++)
            numbers[i][col] = numbers[i + 1][col];

        numbers[2][col] = first;

        updateButtons();
        checkWin();
    }

    public void downShift(View v) {
        shiftCounter++;
        shiftCounterView.setText(String.valueOf(shiftCounter));

        int col = Integer.parseInt(v.getTag().toString());

        int last = numbers[2][col];

        for (int i = 2; i > 0; i--)
            numbers[i][col] = numbers[i - 1][col];

        numbers[0][col] = last;

        updateButtons();
        checkWin();
    }

    private void updateButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(numbers[i][j]));
            }
        }
    }

    private void checkWin() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (numbers[i][j] != winNumbers[i][j]) {
                    return;
                }
            }
        }

        showWinDialog();
        checkNewHighScore(shiftCounter, playerName.getText().toString());
    }

    private void showWinDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Complimenti!");
        builder.setMessage("Hai vinto!");

        builder.setPositiveButton("OK", (dialog, which) -> {
            resetGame(null);
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void resetGame(View v) {
        shiftCounter = 0;
        shiftCounterView.setText(String.valueOf(shiftCounter));
        initButtons();
    }

    private void showPlayerNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Inserisci il tuo nome");

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_player_name, null);
        builder.setView(dialogView);

        final EditText playerNameInput = dialogView.findViewById(R.id.player_name_input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String inputPlayerName = playerNameInput.getText().toString();
            playerName.setText(inputPlayerName);
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void saveHighScore(String playerName, int score) {
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("HighScoreName", playerName);
        editor.putInt("HighScore", score);
        editor.apply();
    }

    private String getHighScoreName() {
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        return prefs.getString("HighScoreName", "Nessun Giocatore");
    }

    private int getHighScore() {
        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        return prefs.getInt("HighScore", 0);
    }

    public void checkNewHighScore(int newScore, String playerName) {
        int highScore = getHighScore();
        if (newScore < highScore) {
            saveHighScore(playerName, newScore);
            topPlayerView.setText(playerName + " - " + newScore);
        }
    }
}