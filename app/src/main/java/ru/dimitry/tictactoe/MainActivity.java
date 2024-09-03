package ru.dimitry.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnStatus;
    private TextView textView;
    private String[][] field;
    private String[] playerSymbols;
    private String[] hintPlayer;
    private int numMovePlayer;

    private int gameStatus;

    public void init() {
        btn1 = findViewById(R.id.btn_1_id);
        btn2 = findViewById(R.id.btn_2_id);
        btn3 = findViewById(R.id.btn_3_id);
        btn4 = findViewById(R.id.btn_4_id);
        btn5 = findViewById(R.id.btn_5_id);
        btn6 = findViewById(R.id.btn_6_id);
        btn7 = findViewById(R.id.btn_7_id);
        btn8 = findViewById(R.id.btn_8_id);
        btn9 = findViewById(R.id.btn_9_id);
        numMovePlayer = 1;
        btnStatus = findViewById(R.id.main_button_id);
        textView = findViewById(R.id.text_view_play_ground_id);
        field = new String[3][3];
        hintPlayer = new String[]{"X player", "O player"};
        playerSymbols = new String[]{"X", "O"};
        gameStatus = 0;
        switchButton(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnStatus.setOnClickListener(view -> {
            switch (gameStatus) {
                case 0:
                    startGame();
                    break;
                case 1:
                    restartGame();
                    break;
            }

        });
        btn1.setOnClickListener(view -> {
            playerMove(btn1.getId(), new int[]{0, 0});
        });
        btn2.setOnClickListener(view -> {
            playerMove(btn2.getId(), new int[]{0, 1});

        });
        btn3.setOnClickListener(view -> {
            playerMove(btn3.getId(), new int[]{0, 2});

        });
        btn4.setOnClickListener(view -> {
            playerMove(btn4.getId(), new int[]{1, 0});

        });
        btn5.setOnClickListener(view -> {
            playerMove(btn5.getId(), new int[]{1, 1});

        });
        btn6.setOnClickListener(view -> {
            playerMove(btn6.getId(), new int[]{1, 2});

        });
        btn7.setOnClickListener(view -> {
            playerMove(btn7.getId(), new int[]{2, 0});

        });
        btn8.setOnClickListener(view -> {

            playerMove(btn8.getId(), new int[]{2, 1});

        });
        btn9.setOnClickListener(view -> {
            playerMove(btn9.getId(), new int[]{2, 2});
        });
    }

    private void switchButton(boolean status) {
        btn1.setEnabled(status);
        btn2.setEnabled(status);
        btn3.setEnabled(status);
        btn4.setEnabled(status);
        btn5.setEnabled(status);
        btn6.setEnabled(status);
        btn7.setEnabled(status);
        btn8.setEnabled(status);
        btn9.setEnabled(status);
    }

    private void startGame() {
        btnStatus.setText(R.string.text_for_button_stop);
        gameStatus = 1;
        textView.setText(hintPlayer[(numMovePlayer - 1) % 2]);
        switchButton(true);
    }

    private void playerMove(int buttonId, int[] indexField) {
        if (buttonIsClear(buttonId)) {
            Button button = findViewById(buttonId);
            button.setText(playerSymbols[(numMovePlayer - 1) % 2]);
            textView.setText(hintPlayer[(numMovePlayer) % 2]);
            field[indexField[0]][indexField[1]] = playerSymbols[numMovePlayer % 2];
            if (numMovePlayer > 8){
                draw();
            }
            else if (numMovePlayer > 4 && checkWinner(playerSymbols[numMovePlayer % 2])) {
                victory(playerSymbols[(numMovePlayer - 1) % 2]);
            } else {
                numMovePlayer++;
            }
        }
    }

    private void restartGame() {
        finish();
        startActivity(getIntent());
    }

    private boolean checkWinner(String checkSymbols) {
        for (int col = 0; col < 3; ++col) {
            if (checkSymbols.equals(field[col][0]) && checkSymbols.equals(field[col][1]) && checkSymbols.equals(field[col][2])) {
                return true;
            }
        }
        for (int row = 0; row < 3; ++row) {
            if (checkSymbols.equals(field[0][row]) && checkSymbols.equals(field[1][row]) && checkSymbols.equals(field[2][row])) {
                return true;
            }
        }
        if (checkSymbols.equals(field[0][0]) && checkSymbols.equals(field[1][1]) && checkSymbols.equals(field[2][2])) {
            return true;
        } else
            return checkSymbols.equals(field[0][2]) && checkSymbols.equals(field[1][1]) && checkSymbols.equals(field[2][0]);
    }

    private void victory(String winner) {
        switchButton(false);
        btnStatus.setText(R.string.text_for_text_view_restart);
        textView.setText(winner + " " + "wins");
    }

    private void draw(){
        switchButton(false);
        btnStatus.setText(R.string.text_for_text_view_restart);
        textView.setText(R.string.text_for_text_view_draw);
    }
    private boolean buttonIsClear(int buttonId) {
        return ((Button) findViewById(buttonId)).getText().equals("");
    }
}