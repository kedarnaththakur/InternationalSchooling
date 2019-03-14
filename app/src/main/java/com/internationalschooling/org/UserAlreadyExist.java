package com.internationalschooling.org;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserAlreadyExist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_already_exist);
    }

    public void LoginNow(View view) {
        Intent intent =new Intent(UserAlreadyExist.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
