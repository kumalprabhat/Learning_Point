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

public class C_Quiz extends AppCompatActivity {
    TextView textView, timer;
    Button nextbutton, submitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    private static final long COUNTDOWN_IN_MILLIS=300000;

    private CountDownTimer countDownTimer;
    private long TimeLftInMillis;

    String questions[] = {
            "Q1. Who is the father of C language?",
            "Q2. C language developed at___________?",
            "Q3. C programs are converted in machine language with the help of______?",
            "Q4. C was primarily developed as",
            "Q5. Standard ANSI C recognize_______number of keywords.",
            "Q6. Which of the following is not reserved keyword in C?",
            "Q7. C variable can not start with?",
            "Q8. Which of the following is not valid identifier?",
            "Q9 If integer needs two bytes of storage, the maximum value of unsigned integer is",
            "Q10. Which is the only function all C program must contain?",
            "Q11. Which of the following is not correct variable type?",
            "Q12. What is the difference between declaration and definition of a variable?",
            "Q13. Bitwise operators can operate upon?",
            "Q14. Which is the right way to declare constant in C?",
            "Q15. Which operators are known as Ternary Operator?",
            "Q16. In switch statement, each case instance value must be _______?",
            "Q17. What is the work of break keyword?",
            "Q18. A binary tree with 27 nodes has _______ null branches.",
            "Q19. Which one of the following is not a linear data structure?",
            "Q20. Recursive functions are executed in a?",
            "Q21. The statement printf(\"%c\", 100); will print?",
            "Q22. The _______ memory allocation function modifies the previous allocated space.",
            "Q23. The \"C\" language is",
            "Q24. The Default Parameter Passing Mechanism is called as",
            "Q25. Which is the correct syntax to declare constant pointer?"
    };
    String answers[] = {"Dennis Ritchie","AT & T's Bell Laboratories of USA in 1972","A Compiler","System programming language","32","main","Both of the above","1examveda","65,535","main()","real","A declaration occurs once, but a definition may occur many times.","ints and chars","B & C Both","?, :","Constant","Exit from loop or switch statement","None of the above","Binary Tree","Last In First Out Order","prints ASCII equivalent of 100","realloc","Context free language","call by Value","A and C both"};
    String opt[] = {
            "James A. Gosling","Dennis Ritchie","Bjarne Stroustrup","Dr. E.F. Codd",
            "AT & T's Bell Laboratories of USA in 1972","AT & T's Bell Laboratories of USA in 1970","Sun Microsystems in 1973","Cambridge University in 1972",
            "An Editor","A Compiler","An Operating System","None of These",
            "System programming language","General purpose language","Data processing language","None of the above.",
            "30","32","34","40",
            "auto","case","main","default",
            "A number","Special symbol other than underscore","Both of the above","An Alphabet",
            "_examveda","1examveda","exam_veda","examveda1",
            "65,535","65,556","32,767","32768",
            "start()","main()","printf()","getch()",
            "float","real","int","double",
            "Both can occur multiple times, but a declaration must occur first.","A definition occurs once, but a declaration may occur many times.","A declaration occurs once, but a definition may occur many times.","There is no difference between them.",
            "double and chars","floats and doubles","ints and floats","ints and chars",
            "int constant var =10;","int const var = 10;","const int var = 10;","B & C Both",
            "::, ?","?, :","?, ;;","None of the above",
            "Constant","Variable","Special Symbol","None of the above",
            "Halt execution of program","Restart execution of program","Exit from loop or switch statement","None of the above",
            "54","27","26","None of the above",
            "Array","Binary Tree","Queue","Stack",
            "First In First Out Order","Load Balancing","Parallel Fashion","Last In First Out Order",
            "prints 100","print garbage","prints ASCII equivalent of 100","None of the above",
            "calloc","free","malloc","realloc",
            "Context free language","Context sensitive language","Regular language","None of the above",
            "call by Value","call by Reference","call by Address","Call by Name",
            "int *const constPtr;","*int constant constPtr;","const int *constPtr;","A and C both"
    };

    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__quiz);
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
                //Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
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
                    Intent in = new Intent(getApplicationContext(), Result.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Result.class);
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