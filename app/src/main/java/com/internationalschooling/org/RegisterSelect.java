package com.internationalschooling.org;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegisterSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_select);
    }



    public void StudentRoleClick(View view) {
        Intent studentintent= new Intent(RegisterSelect.this,RegisterUser.class);
        studentintent.putExtra("ROLE","STUDENT");
        startActivity(studentintent);
        finish();

    }

    public void TeacherRoleClick(View view) {
        Intent teacherintent= new Intent(RegisterSelect.this,RegisterUser.class);
       teacherintent.putExtra("ROLE","TEACHER");
        startActivity(teacherintent);
        finish();
    }

    public void SchoolRoleClick(View view) {
        Intent SchoolIntent= new Intent(RegisterSelect.this,RegisterUser.class);
        SchoolIntent.putExtra("ROLE","SCHOOL");
        startActivity(SchoolIntent);
        finish();
    }
}
