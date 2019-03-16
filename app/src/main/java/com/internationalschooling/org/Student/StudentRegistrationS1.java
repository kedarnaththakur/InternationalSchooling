package com.internationalschooling.org.Student;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.internationalschooling.org.Config;
import com.internationalschooling.org.EmailVarificationConfirm;
import com.internationalschooling.org.MainActivity;
import com.internationalschooling.org.R;
import com.internationalschooling.org.RegisterUser;
import com.internationalschooling.org.UserAlreadyExist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class StudentRegistrationS1 extends AppCompatActivity {
private Spinner CountrySpinner,GenderSpinner,StateSpinner,citySpinner,learningCenter;
    List<String> list;
    ArrayAdapter<String> SpinnerAdapter;
    ArrayAdapter<String> GenderAdapter;
    ArrayAdapter<String> StateAdapter;
    private EditText editTextbday;
    private ProgressBar progressBar;
    String[] countries,gender;
   private JSONArray result;
   private JSONArray result1,result2,result3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration_s1);
        CountrySpinner = (Spinner) findViewById(R.id.CountrySpinner);
        GenderSpinner =(Spinner)findViewById(R.id.GenderSpinner);
        editTextbday=(EditText)findViewById(R.id.Birthday);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        StateSpinner=(Spinner)findViewById(R.id.StateSpinner);
        citySpinner=(Spinner)findViewById(R.id.CitySpinner);
        learningCenter=(Spinner)findViewById(R.id.LearningCenterSpinner);
        fetchCountryCenter();

