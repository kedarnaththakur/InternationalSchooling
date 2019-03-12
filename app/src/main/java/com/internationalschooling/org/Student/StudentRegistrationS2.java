package com.internationalschooling.org.Student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.internationalschooling.org.R;

public class StudentRegistrationS2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_s2);
    }

    public void NextClick(View view) {
        Intent intent = new Intent(StudentRegistrationS2.this,StudentRegistrationS3.class);
        startActivity(intent);
    }
}
