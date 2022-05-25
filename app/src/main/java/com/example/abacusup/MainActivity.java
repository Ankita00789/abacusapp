package com.example.abacusup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button btnUniv;
Button btnCollege;
Button btnFaculty;
Button btnStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnUniv=findViewById(R.id.btnView5);
        btnCollege=findViewById(R.id.btnView6);
        btnFaculty=findViewById(R.id.btnView7);
        btnStudent=findViewById(R.id.btnView8);
        btnUniv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,UniversityReport.class);
                startActivity(intent);
            }
        });
        btnCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CollegeReport.class);
                startActivity(intent);
            }
        });
        btnFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,FacultyReport.class);
                startActivity(intent);
            }
        });
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,StudentReport.class);
                startActivity(intent);
            }
        });
    }
}