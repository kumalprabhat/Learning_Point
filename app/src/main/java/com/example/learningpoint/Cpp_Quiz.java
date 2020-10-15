package com.example.learningpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Locale;

public class Cpp_Quiz extends AppCompatActivity {
    TextView textView,timer;
    Button nextbutton, submitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    private static final long COUNTDOWN_IN_MILLIS=300000;

    private CountDownTimer countDownTimer;
    private long TimeLftInMillis;

    String questions[] = {
            "Q1. 1. Which of the following concept of oops allows compiler to insert arguments in a function call if it is not specified?",
            "Q2. How many types of polymorphisms are supported by C++?",
            "Q3. Which of the following correctly describes overloading of functions?",
            "Q4. How “Late binding” is implemented in C++?",
            "Q5. Which of the following also known as an instance of a class?",
            "Q6. Which of the following means “The use of an object of one class in definition of another class”?",
            "Q7. Which of the following can be overloaded?",
            "Q8. Which of the following function prototype is perfectly acceptable?",
            "Q9. Which of the following function declaration is/are incorrect?",
            "Q10. Which of the following function / types of function cannot have default parameters?",
            "Q11. What happens when a class with parameterized constructors and having no default constructor is used in a program and we create an object that needs a zero-argument constructor?",
            "Q12. Copy constructor must receive its arguments by __________ .",
            "Q13. Which of the following gets called when an object goes out of scope?",
            "Q14. Which of the following are NOT provided by the compiler by default?",
            "Q15. If the programmer does not explicitly provide a destructor, then which of the following creates an empty destructor?",
            "Q16. Which of the following implicitly creates a default constructor when the programmer does not explicitly define at least one constructor for a class?",
            "Q17. Which of the following never requires any arguments?",
            "Q18. To ensure that every object in the array receives a destructor call, always delete memory allocated as an array with operator __________ .",
            "Q19. Minimum number of temporary variable needed to swap content of two variable is:",
            "Q20. Inline function are invoked at the time of"
    };
    String answers[] = {"Default arguments","2","Ad-hoc polymorphism","Using Virtual tables","Object","Composition","Both B and C","int Function(int Tmp = Show());","Both A and B are incorrect.","main()","Compile-time error.","only pass-by-reference","destructor","Copy Destructor","Compiler","Compiler","Default constructor","delete[]","0","Both B and C."};
    String opt[] = {
            "Default arguments","Call by reference","Call by value","Call by pointer",
            "3","2","1","4",
            "Ad-hoc polymorphism","Transient polymorphism","Virtual polymorphism","Pseudo polymorphism",
            "Using C++ tables","Using Virtual tables","Using Indexed virtual tables","Using polymorphic tables",
            "Friend Functions","Member Variables","Member Functions","Object",
            "Encapsulation","Abstraction","Composition","Inheritance",
            "Object","Operators","Functions","Both B and C",
            "int Function(int Tmp = Show());","float = Show(int, float) Function(Tmp);","float Function(int Tmp = Show(int, float));","Both A and C.",
            "int Sum(int a = 0, int b, int c = 3);","int Sum(int a = 5, int b);","int Sum(int a, int b = 2, int c = 3);","Both A and B are incorrect.",
            "Member function of class","Member function of structure","main()","Both B and C",
            "Runtime error.","Preprocessing error.","Compile-time error.","Runtime exception.",
            "only pass-by-reference","only pass-by-value","either pass-by-value or pass-by-reference","only pass by address",
            "constructor","virtual function","main","destructor",
            "Zero-argument Constructor","Copy Destructor","Copy Constructor","Destructor",
            "Preprocessor","main() function","Linker","Compiler",
            "Preprocessor","Compiler","Loader","Linker",
            "Default constructor","Friend function","Member function","const function",
            "delete[]","delete","kill[]","free[]",
            "1","2","3","0",
            "Run time","Compile time","Depends on how it is invoked","Both B and C."
    };

    int flag=0;
    public static int marks=0,correct=0,wrong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp__quiz);

        nextbutton=(Button)findViewById(R.id.button3);
        submitbutton=(Button)findViewById(R.id.buttonquit);
        textView=(TextView) findViewById(R.id.tvque);
        timer=findViewById(R.id.timer);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
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
                    Intent in = new Intent(getApplicationContext(),Cpp_Result.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Cpp_Result.class);
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