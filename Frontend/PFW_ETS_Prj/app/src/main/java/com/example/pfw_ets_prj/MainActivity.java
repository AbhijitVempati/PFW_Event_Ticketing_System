package com.example.pfw_ets_prj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
                            // Display the first 500 characters of the response string.
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

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {*/
            /*
            String urlString = "http://localhost:8080/ZPETS/api/events";
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            String result = convertInputStreamToString(is);
            */
                /*    URL url = new URL("http://10.0.2.2:8080/ZPETS/api/events");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    InputStream stream = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    bufferedReader.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
         /*   }
        }).start();*/

/*
        Event event1 = new Event("NCAA Workshops", LocalDate.now(), "2:00 P.M.", "Classic Ballroom");
        Event event2 = new Event("Honors Banquet", LocalDate.now(), "7:00 P.M.", "International Ballroom");
        Event event11 = new Event("testevent", LocalDate.now(), "5:00 P.M.", "pfw");
        Event event3 = new Event("SLL Roundtable", LocalDate.now().plusDays(-1), "5:30 P.M.", "WU 222");
        Event event4 = new Event("Summit Scholars Showcase", LocalDate.now().plusDays(1), "11:00 A.M.", "WU G-21");
        Event event5 = new Event("IB Loading", LocalDate.now().plusDays(2), "11:00 A.M.", "International Ballroom");
        Event event6 = new Event("Spring Celebrate Philanthropy", LocalDate.now().plusDays(3), "1:00 P.M.", "Classic Ballroom");
        Event event7 = new Event("Mastodon Ally training", LocalDate.now().plusDays(4), "2:00 P.M.", "WU 114");
        Event event8 = new Event("ODMA Study Tables", LocalDate.now().plusDays(5), "10:00 A.M.", "WU 222");
        Event event9 = new Event("Diversity 102", LocalDate.now().plusDays(8), "3:30 P.M.", "WU 222");
        Event event10 = new Event("Spanish Memorial", LocalDate.now().plusDays(8), "5:00 P.M.", "International Ballroom");
        Event.eventsList = new ArrayList<>(Arrays.asList(event1, event2, event11, event3, event4, event5, event6, event7, event8, event9, event10));

*/

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

    public void GoToAccount(View v){
        Intent i = new Intent(this, AccountActivity.class);
        startActivity(i);
    }
}