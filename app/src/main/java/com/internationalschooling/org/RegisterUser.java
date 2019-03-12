package com.internationalschooling.org;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.internationalschooling.org.Student.StudentRegistrationS1;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public void ClickOnRead(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUser.this);


        builder.setTitle("View Terms and Policy");


        builder.setMessage("you can view full details of Terms and Privacy choose any of them");


        //Button One : Yes
        builder.setPositiveButton("Terms", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = "https://www.internationalschooling.org/terms-of-use.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


        //Button Two : No
        builder.setNegativeButton("Privacy policy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String url = "https://www.internationalschooling.org/privacy-policy.html";
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(url));
                startActivity(i1);
                dialog.cancel();
            }
        });

        //Button Three : Neutral
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog diag = builder.create();
        diag.show();
    }

    public void Register(View view) {
        Intent intent1 = new Intent(RegisterUser.this, StudentRegistrationS1.class);
        startActivity(intent1);

    }
}
