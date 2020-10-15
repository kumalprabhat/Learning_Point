package com.example.learningpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Sql_Quiz extends AppCompatActivity {

    TextView textView, timer;
    Button nextbutton, submitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    private static final long COUNTDOWN_IN_MILLIS=300000;

    private CountDownTimer countDownTimer;
    private long TimeLftInMillis;

    String questions[] = {
            "Q1. What is the full form of SQL?",
            "Q2. Which of the following command used to manipulate Oracle Database Structures, including tables?",
            "Q3. Which operator performs pattern matching?",
            "Q4. What operator teste column for the absence of data?",
            "Q5. In SQL, Which command is used to change table's storage characteristics?",
            "Q6. In SQL, Which f the following is not Data Definition command?",
            "Q7. In SQL, Which command is used to SELECT only one copy of each set of duplicate row?",
            "Q8. A command that lets you change one or more field is?",
            "Q9. Which of the SQL statements is Correct?",
            "Q10. Which SQL keyword is used to retrieve only unique value?"
    };
    String answers[] = {"Structured Query Language","Data Definition Language(DDL)","LIKE operator","IS NULL operator","ALTER TABLE","UPDATE","SELECT DISTINCT","Modify","SELECT Username, Password FROM Users","DISTINCT"};
    String opt[] = {
            "Structured Query Language","Structured Query List","Simple Query Language","None of these",
            "Data Definition Language(DDL)","Data Manipulation Language(DML)","Both of above","None",
            "BETWEEN operator","LIKE operator","EXISTS operator","None of these",
            "EXISTS operator","NOT operator","IS NULL operator","None of these",
            "ALTER TABLE","MODIFY TABLE","CHANGE TABLE","All of the above",
            "RENAME","REVOKE","GRANT","UPDATE",
            "SELECT DISTINCT","SELECT UNIQUE","SELECT DIFFERENT","All of the above",
            "Insert","Modify","Look-up","All of the above",
            "SELECT Username AND Password FROM Users","SELECT Username, Password FROM Users","SELECT Username, Password WHERE Username = 'user1","None of these",
            "DISTINCTIVE","UNIQUE","DISTINCT","DIFFERENT"
    };

    int flag=0;
    public static int marks=0,correct=0,wrong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql__quiz);

        nextbutton=(Button)findViewById(R.id.button3);
        submitbutton=(Button)findViewById(R.id.buttonquit);
        textView=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        timer=findViewById(R.id.timer);
        textView.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);

        TimeLftInMillis=COUNTDOWN_IN_MILLIS;
        startCountDown();

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
                if(ansText.equals(answers[flag])) {
                    correct++;
                }
                else {
                    wrong++;
                }

                flag++;

                if(flag<questions.length)
                {
                    textView.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),Sql_Result.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Sql_Result.class);
                startActivity(intent);
            }
        });
    }
    private void startCountDown(){
        countDownTimer=new CountDownTimer(TimeLftInMillis,1000) {
            @Override
            public void onTick(long millisUntillFinshed) {
                TimeLftInMillis = millisUntillFinshed;
                UpdateCountDownText();
            }
            @Override
            public void onFinish() {
                TimeLftInMillis=0;
                Intent i=new Intent(getApplicationContext(),Result.class);
                startActivity(i);
                //UpdateCountDownText();
            }
        }.start();
    }
    private void UpdateCountDownText(){
        int minutes=(int)(TimeLftInMillis/1000)/60;
        int seconds=(int)(TimeLftInMillis/1000)%60;
        String TimeFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        timer.setText(TimeFormatted);
        if (TimeLftInMillis<60000){
            timer.setTextColor(Color.RED);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}