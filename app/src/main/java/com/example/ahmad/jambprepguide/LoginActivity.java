package com.example.ahmad.jambprepguide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    EditText txtTel, txtPass;
    Button btnSign;
    String telephone, pass;
    SharedPreferences sharedPreferences;
    String url = "http://154.113.0.202:8999/utme/rest/api/v1.0/user/login";
    String data = "";
    String token;
    Context context;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtTel = (EditText) findViewById(R.id.txtTel);
        txtPass = (EditText) findViewById(R.id.txtPass);
        btnSign = (Button) findViewById(R.id.btnSignin);
        builder = new AlertDialog.Builder(LoginActivity.this);
        context=this;
        progressDialog=new ProgressDialog(this);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telephone = txtTel.getText().toString();
                pass = txtPass.getText().toString();

                if (telephone.equals("")||pass.equals("")){
                    builder.setTitle("Something went wrong...");
                    builder.setMessage("Please fill all fields");
                    displayAlert("input_error");
                }else{
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating ...");
                    progressDialog.show();


                    //String subjectData = getDataFromApi("http://154.113.0.202:8999/utme/rest/api/v1.0/utme/subjects/1");
                    //Log.e("Subjects", subjectData);
                    Log.e("SubjectData", "before method call"+Global.subjectData);

                    Log.e("SubjectData", "after method call"+Global.subjectData);

                    HashMap<String, String> params =new HashMap<String, String>();
                    params.put("username",telephone);
                    params.put("password", pass);

                    JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params),
                            new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("Login data", response.toString());
                                    JSONObject data = response;
                                    JSONObject d;
                                    try {
                                        d = data.getJSONObject("status");
                                        String desc = d.getString("description");
                                        token = data.getString("data");
                                        Log.e("Token", token);
                                        progressDialog.dismiss();
                                        builder.setTitle("Response");
                                        builder.setMessage(desc);
                                        displayAlert("Response");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("Error", error.toString());
                            progressDialog.dismiss();
                            builder.setTitle("REsponse");
                            builder.setMessage(error.toString());
                            displayAlert("network");
                            error.printStackTrace();
                        }
                    });
                    JambSingleton.getInstance(LoginActivity.this).addToRequestqueue(request);
                }
            }
        });

    }

    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input_error")){
                    txtPass.setText("");
                }
                else if(code.equals("network")){

                }
                else if (code.equals("Response")){
                    sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.commit();

                    Global.getDataFromApi(Global.subjectUrl, context);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
