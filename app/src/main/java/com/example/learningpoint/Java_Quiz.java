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

public class Java_Quiz extends AppCompatActivity {

    TextView textView,timer;
    Button nextbutton, submitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    private static final long COUNTDOWN_IN_MILLIS=300000;

    private CountDownTimer countDownTimer;
    private long TimeLftInMillis;

    String questions[] = {
            "Q1. The Java interpreter is used for the execution of the source code.",
            "Q2. On successful compilation a file with the class extension is created.",
            "Q3. The Java source code can be created in a Notepad editor.",
            "Q4. The Java Program is enclosed in a class definition",
            "Q5. Strings are instances of the class String.",
            "Q6. Which of the following declare an array of string objects?",
            "Q7. What is the value of a[3] as the result of the following array declaration?",
            "Q8. Which of the following are primitive types?",
            "Q9. What is the value of 111 % 13?",
            "Q10. The do-while loop repeats a set of code at-least once before the condition is tested.",
            "Q11. Which of the following is not a wrapper class?",
            "Q12. Which of the following methods are methods of the String class?",
            "Q13. Which of the following methods cause the String object referenced by s to be changed?",
            "Q14. Which of the following are legal operations?",
            "Q15. Which of the following may contain a menu bar?"
    };
    String answers[] = {"True","True","True","True","True","All of the Above","4","Byte","7","True","String","replace()","A & B","s3=s1 + s2;","frame"};
    String opt[] = {
            "True","False",
            "True","False",
            "True","False",
            "True","False",
            "True","False",
            "String[ ] s;","String [ ]s:","String s[ ]:","All of the Above",
            "1","2","3","4",
            "Byte","String","Integer","Float",
            "3","5","7","9",
            "True","False",
            "String","Boolean","Integer","Character",
            "delete()","append()","reverse()","replace()",
            "s.concat( )","s.toUpperCase( )","s.replace( )","A & B",
            "s3=s1 + s2;","s3=s1 - s2;","s3=s1 & s2","s3=s1 && s2",
            "panel","frame","menu bar","menu"
    };

    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java__quiz);

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
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
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
                    Intent in = new Intent(getApplicationContext(),Java_Result.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Java_Result.class);
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