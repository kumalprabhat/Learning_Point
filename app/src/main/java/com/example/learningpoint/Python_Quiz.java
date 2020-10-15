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

public class Python_Quiz extends AppCompatActivity {

    TextView textView,timer;
    Button nextbutton, submitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    private static final long COUNTDOWN_IN_MILLIS=300000;

    private CountDownTimer countDownTimer;
    private long TimeLftInMillis;

    String questions[] = {
            "Q1. Is Python case sensitive when dealing with identifiers?",
            "Q2. What is the maximum possible length of",
            "Q3. Which of the following is invalid?",
            "Q4. Which of the following is an invalid variable?",
            "Q5. Which of the following is not a keyword?",
            "Q6. All keywords in Python are in _________",
            "Q7. Which of the following is an invalid statement?",
            "Q8. Which of the following cannot be a variable?",
            "Q9. What is the output of print 0.1 + 0.2 == 0.3?",
            "Q10. Which of the following is not a complex number?",
            "Q11. What is the type of inf?",
            "12. What is the result of cmp(3, 1)?",
            "Q13. Which of the following is incorrect?",
            "Q14. What is the result of round(0.5) – round(-0.5)?",
            "Q15. Which of the following is a Python tuple?"
    };
    String answers[] = {"Yes","none of the mentioned","none of the mentioned","1st_string","eval","None of the mentioned","a b c = 1000 2000 3000","in","False","k = 2 + 3l","Float","1","float(’12+34′)","2.0","(1, 2, 3)"};
    String opt[] = {
            "Yes","No","machine dependent","none of the mentioned",
            " 31 characters","63 characters","79 characters","none of the mentioned",
            "_a = 1","__a = 1","__str__ = 1","none of the mentioned",
            " my_string_1","1st_string","foo","_",
            "eval","assert","nonlocal","pass",
            "lower case","UPPER CASE","Capitalized","None of the mentioned",
            "abc = 1,000,000","a b c = 1000 2000 3000","a,b,c = 1000, 2000, 3000","a_b_c = 1,000,000",
            "__init__","in","it","on",
            "True","False","Machine dependent","Error",
            "k = 2 + 3j","k = complex(2, 3)","k = 2 + 3l","k = 2 + 3J",
            "Boolean","Integer","Float","Complex",
            "1","0","True","False",
            "float(‘inf’)","float(‘nan’)","float(’56’+’78’)","float(’12+34′)",
            "1.0","2.0","0.0","None of the mentioned",
            "[1, 2, 3]","(1, 2, 3)","{1, 2, 3}","{}"
    };

    int flag=0;
    public static int marks=0,correct=0,wrong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python__quiz);

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
                    Intent in = new Intent(getApplicationContext(),Python_Result.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Python_Result.class);
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