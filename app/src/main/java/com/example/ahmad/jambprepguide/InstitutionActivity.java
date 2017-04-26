package com.example.ahmad.jambprepguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ahmad.jambprepguide.adapter.InstitutionAdapter;
import com.example.ahmad.jambprepguide.adapter.SubjectAdapter;
import com.example.ahmad.jambprepguide.model.Institution;
import com.example.ahmad.jambprepguide.model.Subject;
import com.gc.materialdesign.views.CheckBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstitutionActivity extends AppCompatActivity {

    InstitutionAdapter institutionAdapter;
    ArrayList<Institution> institutions;
    ArrayList<String> choices = new ArrayList<>();
    String url ="http://154.113.0.202:8999/utme/rest/api/v1.0/utme/universities/1";
    TextView txtInst1, txtInst2, txtInst3, txtInst4;
    JSONObject objInstitutes;
    JSONArray arrayInstitutes;
    JSONObject getObjInstitute;
    Toolbar toolbar;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Complete Profile");
        //setSupportActionBar(toolbar);

        txtInst1 = (TextView) findViewById(R.id.txtFirstInst);
        txtInst2 = (TextView) findViewById(R.id.txtSecondInst);
        txtInst3 = (TextView) findViewById(R.id.txtThirdInst);
        txtInst4 = (TextView) findViewById(R.id.txtFourthInst);
        btn = (Button) findViewById(R.id.btnInst);

        ListView listView = (ListView) findViewById(R.id.instlistview);
        institutions = new ArrayList<Institution>();
        try {
            objInstitutes = new JSONObject(Global.instituteData);
            arrayInstitutes = objInstitutes.getJSONArray("data");
            for (int i = 0; i < arrayInstitutes.length(); i++){
                getObjInstitute = arrayInstitutes.getJSONObject(i);
                Institution institution = new Institution(getObjInstitute.getString("name"));
                institutions.add(institution);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        institutionAdapter = new InstitutionAdapter(this, R.layout.item_layout, R.id.txt, institutions);

        listView.setAdapter(institutionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout layout = (LinearLayout) view;
                CheckBox chk = (CheckBox) layout.getChildAt(2);
                String choice = parent.getItemAtPosition(position).toString();
                if (choices.size() == 4) {
                    if (chk.isCheck()) {
                        chk.setChecked(false);
                        choices.remove(choice);
                        show3();
                    } else {
                        Toast.makeText(getBaseContext(), "Maximum subjects reached", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (chk.isCheck()) {
                        chk.setChecked(false);
                        choices.remove(choice);
                        if (choices.size() == 0) {
                            showNothing();
                        }
                        if (choices.size() == 1) {
                            show1();
                        }
                        if (choices.size() == 2) {
                            show2();
                        }
                        if (choices.size() == 3) {
                            show3();
                        }
                    } else {
                        choices.add(choice);
                        Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_SHORT).show();
                        Log.e("Object", parent.getItemAtPosition(position).toString());
                        chk.setChecked(true);
                        if (choices.size() == 1) {
                            show1();
                        }
                        if (choices.size() == 2) {
                            show2();
                        }
                        if (choices.size() == 3) {
                            show3();
                        }
                        if (choices.size() == 4) {
                            show4();
                        }

                    }

                }

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (institutions.size()<4){
                    Toast.makeText(getBaseContext(), "Please choose 4 institutions.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    void showNothing() {
        txtInst1.setText("");
        txtInst2.setText("");
        txtInst3.setText("");
        txtInst4.setText("");
    }

    void show1() {
        txtInst1.setText(choices.get(0));
        txtInst2.setText("");
        txtInst3.setText("");
        txtInst4.setText("");
    }

    void show2() {
        txtInst1.setText(choices.get(0));
        txtInst2.setText(choices.get(1));
        txtInst3.setText("");
        txtInst4.setText("");
    }

    void show3() {
        txtInst1.setText(choices.get(0));
        txtInst2.setText(choices.get(1));
        txtInst3.setText(choices.get(2));
        txtInst4.setText("");
    }

    void show4() {
        txtInst1.setText(choices.get(0));
        txtInst2.setText(choices.get(1));
        txtInst3.setText(choices.get(2));
        txtInst4.setText(choices.get(3));
    }

    public void onStart() {
        super.onStart();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (institutions.size() > 1) {
                    institutions.clear();
                }
                //JSONObject object = response;
                try {
                    Log.e("data", response.toString());
                    JSONArray array = response.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject intObj = array.getJSONObject(i);
                        String title = intObj.getString("name");
                        Institution institution = new Institution(title);
                        institutions.add(institution);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                institutionAdapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Unable to parse json array");
                error.printStackTrace();
            }
        });

        JambSingleton.getInstance(this).addToRequestqueue(jsonObjectRequest);
    }

}
