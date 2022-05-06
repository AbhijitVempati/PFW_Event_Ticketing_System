package com.example.pfw_ets_prj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import org.json.*;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pfw_ets_prj.data.RequestQueueCall;
import com.example.pfw_ets_prj.ui.login.LoginActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        RequestQueue queue = RequestQueueCall.getInstance(this.getApplicationContext()).
                getRequestQueue();
        try {
            String url = "http://10.0.2.2:8080/ZPETS/api/events";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("Response: " + response);
                            JSONArray  json = null;
                            try {
                                json = new JSONArray (response);
                                Event.eventsList = new ArrayList<>();
                                Event event;
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
                                for(int i=0; i < json.length(); i++) {
                                    JSONObject jsonobject = json.getJSONObject(i);
                                    String id       = jsonobject.getString("event_id");
                                    String title    = jsonobject.getString("event_name");
                                    String date  = jsonobject.getString("event_date");
                                    String venue = jsonobject.getString("dept");
                                    String reg = jsonobject.getString("event_reg");
                                    LocalDate d = LocalDate.parse(date,formatter);
                                    event= new Event(title, d, "2:00 P.M.", venue,reg);

                                    Event.eventsList.add(event);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            System.out.println(json.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Response: " + error.toString());
                }
            });
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            RequestQueueCall.getInstance(this).addToRequestQueue(stringRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GoToEventsList(View v){
        Intent i = new Intent(this, ViewEventsActivity.class);
        startActivity(i);
    }

    public void Login(View v){
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("flag","1");
        startActivity(i);

    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

    }

    public void GoToAccount(View v){
        Intent i = new Intent(this, AccountActivity.class);
        startActivity(i);
    }

}