//**************************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //*************************************************************************************
        //************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //*************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //**************************************************************************************
        //*************************************************************************************
        //**
        //******************* fetch Country by postRequest
        RequestQueue requestQueue = Volley.newRequestQueue(this);

                    String URL = Config.CountrySpinnerdata;

        String jsonBody ="{\n" +
                "\t\"authentication\":{\n" +
                "\t\"hash\":\"sdfsdfsfdsdff\"\n" +
                "\t},\n" +
                "\t\"requestData\":{\n" +
                "\t\t\"requestKey\":\"COUNTRIES-LIST\",\n" +
                "\t\t\"requestValue\":\"0\"\n" +
                "\t}\n" +
                "}";
              final String mRequestBody = jsonBody;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject obj = null;

                try {
                    ArrayList<String> cs = new ArrayList<String>();
                    obj = new JSONObject(response);

                    String mastersData = obj.getString("mastersData");
                    JSONObject obj1 = new JSONObject(mastersData);
                    String country = obj1.getString("countries");

                    result = obj1.getJSONArray("countries");
                    for(int i=0;i<result.length();i++){
                        try {
                            //Getting json object
                            JSONObject json = result.getJSONObject(i);

                            //Adding the name of the student to array list
                            cs.add(json.getString("value"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    //set array to adapter and give colour to view
 CountrySpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
         android.R.layout.simple_spinner_dropdown_item, cs){

                        public View getView(int position, View convertView,
                                            ViewGroup parent) {
                            View v = super.getView(position, convertView, parent);
                            ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                            return v;
                        }


                        public View getDropDownView(int position, View convertView,
                                                    ViewGroup parent) {
                            View v = super.getDropDownView(position, convertView,
                                    parent);
                            v.setBackgroundColor(Color.parseColor("#0B1996"));
                            ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                            return v;
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                CountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String name =getName(position);
                        String key =getkey(position);
                     //fetch value of states
                        fetchstate(name,key);
                       }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                  }
//**************************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
//**************************************************************************************************
            //*************************************************************************************
            //************************************************************************************
            //**************************************************************************************
//**************************************************************************************************
            //*************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
//**************************************************************************************************
            //**************************************************************************************
            //*************************************************************************************
            //**
            // METHORD TO FETCH State DATA
                private void fetchstate(final String name, String key) {
                RequestQueue requestQueue = Volley.newRequestQueue(StudentRegistrationS1.this);

                String CountryUrl = Config.CountrySpinnerdata;

                // JSONObject jsonBody1 = new JSONObject();
                //  jsonBody.put("authentication", "firstvalue");
                //  jsonBody.put("requestData", "secondobject");
                String jsonBody ="{\n" +
                        "\"authentication\":{\n" +
                        "\t\t\t\"hash\":\"sdfsdfsfdsdff\"\n" +
                        "},\n" +
                        "\"requestData\":{\n" +
                        "\t\"requestKey\":\"STATES-LIST\",\n" +
                        "\t\"requestValue\":\""+key+"\"\n" +
                        "\t\n" +
                        "}\n" +
                        "}";


                final String mRequestBody = jsonBody;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, CountryUrl,
                    new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject obj = null;

                        try {
                            ArrayList<String> cs1 = new ArrayList<String>();
                            obj = new JSONObject(response);
                            String mastersData = obj.getString("mastersData");
                            JSONObject obj1 = new JSONObject(mastersData);

                                result1 = obj1.getJSONArray("states");

                            for(int i=0;i<result1.length();i++) {
                                try {
                                    //Getting json object
                                    JSONObject json = result1.getJSONObject(i);

                                    //Adding the name of the student to array list
                                    cs1.add(json.getString("value"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            StateSpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
                                    android.R.layout.simple_spinner_dropdown_item, cs1){

                                public View getView(int position, View convertView,
                                                    ViewGroup parent) {
                                    View v = super.getView(position, convertView, parent);
                                    ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                                    return v;
                                }


                                public View getDropDownView(int position, View convertView,
                                                            ViewGroup parent) {
                                    View v = super.getDropDownView(position, convertView,
                                            parent);
                                    v.setBackgroundColor(Color.parseColor("#0B1996"));
                                    ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                                    return v;
                                }
                            });
                       } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String namestate =getStateName(position);
                                String keystate =getStateKey(position);
                                 fetchcity(namestate,keystate);

                            }
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

//**************************************************************************************************
                        //**************************************************************************************
                        //**************************************************************************************
                        //**************************************************************************************
//**************************************************************************************************
                        //*************************************************************************************
                        //************************************************************************************
                        //**************************************************************************************
//**************************************************************************************************
                        //*************************************************************************************
                        //**************************************************************************************
                        //**************************************************************************************
//**************************************************************************************************
                        //**************************************************************************************
                        //*************************************************************************************
                        //**
                //  METHORD TO FETCH City DATA

                private void fetchcity(String namestate, String keystate) {
                    RequestQueue requestQueue = Volley.newRequestQueue(StudentRegistrationS1.this);

                    String CountryUrl = Config.CountrySpinnerdata;

                    // JSONObject jsonBody1 = new JSONObject();
                    //  jsonBody.put("authentication", "firstvalue");
                    //  jsonBody.put("requestData", "secondobject");
                    String jsonBody ="{\n" +
                            "\t\"authentication\":{\n" +
                            "\t\"hash\":\"sdfsdfsfdsdff\"\n" +
                            "\t},\n" +
                            "\t\"requestData\":{\n" +
                            "\t\t\"requestKey\":\"CITIES-LIST\",\n" +
                            "\t\t\"requestValue\":\""+keystate+"\"\n" +
                            "\t}\n" +
                            "}";


                    final String mRequestBody = jsonBody;
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, CountryUrl,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JSONObject obj = null;

                                    try {
                                        ArrayList<String> cs2 = new ArrayList<String>();
                                        obj = new JSONObject(response);
                                        String mastersData = obj.getString("mastersData");
                                        JSONObject obj2 = new JSONObject(mastersData);

                                        result2 = obj2.getJSONArray("cities");
                                        Log.v("TEST",result2.toString());

                                        for(int i=0;i<result2.length();i++) {
                                            try {
                                                //Getting json object
                                                JSONObject json = result2.getJSONObject(i);

                                                //Adding the name of the student to array list
                                                cs2.add(json.getString("value"));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }
                                        citySpinner.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
                                                android.R.layout.simple_spinner_dropdown_item, cs2){

                                            public View getView(int position, View convertView,
                                                                ViewGroup parent) {
                                                View v = super.getView(position, convertView, parent);
                                                ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                                                return v;
                                            }


                                            public View getDropDownView(int position, View convertView,
                                                                        ViewGroup parent) {
                                                View v = super.getDropDownView(position, convertView,
                                                        parent);
                                                v.setBackgroundColor(Color.parseColor("#0B1996"));
                                                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                                                return v;
                                            }
                                        });
                                     //set spinner adapter


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                 //onclick
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
//*************************************************************************************************************
//**************************************************************************************************
                        //**************************************************************************************
                        //**************************************************************************************
                        //**************************************************************************************
//**************************************************************************************************
                        //*************************************************************************************
                        //************************************************************************************
                        //**************************************************************************************
//**************************************************************************************************
                        //*************************************************************************************
                        //**************************************************************************************
                        //**************************************************************************************
//**************************************************************************************************
                        //**************************************************************************************
                        //*************************************************************************************
                        //**
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
//**************************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
//**************************************************************************************************
            //*************************************************************************************
            //************************************************************************************
            //**************************************************************************************
//**************************************************************************************************
            //*************************************************************************************
            //**************************************************************************************
            //**************************************************************************************
//**************************************************************************************************
            //**************************************************************************************
            //*************************************************************************************
            //**
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
        //************************************************************************************
       // set gender Spinner
        gender= getResources().getStringArray(R.array.gender);
        SpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,gender ){

            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                return v;
            }


            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,
                        parent);
                v.setBackgroundColor(Color.parseColor("#0B1996"));
                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                return v;
            }
        };
        SpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        // Set Adapter in the spinner
        GenderSpinner.setAdapter(SpinnerAdapter);

        //************************************************************************************
        //**************************************************************************************************
        //*************************************************************************************
        //************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //*************************************************************************************
        //**************************************************************************************
        //**************************************************************************************
//**************************************************************************************************
        //**************************************************************************************
        //*************************************************************************************
        //**





               }

    private void fetchCountryCenter() {

        RequestQueue requestQueue = Volley.newRequestQueue(StudentRegistrationS1.this);

        String Url = Config.CountrySpinnerdata;

        String jsonBody ="{\n" +
                "\"authentication\":{\n" +
                "\t\t\t\"hash\":\"sdfsdfsfdsdff\"\n" +
                "},\n" +
                "\"requestData\":{\n" +
                "\t\"requestKey\":\"SCHOOLS-LIST\",\n" +
                "\t\"requestValue\":\"1\"\n" +
                "\t\n" +
                "}\n" +
                "}";


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
                        String master = null;
                        try {
                            master = obj.getString("mastersData");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject obj1 = null;
                        try {
                            obj1 = new JSONObject(master);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            String Schools=obj1.getString("schools");
                            Log.v("Schools is" ,Schools);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayList<String> cs2 = new ArrayList<String>();


                        try {
                            result3 = obj1.getJSONArray("schools");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        for(int i=0;i<result3.length();i++) {
                            try {
                                //Getting json object
                                JSONObject json = result3.getJSONObject(i);

                                //Adding the name of the student to array list
                                cs2.add(json.getString("value"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        learningCenter.setAdapter(new ArrayAdapter<String>(StudentRegistrationS1.this,
                                android.R.layout.simple_spinner_dropdown_item, cs2){

                            public View getView(int position, View convertView,
                                                ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);
                                ((TextView) v).setTextColor(Color.parseColor("#0B1996"));
                                return v;
                            }


                            public View getDropDownView(int position, View convertView,
                                                        ViewGroup parent) {
                                View v = super.getDropDownView(position, convertView,
                                        parent);
                                v.setBackgroundColor(Color.parseColor("#0B1996"));
                                ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                                return v;
                            }
                        });
                        //set spinner adapter

















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

    public void NextClick(View view) {
        Intent intent = new Intent(this,StudentRegistrationS2.class);
        startActivity(intent);

    }

    public void datePick(View view) {

        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String myFormat = "MM/dd/yy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                    editTextbday.setText(sdf.format(myCalendar.getTime()));

            }

        };

        editTextbday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(StudentRegistrationS1.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    public void ClickNext(View view) {}
    private String getName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
    private String getkey(int position){
        String key="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            key = json.getString("key");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return key;
    }
    private String getStateName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result1.getJSONObject(position);

            //Fetching name from that object
            name = json.getString("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }
    private String getStateKey(int position){
        String key="";
        try {
            //Getting object of given index
            JSONObject json = result1.getJSONObject(position);

            //Fetching name from that object
            key = json.getString("key");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return key;
    }
}




