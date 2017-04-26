package com.example.ahmad.jambprepguide;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ahmad.jambprepguide.model.Institution;
import com.example.ahmad.jambprepguide.model.Subject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 4/5/17.
 */

public class Global {
    public static String regUrl = "http://154.113.0.202:8999/utme/rest/api/v1.0/user/register";
    public static String loginUrl = "http://154.113.0.202:8999/utme/rest/api/v1.0/user/login";
    public static String subjectUrl = "http://154.113.0.202:8999/utme/rest/api/v1.0/utme/subjects/1";
    public static String instituteUrl = "http://154.113.0.202:8999/utme/rest/api/v1.0/utme/universities/1";
    public static String topicUrl = "";

    public static String subjectData = "";
    public static String instituteData = "";
    public static String topicData = "";

    public static ArrayList<Subject> subjectChoices = new ArrayList<>();

    public static String topicTitle = "";

    public static ArrayList<Subject> subjects = new ArrayList<>();
    public static ArrayList<Institution> institutions = new ArrayList<>();

    public static void getDataFromApi(String url , final Context ctx){
        Log.e("Pogress", "Progress Globall");
        final ProgressDialog progressDialog= new ProgressDialog(ctx);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading ....");
        progressDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //dosSomething(response);
                Global.subjectData = response.toString();
                Log.e("Res","res: "+subjectData);
                progressDialog.dismiss();
                Intent intent=new Intent(ctx, SubjectActivity.class);
                ctx.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Data", error.toString());
                error.printStackTrace();
                progressDialog.dismiss();

            }
        });
        JambSingleton.getInstance(ctx).addToRequestqueue(request);
    }

    public static void getInstitutesFromApi(String url, final Context context){
        Log.e("Pogress", "Progress Globall");
        final ProgressDialog progressDialog= new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading ....");
        progressDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //dosSomething(response);
                Global.instituteData = response.toString();
                Log.e("Res","res: "+instituteData);
                progressDialog.dismiss();
                Intent intent=new Intent(context, InstitutionActivity.class);
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Data", error.toString());
                error.printStackTrace();
                progressDialog.dismiss();

            }
        });
        JambSingleton.getInstance(context).addToRequestqueue(request);
    }

    public static void getTopicsFromApi(String url, final Context context, final String topic){
        Log.e("Pogress", "Progress Globall");
        final ProgressDialog progressDialog= new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading ....");
        progressDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+topic, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //dosSomething(response);
                Global.topicData = response.toString();
                topicTitle = topic;
                Log.e("Res","res: "+topicData);
                progressDialog.dismiss();
                Intent intent=new Intent(context, TopicsActivity.class);
                context.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Data", error.toString());
                error.printStackTrace();
                progressDialog.dismiss();

            }
        });
        JambSingleton.getInstance(context).addToRequestqueue(request);
    }
}
