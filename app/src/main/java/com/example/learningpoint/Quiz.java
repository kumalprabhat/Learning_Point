package com.example.learningpoint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class Quiz extends AppCompatActivity {
    Button c,cpp,java,python,sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        c=findViewById(R.id.c);
        cpp=findViewById(R.id.cpp);
        java=findViewById(R.id.java);
        python=findViewById(R.id.python);
        sql=findViewById(R.id.sql);


        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),C_Quiz.class);
                startActivity(i);
            }
        });
        cpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Cpp_Quiz.class);
                startActivity(i);
            }

        });
        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Java_Quiz.class);
                startActivity(i);
            }
        });
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Python_Quiz.class);
                startActivity(i);
            }
        });
        sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Sql_Quiz.class);
                startActivity(i);
            }
        });
    }
}