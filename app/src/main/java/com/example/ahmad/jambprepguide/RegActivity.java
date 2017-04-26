package com.example.ahmad.jambprepguide;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class RegActivity extends AppCompatActivity{
    EditText txtfullname, txtphone, txtemail, txtpass, txtconpass, txtUtmeNumber;
    Spinner gender;
    Button btnReg;
    AlertDialog.Builder builder;
    String name, phone, email, password, confirm, regNumber;
    String reg_url = "http://154.113.0.202:8999/utme/rest/api/v1.0/user/register";
    SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //sharedPreferences = this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);

        txtfullname = (EditText) findViewById(R.id.edit_reg_fullname);
        txtphone = (EditText) findViewById(R.id.edit_reg_phone);
        txtemail = (EditText) findViewById(R.id.edit_reg_age);
        txtUtmeNumber = (EditText) findViewById(R.id.edit_reg_number);
        txtpass = (EditText) findViewById(R.id.edit_reg_pass);
        txtconpass = (EditText) findViewById(R.id.edit_reg_passagain);
        gender = (Spinner) findViewById(R.id.spin_reg_gender);
        builder = new AlertDialog.Builder(RegActivity.this);


        btnReg = (Button) findViewById(R.id.btn_reg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtfullname.getText().toString();
                phone = txtphone.getText().toString();
                email = txtemail.getText().toString();
                password = txtpass.getText().toString();
                confirm = txtconpass.getText().toString();
                regNumber = txtUtmeNumber.getText().toString();

                if (name.equals("") || phone.equals("") || email.equals("") || password.equals("") || confirm.equals("") || regNumber.equals("")) {
                    builder.setTitle("Something went wrong...");
                    builder.setMessage("Please fill all the fields");
                    displayAlert("input_error");
                } else if (!(password.equals(confirm))) {
                    builder.setTitle("Something went wrong...");
                    builder.setMessage("Passwords are not matching...");
                    displayAlert("input_error");
                } else {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("phonenumber", phone);
                    params.put("email", email);
                    params.put("utmenumber", regNumber);
                    params.put("password", password);

                    JsonObjectRequest request = new JsonObjectRequest(reg_url, new JSONObject(params),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("Response", response.toString());
                                    JSONObject data = response;
                                    JSONObject d = null;
                                    try {
                                        d = data.getJSONObject("status");
                                        String desc = d.getString("description");
                                        Log.e("Data", desc);
                                        builder.setTitle("Status");
                                        builder.setMessage(desc);
                                        displayAlert("Response");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error", error.getMessage());
                        }
                    });

                    JambSingleton.getInstance(RegActivity.this).addToRequestqueue(request);
                }

            }
        });
    }


    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")){
                    txtpass.setText("");
                    txtconpass.setText("");
                }
                else if (code.equals("Response")){
                    sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("phone", phone);
                    editor.putString("email", email);
                    editor.putString("utme", regNumber);
                    editor.putString("pass", password);
                    editor.commit();

                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
