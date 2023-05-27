package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout gameLayout;
    TextView sumTextView;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answers = new ArrayList<>();
    int correctAnsPos;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    int score = 0;
    int totalQuestions = 0;
    Button playAgainButton;
    public void playAgain(View View) {
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score = 0;
        totalQuestions = 0;
        newQuestion();
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));
        timerTextView.setText("30s");
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + 's');
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Times Up!");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();

    }
    public void chooseAnswer(View view) {
        resultTextView.setVisibility(View.VISIBLE);
        if(Integer.toString(correctAnsPos).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        }
        else {
            resultTextView.setText("Wrong");
        }
        totalQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));
        newQuestion();
    }

    public void newQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        correctAnsPos = rand.nextInt(4);

        answers.clear();

        for(int i = 0; i < 4; i++) {
            if(i == correctAnsPos) {
                answers.add(a + b);
            }
            else {
                int wrongAnswer;
                do {
                    wrongAnswer = rand.nextInt(41);
                } while(wrongAnswer == (a + b));
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}