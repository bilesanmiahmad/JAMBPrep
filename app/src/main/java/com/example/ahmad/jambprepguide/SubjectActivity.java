package com.example.ahmad.jambprepguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ahmad.jambprepguide.adapter.SubjectAdapter;
import com.example.ahmad.jambprepguide.model.Subject;
import com.gc.materialdesign.views.CheckBox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {
    SubjectAdapter sa;
    ArrayList<Subject> subjectArrayList;
    ArrayList<String> choices = new ArrayList<>();
    String url = "http://154.113.0.202:8999/utme/rest/api/v1.0/utme/subjects/1";
    TextView[] views = new TextView[4];
    TextView txtData, txt2, txt3, txt4;
    JSONObject jsonSubjects;
    JSONArray arraSubjects;
    Context ctx;
    //Toolbar toolbar;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Complete Profile");
        //setSupportActionBar(toolbar);

        txtData = (TextView) findViewById(R.id.txtData);
        txt2 = (TextView) findViewById(R.id.txtSecond);
        txt3 = (TextView) findViewById(R.id.txtThird);
        txt4 = (TextView) findViewById(R.id.txtFourth);
        btn = (Button) findViewById(R.id.btnSubjects);
        ctx = this;

        Log.e("Globall data", "Data :"+Global.subjectData);


        final ListView listView = (ListView) findViewById(R.id.listview);
        subjectArrayList = new ArrayList<Subject>();
        try {
            jsonSubjects = new JSONObject(Global.subjectData);
            arraSubjects = jsonSubjects.getJSONArray("data");
            for (int i = 0; i < arraSubjects.length(); i++){
                Subject subject = new Subject(arraSubjects.getString(i));
                subjectArrayList.add(subject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sa = new SubjectAdapter(this, R.layout.item_layout, R.id.txt, subjectArrayList);

        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout layout = (LinearLayout) view;
                CheckBox chk = (CheckBox) layout.getChildAt(2);
                //chk.setOncheckListener();
                if(Global.subjectChoices.size()>3 && !subjectArrayList.get(position).isSelected())
                      {
                    Toast.makeText(getBaseContext(), "Maximum subjects reached", Toast.LENGTH_SHORT).show();

                      }
                else {
                    if (subjectArrayList.get(position).isSelected()) {
                        subjectArrayList.get(position).setSelected(false);
                        Global.subjectChoices.remove(subjectArrayList.get(position));
                        chk.setChecked(false);


                    } else {
                        subjectArrayList.get(position).setSelected(true);
                        Global.subjectChoices.add(subjectArrayList.get(position));
                        chk.setChecked(true);

                    }
                }

//
                Log.e("Position clicked", String.valueOf(position));

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choices.size()<4){
                    Toast.makeText(getBaseContext(), "Please choose four subjects", Toast.LENGTH_SHORT).show();
                }else{

                    Global.getInstitutesFromApi(Global.instituteUrl, ctx);
                }
            }
        });
    }

    void showNothing() {
        txtData.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
    }

    void show1() {
        txtData.setText(choices.get(0));
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
    }

    void show2() {
        txtData.setText(choices.get(0));
        txt2.setText(choices.get(1));
        txt3.setText("");
        txt4.setText("");
    }

    void show3() {
        txtData.setText(choices.get(0));
        txt2.setText(choices.get(1));
        txt3.setText(choices.get(2));
        txt4.setText("");
    }

    void show4() {
        txtData.setText(choices.get(0));
        txt2.setText(choices.get(1));
        txt3.setText(choices.get(2));
        txt4.setText(choices.get(3));
    }

    public void onStart() {
        super.onStart();


    }
}
