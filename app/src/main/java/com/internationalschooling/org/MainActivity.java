package com.internationalschooling.org;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.internationalschooling.org.Student.StudentHome;
import com.internationalschooling.org.Student.StudentRegistrationS1;
import com.internationalschooling.org.Student.StudentRegistrationS2;
import com.internationalschooling.org.Student.StudentRegistrationS3;
import com.internationalschooling.org.Student.StudentRegistrationS4;
import com.internationalschooling.org.Student.StudentRegistrationS5;
import com.internationalschooling.org.Student.StudentRegistrationS6;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(TextInputEditText)findViewById(R.id.email);
        password=(TextInputEditText)findViewById(R.id.password);

    }

    public void RegisterClick(View view) {
        Intent intent= new Intent(MainActivity.this,RegisterSelect.class);
        startActivity(intent);
       }

    public void LoginClick(View view) {

        String Url = Config.LoginUrl;
        String Email,Pass;
        Email=email.getEditableText().toString();
        Pass=password.getEditableText().toString();
        if(Validations.isValidEmail(Email) && Validations.isValidPass(Pass)){
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);


            String jsonBody ="{  \n" +
                    "   \"authentication\":{  \n" +
                    "      \"hash\":\"sladfjlkasldflsaj\",\n" +
                    "      \"userType\":\"STUDENT\",\n" +
                    "      \"userId\":\"\",\n" +
                    "      \"studentId\":\"\",\n" +
                    "      \"parentId\":\"\"\n" +
                    "   },\n" +
                    "   \"requestData\":{  \n" +
                    "      \"loginDTO\":{  \n" +
                    "         \"email\":\""+Email+"\",\n" +
                    "         \"password\":\""+Pass+"\",\n" +
                    "         \"captcha\":\"Golu1234\"\n" +
                    "      }\n" +
                    "   }\n" +
                    "}\n";


            final String mRequestBody = jsonBody;
            StringRequest stringRequest = new StringRequest(Request.Method.POST,Url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject  obj = null;
                            try {
                                obj = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                String message = obj.getString("message");
                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                                Log.v("BBBBBB",message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                String responseLoginDTO = obj.getString("responseLoginDTO");
                               JSONObject obj1 = new JSONObject(responseLoginDTO);
                               String userhash = obj1.getString("userLoginHash");
                                String usertype = obj1.getString("userType");
                                String loginstage = obj1.getString("lognStage");

                                Log.v("CCCCCC",responseLoginDTO);
                                Log.v("EEEEEEE",usertype);
                                Log.v("EEEEEEE",loginstage);
                                if(usertype.equals("4")){
                                    if (loginstage.equals("10")) {
                                        Intent intent = new Intent(MainActivity.this,StudentRegistrationS1.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (loginstage.equals("11")) {
                                        Intent intent = new Intent(MainActivity.this,StudentRegistrationS2.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if (loginstage.equals("12")) {
                                        Intent intent = new Intent(MainActivity.this,StudentRegistrationS3.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if (loginstage.equals("13")) {
                                        Intent intent = new Intent(MainActivity.this,StudentRegistrationS4.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if (loginstage.equals("14")) {
                                        Intent intent = new Intent(MainActivity.this,StudentRegistrationS5.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else if (loginstage.equals("15")) {
                                        Intent intent = new Intent(MainActivity.this, StudentRegistrationS6.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //  JSONObject obj2 = new JSONObject(responseData);
                            // result = obj2.getJSONArray("cities");



                        }


                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                mRequestBody, "utf-8");
                        return null;
                    }
                }

            };
            requestQueue.add(stringRequest);

        }
        else {

            Toast.makeText(MainActivity.this,"Email or Password formate is wrong",Toast.LENGTH_SHORT).show();
        }

 }
}
