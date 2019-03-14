package com.internationalschooling.org;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmailVarificationConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_varification_confirm);
    }

    public void LoginNow(View view) {
        Intent intent =new Intent(EmailVarificationConfirm.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
